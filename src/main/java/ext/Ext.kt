package ext

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

typealias RP = RequestParam
typealias RB = RequestBody
typealias RH = RequestHeader
typealias Str = String
typealias Bool = Boolean

// Make it easier to throw a ResponseStatusException
operator fun HttpStatus.invoke(message: String? = null): Nothing = throw ResponseStatusException(this, message ?: this.reasonPhrase)
operator fun Int.compareTo(message: String): Int = throw ResponseStatusException(HttpStatus.valueOf(this), message)

// Email validation
// https://www.baeldung.com/java-email-validation-regex
val emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$".toRegex()
fun Str.isValidEmail(): Bool = emailRegex.matches(this)

fun millis() = System.currentTimeMillis()

val HTTP = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}