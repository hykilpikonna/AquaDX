/*
This function is based on minime, commit e90068f32510dc4e14eef62d099272ffb6ce8ec3 by xmc
and ported by skogaby. Thanks.
*/
package icu.samnyan.aqua.sega.aimedb.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.aimedb.handler.BaseHandler;
import icu.samnyan.aqua.sega.aimedb.util.AimeDbUtil;
import icu.samnyan.aqua.sega.aimedb.util.LogMapper;
import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
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
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class FeliCaLookup2Handler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(FeliCaLookup2Handler.class);

    private final LogMapper logMapper;

    private final CardRepository cardRepository;

    @Autowired
    public FeliCaLookup2Handler(LogMapper logMapper, CardRepository cardRepository) {
        this.logMapper = logMapper;
        this.cardRepository = cardRepository;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException {
        Map<String, Object> requestMap = AimeDbUtil.getBaseInfo(msg);
        requestMap.put("type", "felica_lookup2");
        requestMap.put("idm", msg.slice(0x0030, 0x0038 - 0x0030));
        requestMap.put("pmm", msg.slice(0x0038, 0x0040 - 0x0038));

        logger.info("Request: " + logMapper.write(requestMap));

        // Get the decimal represent of the hex value, same from minime
        StringBuilder accessCode = new StringBuilder(
                String.valueOf(((ByteBuf) requestMap.get("idm")).getLong(0)).replaceAll("-","") // Prevent negative overflow
        );
        while (accessCode.length() < 20) {
            accessCode.insert(0, "0");
        }

        long aimeId = -1;
        Optional<Card> card = cardRepository.findByLuid(accessCode.toString());
        if (card.isPresent()) {
            aimeId = card.get().getExtId();
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", "felica_lookup2");
        resultMap.put("status", 1);
        resultMap.put("accessCode", accessCode);
        resultMap.put("aimeId", cardRepository.findByLuid(accessCode.toString()));

        logger.info("Response: " + logMapper.write(resultMap));

        ByteBuf respSrc = Unpooled.copiedBuffer(new byte[0x0140]);
        respSrc.setShortLE(0x0004, 0x0012);
        respSrc.setShortLE(0x0008, (int) resultMap.get("status"));
        respSrc.setLongLE(0x0020, aimeId);
        respSrc.setIntLE(0x0024, 0xFFFFFFFF);
        respSrc.setIntLE(0x0028, 0xFFFFFFFF);
        respSrc.setBytes(0x002c, ByteBufUtil.decodeHexDump(accessCode));
        respSrc.setShortLE(0x0037, 0x0001);

        ctx.writeAndFlush(respSrc);
    }
}
