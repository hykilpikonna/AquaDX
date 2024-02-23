package icu.samnyan.aqua.sega.aimedb.handler.impl

import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler
import icu.samnyan.aqua.sega.aimedb.util.AimeDbUtil
import icu.samnyan.aqua.sega.aimedb.util.LogMapper
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
class FeliCaLookupHandler(val logMapper: LogMapper) : BaseHandler {
    val logger: Logger = LoggerFactory.getLogger(FeliCaLookupHandler::class.java)

    override fun handle(ctx: ChannelHandlerContext, msg: ByteBuf) {
        val requestMap = AimeDbUtil.getBaseInfo(msg)
        requestMap["type"] = "felica_lookup"
        requestMap["idm"] = msg.slice(0x0020, 0x0028 - 0x0020)
        requestMap["pmm"] = msg.slice(0x0028, 0x0030 - 0x0028)

        logger.info("Request: " + logMapper.write(requestMap))


        // Get the decimal represent of the hex value, same from minime
        val accessCode = (requestMap["idm"] as ByteBuf).getLong(0).toString()
            .replace("-", "") // Prevent negative overflow
            .padStart(20, '0')

        logger.info("Response: $accessCode")

        val respSrc = Unpooled.copiedBuffer(ByteArray(0x0030))
        respSrc.setShortLE(0x0004, 0x0003)
        respSrc.setShortLE(0x0008, 1)
        respSrc.setBytes(0x0024, ByteBufUtil.decodeHexDump(accessCode))

        ctx.writeAndFlush(respSrc)
    }
}
