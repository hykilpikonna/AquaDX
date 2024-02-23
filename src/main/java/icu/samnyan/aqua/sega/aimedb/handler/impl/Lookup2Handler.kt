package icu.samnyan.aqua.sega.aimedb.handler.impl

import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler
import icu.samnyan.aqua.sega.aimedb.util.AimeDbUtil.getBaseInfo
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
class Lookup2Handler(
    val logMapper: LogMapper,
    val cardRepository: CardRepository
) : BaseHandler {
    val logger: Logger = LoggerFactory.getLogger(Lookup2Handler::class.java)

    override fun handle(ctx: ChannelHandlerContext, msg: ByteBuf) {
        val requestMap = getBaseInfo(msg)
        requestMap["type"] = "lookup2"
        requestMap["luid"] = ByteBufUtil.hexDump(msg.slice(0x0020, 0x002a - 0x0020))

        logger.info("Request: " + logMapper.write(requestMap))

        var aimeId: Long = -1
        val card = cardRepository.findByLuid(requestMap["luid"] as String?)
        if (card.isPresent) {
            aimeId = card.get().extId
        }

        logger.info("Response: $aimeId")

        val respSrc = Unpooled.copiedBuffer(ByteArray(0x0130))
        respSrc.setShortLE(0x0004, 0x0010)
        respSrc.setShortLE(0x0008, 1)
        respSrc.setLongLE(0x0020, aimeId)
        respSrc.setByte(0x0024, 0)

        ctx.writeAndFlush(respSrc)
    }
}
