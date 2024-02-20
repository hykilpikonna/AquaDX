package icu.samnyan.aqua.net

import ext.*
import icu.samnyan.aqua.net.components.EmailService
import icu.samnyan.aqua.net.components.GeoIP
import icu.samnyan.aqua.net.components.JWT
import icu.samnyan.aqua.net.components.TurnstileService
import icu.samnyan.aqua.net.db.AquaNetUser
import icu.samnyan.aqua.net.db.AquaNetUserRepo
import icu.samnyan.aqua.net.db.AquaUserValidator
import icu.samnyan.aqua.net.db.AquaUserValidator.Companion.SETTING_FIELDS
import icu.samnyan.aqua.net.db.EmailConfirmationRepo
import icu.samnyan.aqua.net.utils.SUCCESS
import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.general.service.CardService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalDateTime
import java.util.Random
import kotlin.reflect.KMutableProperty

@RestController
@API("/api/v2/user")
class UserRegistrar(
    val userRepo: AquaNetUserRepo,
    val hasher: PasswordEncoder,
    val turnstileService: TurnstileService,
    val emailService: EmailService,
    val geoIP: GeoIP,
    val jwt: JWT,
    val confirmationRepo: EmailConfirmationRepo,
    val cardRepo: CardRepository,
    val cardService: CardService,
    val validator: AquaUserValidator,
) {
    companion object {
        // Random long with length 17 (10^18 possibilities)
        const val cardExtIdStart = 1e17.toLong()
        const val cardExtIdEnd = 1e18.toLong() - 1
    }

    /**
     * Register a new user
     */
    @API("/register")
    suspend fun register(
        @RP username: Str, @RP email: Str, @RP password: Str, @RP turnstile: Str,
        request: HttpServletRequest
    ): Any {

        val ip = geoIP.getIP(request)

        // Check captcha
        if (!turnstileService.validate(turnstile, ip)) 400 - "Invalid captcha"

        // GeoIP check to infer country
        val country = geoIP.getCountry(ip)

        // Create user
        val u = async { AquaNetUser(
            username = validator.checkUsername(username),
            email = validator.checkEmail(email),
            pwHash = validator.checkPwHash(password),
            regTime = millis(), lastLogin = millis(), country = country,
        ) }

        // Create a ghost card
        val card = Card().apply {
            extId = cardService.randExtID(cardExtIdStart, cardExtIdEnd)
            luid = extId.toString()
            registerTime = LocalDateTime.now()
            accessTime = registerTime
            aquaUser = u
        }
        u.ghostCard = card

        // Save the user
        async {
            userRepo.save(u)
            cardRepo.save(card)
        }

        // Send confirmation email
        emailService.sendConfirmation(u)

        return mapOf("success" to true)
    }

    @API("/login")
    suspend fun login(
        @RP email: Str, @RP password: Str, @RP turnstile: Str,
        request: HttpServletRequest
    ): Any {

        // Check captcha
        val ip = geoIP.getIP(request)
        if (!turnstileService.validate(turnstile, ip)) 400 - "Invalid captcha"

        // Treat email as email / username
        val user = async { userRepo.findByEmailIgnoreCase(email) ?: userRepo.findByUsernameIgnoreCase(email) }
            ?: (400 - "User not found")
        if (!hasher.matches(password, user.pwHash)) 400 - "Invalid password"

        // Generate JWT token
        val token = jwt.gen(user)

        // Set last login time
        async { userRepo.save(user.apply { lastLogin = millis() }) }

        return mapOf("token" to token)
    }

    @API("/confirm-email")
    suspend fun confirmEmail(@RP token: Str): Any {
        // Find the confirmation
        val confirmation = async { confirmationRepo.findByToken(token) }

        // Check if the token is valid
        if (confirmation == null) 400 - "Invalid token"

        // Check if the token is expired
        if (confirmation.createdAt.plusSeconds(60 * 60 * 24).isBefore(Instant.now())) 400 - "Token expired"

        // Confirm the email
        async { userRepo.save(confirmation.aquaNetUser.apply { emailConfirmed = true }) }

        return SUCCESS
    }

    @API("/me")
    suspend fun getUser(@RP token: Str) = jwt.auth(token) { u ->
        mapOf(
            "username" to u.username,
            "email" to u.email,
            "lastLogin" to u.lastLogin,
            "regTime" to u.regTime,
            "profileLocation" to u.profileLocation,
            "profileBio" to u.profileBio,
            "emailConfirmed" to u.emailConfirmed,
            "ghostCard" to u.ghostCard.luid,
            "cards" to u.cards.map { it.luid },
        )
    }

    @API("/setting")
    suspend fun setting(@RP token: Str, @RP key: Str, @RP value: Str) = jwt.auth(token) { u ->
        // Check if the key is a settable field
        val field = SETTING_FIELDS.find { it.name == key } ?: (400 - "Invalid setting")

        async {
            // Set the validated field
            field.setter.call(u, field.checker.call(validator, value))

            // Save the user
            userRepo.save(u)
        }

        SUCCESS
    }
}