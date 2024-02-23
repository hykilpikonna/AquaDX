package icu.samnyan.aqua.sega.aimedb

import icu.samnyan.aqua.sega.aimedb.exception.InvalidRequestException
import icu.samnyan.aqua.sega.aimedb.util.Encryption
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class AimeDbDecoder : ByteToMessageDecoder() {
    var length = 0
    val logger: Logger = LoggerFactory.getLogger(AimeDbDecoder::class.java)

    /**
     * Decrypt the incoming request including frame management
     * @param ctx ChannelHandlerContext
     * @param input ByteBuf in
     * @param out List<Object>
    </Object> */
    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        if (input.readableBytes() < 16) return
        if (length == 0) {
            length = getLength(input)
            logger.info("Aime Server Request Length: $length")
        }

        if (input.readableBytes() < length) return

        // Create a byte array to store the encrypted data
        val result = Encryption.decrypt(input.readBytes(length))

        val resultMap: MutableMap<String, Any> = HashMap()
        resultMap["type"] = result.getShortLE(0x04).toInt()
        resultMap["data"] = result

        logger.debug("Aime Server Request Type: " + resultMap["type"])

        out.add(resultMap)
    }

    /**
     * Get the length from request
     *
     * @param input the request
     * @return int the length of this request
     */
    private fun getLength(input: ByteBuf): Int {
        val currentPos = input.readerIndex()
        val result = Encryption.decrypt(input)

        // Check the header
        if (result.getByte(0).toInt() != 0x3e) {
            throw InvalidRequestException()
        }

        // Read the length from offset 6
        return result.getShortLE(currentPos + 6).toInt()
    }
}
