package ext

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
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
import org.springframework.web.bind.annotation.*
import java.lang.reflect.Field
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

typealias RP = RequestParam
typealias RB = RequestBody
typealias RH = RequestHeader
typealias PV = PathVariable
typealias API = RequestMapping
typealias Str = String
typealias Bool = Boolean
typealias JavaSerializable = java.io.Serializable

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
fun <T : Any> KClass<T>.varsMap() = vars().associateBy { it.name }
fun <T : Any> KClass<T>.getters() = java.methods.filter { it.name.startsWith("get") }
fun <T : Any> KClass<T>.gettersMap() = getters().associateBy { it.name.removePrefix("get").decapitalize() }
infix fun KCallable<*>.returns(type: KClass<*>) = returnType.jvmErasure.isSubclassOf(type)
@Suppress("UNCHECKED_CAST")
fun <C, T: Any> KMutableProperty1<C, T>.setCast(obj: C, value: String) = set(obj, when (returnType.classifier) {
    String::class -> value
    Int::class -> value.toInt()
    Boolean::class -> value.toBoolean()
    else -> 400 - "Invalid field type $returnType"
} as T)
inline fun <reified T: Any> Field.gets(obj: Any) = get(obj) as T

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

// JSON
val ACCEPTABLE_FALSE = setOf("0", "false", "no", "off", "False", "None", "null")
val ACCEPTABLE_TRUE = setOf("1", "true", "yes", "on", "True")
@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
val JACKSON = ObjectMapper().apply {
    findAndRegisterModules()
    registerModule(SimpleModule().addDeserializer(Boolean::class.java, object : JsonDeserializer<Boolean>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext) = when(parser.text) {
            in ACCEPTABLE_FALSE -> false
            in ACCEPTABLE_TRUE -> true
            else -> 400 - "Invalid boolean value ${parser.text}"
        }
    }).addDeserializer(List::class.java, object : JsonDeserializer<List<Integer>>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext) =
            try {
                val text = parser.text.trim('[', ']')
                if (text.isEmpty()) emptyList()
                else text.split(',').map { it.trim().toInt() } as List<Integer>
            } catch (e: Exception) {
                400 - "Invalid list value ${parser.text}: $e" }
    }).addDeserializer(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext) =
            parser.text.asDateTime() ?: (400 - "Invalid date time value ${parser.text}")
    }))
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}
inline fun <reified T> Str.parseJson() = JACKSON.readValue(this, T::class.java)
fun <T> T.toJson() = JACKSON.writeValueAsString(this)
@OptIn(ExperimentalSerializationApi::class)
val JSON = Json {
    ignoreUnknownKeys = true
    isLenient = true
    namingStrategy = JsonNamingStrategy.SnakeCase
}
// Global Tools
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

val ALT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
fun Str.asDateTime() = try { LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }
catch (e: Exception) { try { LocalDateTime.parse(this, ALT_DATETIME_FORMAT) }
catch (e: Exception) { null } }

// Encodings
fun Long.toHex(len: Int = 16): Str = "0x${this.toString(len).padStart(len, '0').uppercase()}"
fun Map<String, Any>.toUrl() = entries.joinToString("&") { (k, v) -> "$k=$v" }

// Collections
operator fun <K, V> Map<K, V>.plus(map: Map<K, V>) =
    (if (this is MutableMap) this else toMutableMap()).apply { putAll(map) }
operator fun <K, V> MutableMap<K, V>.plusAssign(map: Map<K, V>) { putAll(map) }
fun <T> MutableList<T>.popAll(list: List<T>) = list.also { removeAll(it) }
fun <T> MutableList<T>.popAll(vararg items: T) = popAll(items.toList())
inline fun <T> Iterable<T>.mapApply(block: T.() -> Unit) = map { it.apply(block) }

// Optionals
operator fun <T> Optional<T>.invoke(): T? = orElse(null)

// Strings
operator fun Str.get(range: IntRange) = substring(range.first, (range.last + 1).coerceAtMost(length))
operator fun Str.get(start: Int, end: Int) = substring(start, end.coerceAtMost(length))
fun Str.center(width: Int, padChar: Char = ' ') = padStart((length + width) / 2, padChar).padEnd(width, padChar)
fun Str.splitLines() = replace("\r\n", "\n").split('\n')

// Coroutine
suspend fun <T> async(block: suspend kotlinx.coroutines.CoroutineScope.() -> T): T = withContext(Dispatchers.IO) { block() }

// Paths
fun path(part1: Str, vararg parts: Str) = Path.of(part1, *parts)
fun Str.path() = Path.of(this)
operator fun Path.div(part: Str) = resolve(part)
fun Str.ensureEndingSlash() = if (endsWith('/')) this else "$this/"
