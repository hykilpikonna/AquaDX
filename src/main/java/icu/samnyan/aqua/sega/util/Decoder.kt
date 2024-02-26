package icu.samnyan.aqua.sega.util

import java.util.*
import kotlin.text.Charsets.UTF_8

object Decoder {
    /**
     * Decode the input byte array from Base64 MIME encoding and decompress the decoded byte array
     */
    fun decode(src: ByteArray, base64: Boolean, nowrap: Boolean): Map<String, String> {
        // Decode the input byte array from Base64 MIME encoding
        var bytes = src
        if (base64) bytes = Base64.getMimeDecoder().decode(bytes)

        // Decompress the decoded byte array
        val output = Compression.decompress(bytes, nowrap).toString(UTF_8).trim()

        // Split the string by '&' symbol to separate key-value pairs
        return output.split("&").associate {
            val (key, value) = it.split("=")
            key to value
        }
    }

    @JvmStatic
    fun decodeAllNet(src: ByteArray) = decode(src, base64 = true, nowrap = false)

    @JvmStatic
    fun decodeBilling(src: ByteArray) = decode(src, base64 = false, nowrap = true)
}
