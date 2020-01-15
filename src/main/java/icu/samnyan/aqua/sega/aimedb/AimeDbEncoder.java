package icu.samnyan.aqua.sega.aimedb;

import icu.samnyan.aqua.sega.aimedb.util.Encryption;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AimeDbEncoder extends MessageToByteEncoder {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

        if (msg instanceof ByteBuf) {
            ByteBuf resp = ((ByteBuf) msg);
            resp.writerIndex(0);
            resp.writeShortLE(0xa13e);
            resp.writeShortLE(0x3087);
            resp.setShortLE(0x0006, resp.capacity());
            ByteBuf encryptedResp = Encryption.encrypt(resp);
            ctx.writeAndFlush(encryptedResp);
        }

    }
}

