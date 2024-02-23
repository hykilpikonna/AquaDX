package icu.samnyan.aqua.sega.aimedb

import icu.samnyan.aqua.sega.util.ByteBufUtil
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled.copiedBuffer
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
object AimeDbEncryption {
    val KEY = SecretKeySpec("Copyright(C)SEGA".toByteArray(StandardCharsets.UTF_8), "AES")
    val enc = Cipher.getInstance("AES/ECB/NoPadding").apply { init(Cipher.ENCRYPT_MODE, KEY) }
    val dec = Cipher.getInstance("AES/ECB/NoPadding").apply { init(Cipher.DECRYPT_MODE, KEY) }

    fun decrypt(src: ByteBuf) = copiedBuffer(dec.doFinal(ByteBufUtil.toBytes(src)))

    fun encrypt(src: ByteBuf) = copiedBuffer(enc.doFinal(ByteBufUtil.toAllBytes(src)))
}
