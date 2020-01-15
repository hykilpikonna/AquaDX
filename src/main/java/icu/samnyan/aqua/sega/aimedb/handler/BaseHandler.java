package icu.samnyan.aqua.sega.aimedb.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface BaseHandler {

    void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException;
}
