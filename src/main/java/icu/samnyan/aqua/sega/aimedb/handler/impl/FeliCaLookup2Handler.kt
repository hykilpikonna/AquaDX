package icu.samnyan.aqua.sega.aimedb.handler.impl

import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler
import icu.samnyan.aqua.sega.aimedb.util.AimeDbUtil
import icu.samnyan.aqua.sega.aimedb.util.LogMapper
import icu.samnyan.aqua.sega.general.dao.CardRepository
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
class FeliCaLookup2Handler(
    val logMapper: LogMapper,
    val cardRepository: CardRepository
) : BaseHandler {
    val logger: Logger = LoggerFactory.getLogger(FeliCaLookup2Handler::class.java)

    override fun handle(ctx: ChannelHandlerContext, msg: ByteBuf) {
        val requestMap = AimeDbUtil.getBaseInfo(msg)
        requestMap["type"] = "felica_lookup2"
        requestMap["idm"] = msg.slice(0x0030, 0x0038 - 0x0030)
        requestMap["pmm"] = msg.slice(0x0038, 0x0040 - 0x0038)

        logger.info("Request: " + logMapper.write(requestMap))

        // Get the decimal represent of the hex value, same from minime
        val accessCode = (requestMap["idm"] as ByteBuf).getLong(0).toString()
            .replace("-", "") // Prevent negative overflow
            .padStart(20, '0')

        var aimeId: Long = -1
        val card = cardRepository.findByLuid(accessCode)
        if (card.isPresent) {
            aimeId = card.get().extId
        }

        logger.info("Response: $accessCode, $aimeId")

        val respSrc = Unpooled.copiedBuffer(ByteArray(0x0140))
        respSrc.setShortLE(0x0004, 0x0012)
        respSrc.setShortLE(0x0008, 1)
        respSrc.setLongLE(0x0020, aimeId)
        respSrc.setIntLE(0x0024, -0x1)
        respSrc.setIntLE(0x0028, -0x1)
        respSrc.setBytes(0x002c, ByteBufUtil.decodeHexDump(accessCode))
        respSrc.setShortLE(0x0037, 0x0001)

        ctx.writeAndFlush(respSrc)
    }
}
