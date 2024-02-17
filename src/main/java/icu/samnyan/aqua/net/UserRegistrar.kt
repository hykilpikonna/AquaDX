package icu.samnyan.aqua.net

import ext.*
import icu.samnyan.aqua.net.db.AquaNetUser
import icu.samnyan.aqua.net.db.AquaNetUserRepo
import icu.samnyan.aqua.net.utils.TurnstileService
import io.ktor.client.request.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/user")
class UserRegistrar(
    val userRepo: AquaNetUserRepo,
    val hasher: PasswordEncoder,
    val turnstileService: TurnstileService
) {
    /**
     * Register a new user
     */
    @PostMapping("/register")
    fun register(@RP email: Str, @RP pass: Str, @RP captcha: Str?, request: HttpServletRequest) {
        request.ip

        // Check captcha
        if (!turnstileService.validate(captcha,

        // Check if email is valid
        if (!email.isValidEmail()) 400 > "Invalid email"

        // Check if user with the same email exists
        if (userRepo.existsByEmail(email)) 400 > "User already exists"

        // Validate password
        if (pass.length < 8) 400 > "Password too short"

        val u = AquaNetUser(email = email, pwHash = hasher.encode(pass), regTime = millis(), lastLogin = millis())
        userRepo.save(u)

        200 > "User created"
    }
}