package ext

import kotlin.random.Random
import kotlin.random.nextInt

const val CLIENT_ID = "A1234567890"
const val FTK = "test"
const val HOST = "http://localhost"
val ACCESS_CODE = "9900" + (1..16).map { Random.nextInt(0..9) }.joinToString("")

inline fun <reified T> String.json() = try {
    JACKSON.readValue(this, T::class.java)
}
catch (e: Exception) {
    println("Failed to parse JSON: $this")
    throw e
}

fun String.jsonMap(): Map<String, Any?> = json()
fun String.jsonArray(): List<Map<String, Any?>> = json()