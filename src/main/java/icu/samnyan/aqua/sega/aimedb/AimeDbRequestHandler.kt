package icu.samnyan.aqua.sega.aimedb

import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.general.service.CardService
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import kotlin.jvm.optionals.getOrNull

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope("prototype")
class AimeDbRequestHandler(
    val cardService: CardService
): ChannelInboundHandlerAdapter() {
    val logger: Logger = LoggerFactory.getLogger(AimeDbRequestHandler::class.java)

    data class AimeBaseInfo(val gameId: String, val keychipId: String)

    fun getBaseInfo(input: ByteBuf) = AimeBaseInfo(
        gameId = input.toString(0x000a, 0x000e - 0x000a, StandardCharsets.US_ASCII),
        keychipId = input.toString(0x0014, 0x001f - 0x0014, StandardCharsets.US_ASCII)
    )

    final val handlers = mapOf<Int, (ByteBuf) -> ByteBuf?>(
        0x0001 to ::doFelicaLookup,
        0x0004 to ::doLookup,
        0x0005 to ::doRegister,
        0x0009 to ::doLog,
        0x000b to ::doCampaign,
        0x000d to ::doTouch,
        0x000f to ::doLookup2,
        0x0011 to ::doFelicaLookup2,
        0x0013 to ::doUnknown19,
        0x0064 to ::doHello,
        0x0066 to ::doGoodbye
    )

    /**
     * Handle the incoming request
     */
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        if (msg is Map<*, *>) {
            val type = msg["type"] as Int
            val data = msg["data"] as ByteBuf
            val base = getBaseInfo(data)
            val handler = handlers[type] ?: let {
                logger.error("AimeDB: Unknown request type: ${type.toString(16)}")
                ctx.flush()
                return
            }

            logger.info("AimeDB: Request $handler for game ${base.gameId}, from keychip ${base.keychipId}")

            val result = handler(data)
            if (result != null) ctx.writeAndFlush(result)
            else ctx.flush()
        }
    }

    @Deprecated("Deprecated in Netty 5") // TODO: Move this to ChannelInboundHandler
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        logger.error("AimeDB: Error", cause)
        ctx.close()
    }

    override fun channelInactive(ctx: ChannelHandlerContext) {
        super.channelInactive(ctx)
        logger.debug("AimeDB: Connection closed")
    }

    /**
     * Felica Lookup v1: Return the Felica IDm as-is
     */
    fun doFelicaLookup(msg: ByteBuf): ByteBuf {
        val idm = msg.slice(0x0020, 0x0028 - 0x0020).getLong(0)
        val pmm = msg.slice(0x0028, 0x0030 - 0x0028).getLong(0)
        logger.info("> Felica Lookup v1 ($idm, $pmm)")

        // Get the decimal represent of the hex value, same from minime
        val accessCode = idm.toString().replace("-", "").padStart(20, '0')

        logger.info("> Response: $accessCode")
        return Unpooled.copiedBuffer(ByteArray(0x0030)).apply {
            setShortLE(0x0004, 0x0003)
            setShortLE(0x0008, 1)
            setBytes(0x0024, ByteBufUtil.decodeHexDump(accessCode))
        }
    }

    /**
     * Felica Lookup v2: Look up the card in the card repository, return the External ID
     */
    fun doFelicaLookup2(msg: ByteBuf): ByteBuf {
        val idm = msg.slice(0x0020, 0x0028 - 0x0020).getLong(0)
        val pmm = msg.slice(0x0028, 0x0030 - 0x0028).getLong(0)
        logger.info("> Felica Lookup v2 ($idm, $pmm)")

        // Get the decimal represent of the hex value, same from minime
        val accessCode = idm.toString().replace("-", "").padStart(20, '0')
        val aimeId = cardService.getCardByAccessCode(accessCode).getOrNull()?.extId ?: -1

        logger.info("Response: $accessCode, $aimeId")
        return Unpooled.copiedBuffer(ByteArray(0x0140)).apply {
            setShortLE(0x0004, 0x0012)
            setShortLE(0x0008, 1)
            setLongLE(0x0020, aimeId)
            setIntLE(0x0024, -0x1) // 0xFFFFFFFF
            setIntLE(0x0028, -0x1) // 0xFFFFFFFF
            setBytes(0x002c, ByteBufUtil.decodeHexDump(accessCode))
            setShortLE(0x0037, 0x0001)
        }
    }

    /**
     * Lookup v1: Find the LUID in the database and return the External ID
     */
    fun doLookup(msg: ByteBuf): ByteBuf {
        val luid = ByteBufUtil.hexDump(msg.slice(0x0020, 0x002a - 0x0020))
        logger.info("> Lookup v1 ($luid)")

        val aimeId = cardService.getCardByAccessCode(luid).getOrNull()?.extId ?: -1

        logger.info("> Response: $aimeId")
        return Unpooled.copiedBuffer(ByteArray(0x0130)).apply {
            setShortLE(0x0004, 0x0006)
            setShortLE(0x0008, 1)
            setLongLE(0x0020, aimeId)
            setByte(0x0024, 0)
        }
    }

    fun doLookup2(msg: ByteBuf): ByteBuf {
        val luid = ByteBufUtil.hexDump(msg.slice(0x0020, 0x002a - 0x0020))
        logger.info("> Lookup v2 ($luid)")

        val aimeId = cardService.getCardByAccessCode(luid).getOrNull()?.extId ?: -1

        logger.info("Response: $aimeId")
        return Unpooled.copiedBuffer(ByteArray(0x0130)).apply {
            setShortLE(0x0004, 0x0010)
            setShortLE(0x0008, 1)
            setLongLE(0x0020, aimeId)
            setByte(0x0024, 0)
        }
    }

    /**
     * Register: Register a new card by access code
     */
    fun doRegister(msg: ByteBuf): ByteBuf {
        val luid = ByteBufUtil.hexDump(msg.slice(0x0020, 0x002a - 0x0020))
        logger.info("> Register ($luid)")

        var status = 0
        var aimeId = 0L

        if (cardService.getCardByAccessCode(luid).isEmpty) {
            val card: Card = cardService.registerByAccessCode(luid)

            status = 1
            aimeId = card.extId
        }
        else logger.warn("> Duplicated Aime Card Register detected, access code: $luid")

        logger.info("> Response: $status, $aimeId")
        return Unpooled.copiedBuffer(ByteArray(0x0030)).apply {
            setShortLE(0x0004, 0x0006)
            setShortLE(0x0008, status)
            setLongLE(0x0020, aimeId)
        }
    }

    /**
     * Log: Just log the request and return a status 1
     */
    fun doLog(msg: ByteBuf) = Unpooled.copiedBuffer(ByteArray(0x0020)).apply {
        setShortLE(0x0004, 0x000a)
        setShortLE(0x0008, 1)
    }

    /**
     * Campaign: Just return a status 1
     */
    fun doCampaign(msg: ByteBuf) = Unpooled.copiedBuffer(ByteArray(0x0200)).apply {
        setShortLE(0x0004, 0x000c)
        setShortLE(0x0008, 1)
    }

    /**
     * Touch: Just return a status 1
     */
    fun doTouch(msg: ByteBuf): ByteBuf {
        val aimeId = msg.getUnsignedIntLE(0x0020)
        logger.info("> Touch ($aimeId)")

        return Unpooled.copiedBuffer(ByteArray(0x0050)).apply {
            setShortLE(0x0004, 0x000e)
            setShortLE(0x0008, 1)
            setShortLE(0x0020, 0x006f)
            setShortLE(0x0024, 0x0001)
        }
    }

    /**
     * We don't know what this is, just return a status 1
     */
    fun doUnknown19(msg: ByteBuf) = Unpooled.copiedBuffer(ByteArray(0x0040)).apply {
        setShortLE(0x0004, 0x0014)
        setShortLE(0x0008, 1)
    }

    /**
     * Ping: Just return a status 1
     */
    fun doHello(msg: ByteBuf) = Unpooled.copiedBuffer(ByteArray(0x0020)).apply {
        setShortLE(0x0004, 0x0065)
        setShortLE(0x0008, 1)
    }
    
    fun doGoodbye(msg: ByteBuf) = null
}
