package icu.samnyan.aqua.net

import ext.*
import icu.samnyan.aqua.net.components.*
import icu.samnyan.aqua.net.db.*
import icu.samnyan.aqua.net.db.AquaUserServices.Companion.SETTING_FIELDS
import icu.samnyan.aqua.net.utils.SUCCESS
import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.general.service.CardService
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalDateTime

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
    val validator: AquaUserServices,
    val emailProps: EmailProperties
) {
    companion object {
        // Random long with length 9-10
        // We chose 1e9 as the start because normal cards took 0...1e9-1
        // This is because games can only take uint32 for card ID, which is at max 10 digits (4294967295)
        const val cardExtIdStart = 1e9.toLong()
        const val cardExtIdEnd = 4294967295

        val log = LoggerFactory.getLogger(UserRegistrar::class.java)
    }

    @API("/register")
    @Doc("Register a new user. This will also create a ghost card for the user and send a confirmation email.", "Success message")
    suspend fun register(
        @RP username: Str, @RP email: Str, @RP password: Str, @RP turnstile: Str,
        request: HttpServletRequest
    ): Any {
        val ip = geoIP.getIP(request)
        log.info("Net: /user/register from $ip : $username")

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
            isGhost = true
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
    @Doc("Login with email/username and password. This will also check if the email is verified and send another confirmation", "JWT token")
    suspend fun login(
        @RP email: Str, @RP password: Str, @RP turnstile: Str,
        request: HttpServletRequest
    ): Any {
        // Check captcha
        val ip = geoIP.getIP(request)
        log.info("Net: /user/login from $ip : $email")
        if (!turnstileService.validate(turnstile, ip)) 400 - "Invalid captcha"

        // Treat email as email / username
        val user = async { userRepo.findByEmailIgnoreCase(email) ?: userRepo.findByUsernameIgnoreCase(email) }
            ?: (400 - "User not found")
        if (!hasher.matches(password, user.pwHash)) 400 - "Invalid password"

        // Check if email is verified
        if (!user.emailConfirmed && emailProps.enable) {
            // Check if last confirmation email was sent within a minute
            val confirmations = async { confirmationRepo.findByAquaNetUserAuId(user.auId) }
            val lastConfirmation = confirmations.maxByOrNull { it.createdAt }

            if (lastConfirmation?.createdAt?.plusSeconds(60)?.isAfter(Instant.now()) == true) {
                400 - "Email not verified - STATE_0"
            }

            // Check if we have sent more than 3 confirmation emails in the last 24 hours
            if (confirmations.count { it.createdAt.plusSeconds(60 * 60 * 24).isAfter(Instant.now()) } > 3) {
                400 - "Email not verified - STATE_1"
            }

            // Send another confirmation email
            emailService.sendConfirmation(user)
            400 - "Email not verified - STATE_2"
        }

        // Generate JWT token
        val token = jwt.gen(user)

        // Set last login time
        async { userRepo.save(user.apply { lastLogin = millis() }) }
        log.info("> Login success: ${user.username} ${user.auId}")

        return mapOf("token" to token)
    }

    @API("/confirm-email")
    @Doc("Confirm email address with a token sent through email to the user.", "Success message")
    suspend fun confirmEmail(@RP token: Str): Any {
        log.info("Net: /user/confirm-email with token $token")

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
    @Doc("Get the information of the current logged-in user.", "User information")
    suspend fun getUser(@RP token: Str) = jwt.auth(token)

    @API("/setting")
    @Doc("Validate and set a user setting field.", "Success message")
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

    val keychipRange = 1e9.toULong()..1e10.toULong() - 1UL

    @API("/keychip")
    @Doc("Get a Keychip ID so that the user can connect to the server.", "Success message")
    suspend fun setupConnection(@RP token: Str) = jwt.auth(token) { u ->
        if (u.keychip != null) return mapOf("keychip" to u.keychip)
        log.info("Net: /user/keychip setup: ${u.auId} for ${u.username}")

        // Generate a keychip id with 10 digits (e.g. A1234567890)
        var new = "A" + keychipRange.random()
        while (async { userRepo.findByKeychip(new) != null }) new = "A" + keychipRange.random()
        async { userRepo.save(u.apply { keychip = new }) }

        mapOf("keychip" to new)
    }
}