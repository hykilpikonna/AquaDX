package icu.samnyan.aqua.net.utils

import ext.Bool
import ext.HTTP
import ext.Str
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.Serializable
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service

@Configuration
@ConfigurationProperties(prefix = "turnstile")
class TurnstileProperties {
    var enable: Bool = false

    lateinit var secret: Str

    lateinit var ipHeader: Str
}

@Service
class TurnstileService(val props: TurnstileProperties) {
    @Serializable
    data class Outcome(val success: Boolean)

    suspend fun validate(captcha: Str?, request: HttpServletRequest): Boolean {
        if (!props.enable) return true
        if (captcha == null) return false

        val ip = request.getHeader(props.ipHeader) ?: request.remoteAddr

        val outcome: Outcome = HTTP.post("https://challenges.cloudflare.com/turnstile/v0/siteverify") {
            setBody(
                FormDataContent(Parameters.build {
                    append("secret", props.secret)
                    append("response", captcha)
                    append("remoteip", ip)
                })
            )
        }.body()

        return outcome.success
    }
}

