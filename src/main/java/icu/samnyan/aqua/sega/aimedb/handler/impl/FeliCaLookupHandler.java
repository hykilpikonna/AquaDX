package icu.samnyan.aqua.sega.aimedb.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler;
import icu.samnyan.aqua.sega.aimedb.util.AimeDbUtil;
import icu.samnyan.aqua.sega.aimedb.util.LogMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
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
public class FeliCaLookupHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(FeliCaLookupHandler.class);

    private final LogMapper logMapper;

    @Autowired
    public FeliCaLookupHandler(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException {
        Map<String, Object> requestMap = AimeDbUtil.getBaseInfo(msg);
        requestMap.put("type", "felica_lookup");
        requestMap.put("idm", msg.slice(0x0020, 0x0028 - 0x0020));
        requestMap.put("pmm", msg.slice(0x0028, 0x0030 - 0x0028));

        logger.info("Request: " + logMapper.write(requestMap));


        // Get the decimal represent of the hex value, same from minime
        StringBuilder accessCode = new StringBuilder(
                String.valueOf(((ByteBuf) requestMap.get("idm")).getLong(0)).replaceAll("-","") // Prevent negative overflow
        );
        while (accessCode.length() < 20) {
            accessCode.insert(0, "0");
        }


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", "felica_lookup");
        resultMap.put("status", 1);
        resultMap.put("accessCode", accessCode);

        logger.info("Response: " + logMapper.write(resultMap));

        ByteBuf respSrc = Unpooled.copiedBuffer(new byte[0x0030]);
        respSrc.setShortLE(0x0004, 0x0003);
        respSrc.setShortLE(0x0008, (int) resultMap.get("status"));
        respSrc.setBytes(0x0024, ByteBufUtil.decodeHexDump(accessCode));

        ctx.writeAndFlush(respSrc);

    }
}
