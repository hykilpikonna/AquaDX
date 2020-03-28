package icu.samnyan.aqua.sega.aimedb.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GoodbyeHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GoodbyeHandler.class);

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("type", "goodbye");


        logger.info("Request: " + new ObjectMapper().writeValueAsString(requestMap));
        ctx.flush();
    }
}
