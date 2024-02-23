package icu.samnyan.aqua.sega.aimedb

import icu.samnyan.aqua.sega.aimedb.util.Encryption
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class AimeDbEncoder : MessageToByteEncoder<Any>() {
    override fun encode(ctx: ChannelHandlerContext, msg: Any, out: ByteBuf) {
        if (msg is ByteBuf) {
            msg.writerIndex(0)
            msg.writeShortLE(0xa13e)
            msg.writeShortLE(0x3087)
            msg.setShortLE(0x0006, msg.capacity())
            val encryptedResp = Encryption.encrypt(msg)
            ctx.writeAndFlush(encryptedResp)
        }
    }
}

