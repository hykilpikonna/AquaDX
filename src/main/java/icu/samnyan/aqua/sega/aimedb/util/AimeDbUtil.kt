package icu.samnyan.aqua.sega.aimedb.util

import io.netty.buffer.ByteBuf
import java.nio.charset.StandardCharsets

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
object AimeDbUtil {
    @JvmStatic
    fun getBaseInfo(input: ByteBuf) = mutableMapOf<String, Any>(
        "gameId" to input.toString(0x000a, 0x000e - 0x000a, StandardCharsets.US_ASCII),
        "keychipId" to input.toString(0x0014, 0x001f - 0x0014, StandardCharsets.US_ASCII)
    )
}
