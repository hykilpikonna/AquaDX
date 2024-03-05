package ext

import icu.samnyan.aqua.net.utils.ApiException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

typealias RP = RequestParam
typealias RB = RequestBody
typealias RH = RequestHeader
typealias API = RequestMapping
typealias Str = String
typealias Bool = Boolean

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Doc(
    val desc: String,
    val ret: String = ""
)

// Make it easier to throw a ResponseStatusException
operator fun HttpStatus.invoke(message: String? = null): Nothing = throw ApiException(value(), message ?: this.reasonPhrase)
operator fun Int.minus(message: String): Nothing {
    ApiException.log.info("> Error $this: $message")
    throw ApiException(this, message)
}

// Email validation
// https://www.baeldung.com/java-email-validation-regex
val emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$".toRegex()
fun Str.isValidEmail(): Bool = emailRegex.matches(this)

val HTTP = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

// Date and time
fun millis() = System.currentTimeMillis()
val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd")
fun LocalDate.isoDate() = format(DATE_FORMAT)
fun LocalDateTime.isoDateTime() = format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

// Encodings
fun Long.toHex(len: Int = 16): Str = "0x${this.toString(len).padStart(len, '0').uppercase()}"
fun Map<String, Any>.toUrl() = entries.joinToString("&") { (k, v) -> "$k=$v" }

// Map
operator fun <K, V> Map<K, V>.plus(map: Map<K, V>) =
    (if (this is MutableMap) this else toMutableMap()).apply { putAll(map) }
operator fun <K, V> MutableMap<K, V>.plusAssign(map: Map<K, V>) { putAll(map) }

suspend fun <T> async(block: suspend kotlinx.coroutines.CoroutineScope.() -> T): T = withContext(Dispatchers.IO) { block() }

// Paths
fun path(part1: Str, vararg parts: Str) = Path.of(part1, *parts)
fun Str.path() = Path.of(this)
operator fun Path.div(part: Str) = resolve(part)

