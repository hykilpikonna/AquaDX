package ext

import icu.samnyan.aqua.net.utils.ApiException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.apache.tika.Tika
import org.apache.tika.mime.MimeTypes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

typealias RP = RequestParam
typealias RB = RequestBody
typealias RH = RequestHeader
typealias API = RequestMapping
typealias Str = String
typealias Bool = Boolean

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Doc(
    val desc: String,
    val ret: String = ""
)

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class SettingField(val name: Str, val desc: Str)

// Reflection
@Suppress("UNCHECKED_CAST")
fun <T : Any> KClass<T>.vars() = memberProperties.mapNotNull { it as? KMutableProperty1<T, Any> }
@Suppress("UNCHECKED_CAST")
fun <C, T: Any> KMutableProperty1<C, T>.setCast(obj: C, value: String) = set(obj, when (returnType.classifier) {
    String::class -> value
    Int::class -> value.toInt()
    Boolean::class -> value.toBoolean()
    else -> 400 - "Invalid field type $returnType"
} as T)

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

// Global tools
@OptIn(ExperimentalSerializationApi::class)
val JSON = Json {
    ignoreUnknownKeys = true
    isLenient = true
    namingStrategy = JsonNamingStrategy.SnakeCase
}
val HTTP = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(JSON)
    }
}
val TIKA = Tika()
val MIMES = MimeTypes.getDefaultMimeTypes()

// Class resource
object Ext {
    val log = LoggerFactory.getLogger(Ext::class.java)
}
fun res(name: Str) = Ext::class.java.getResourceAsStream(name)
fun resStr(name: Str) = res(name)?.reader()?.readText()
inline fun <reified T> resJson(name: Str, warn: Boolean = true) = resStr(name)?.let {
    JSON.decodeFromString<T>(it)
} ?: run { if (warn) Ext.log.warn("Resource $name is not found"); null }

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

// Strings
operator fun Str.get(range: IntRange) = substring(range.first, (range.last + 1).coerceAtMost(length))
operator fun Str.get(start: Int, end: Int) = substring(start, end.coerceAtMost(length))
fun Str.center(width: Int, padChar: Char = ' ') = padStart((length + width) / 2, padChar).padEnd(width, padChar)

// Coroutine
suspend fun <T> async(block: suspend kotlinx.coroutines.CoroutineScope.() -> T): T = withContext(Dispatchers.IO) { block() }

// Paths
fun path(part1: Str, vararg parts: Str) = Path.of(part1, *parts)
fun Str.path() = Path.of(this)
operator fun Path.div(part: Str) = resolve(part)
fun Str.ensureEndingSlash() = if (endsWith('/')) this else "$this/"
