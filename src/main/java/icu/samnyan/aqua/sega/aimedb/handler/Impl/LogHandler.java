package icu.samnyan.aqua.sega.aimedb.handler.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler;
import icu.samnyan.aqua.sega.aimedb.util.AimeDbUtil;
import icu.samnyan.aqua.sega.aimedb.util.LogMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class LogHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogHandler.class);

    private final LogMapper logMapper;

    @Autowired
    public LogHandler(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException {
        Map<String, Object> requestMap = AimeDbUtil.getBaseInfo(msg);
        requestMap.put("type", "log");


        logger.info("Request: " + logMapper.write(requestMap));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", "log");
        resultMap.put("status", 1);

        logger.info("Response: " + logMapper.write(resultMap));

        ByteBuf respSrc = Unpooled.copiedBuffer(new byte[0x0020]);
        respSrc.setShortLE(0x0004, 0x000a);
        respSrc.setShortLE(0x0008, (int) resultMap.get("status"));

        ctx.writeAndFlush(respSrc);

    }
}
