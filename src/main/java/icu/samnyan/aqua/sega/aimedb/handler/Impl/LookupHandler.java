package icu.samnyan.aqua.sega.aimedb.handler.Impl;

import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler;
import icu.samnyan.aqua.sega.aimedb.util.LogMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mifare Card lookup? idk
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class LookupHandler implements BaseHandler {

    private final LogMapper logMapper;

    @Autowired
    public LookupHandler(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) {

    }
}
