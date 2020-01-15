package icu.samnyan.aqua.sega.aimedb.handler.Impl;

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
public class Lookup2Handler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(Lookup2Handler.class);

    private final LogMapper logMapper;

    private final CardRepository cardRepository;

    @Autowired
    public Lookup2Handler(LogMapper logMapper, CardRepository cardRepository) {
        this.logMapper = logMapper;
        this.cardRepository = cardRepository;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException {
        Map<String, Object> requestMap = AimeDbUtil.getBaseInfo(msg);
        requestMap.put("type", "lookup2");
        requestMap.put("luid", ByteBufUtil.hexDump(msg.slice(0x0020, 0x002a - 0x0020)));

        logger.info("Request: " + logMapper.write(requestMap));

        long aimeId = -1;
        Optional<Card> card = cardRepository.findByLuid((String) requestMap.get("luid"));
        if (card.isPresent()) {
            aimeId = card.get().getExtId();
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", "lookup2");
        resultMap.put("status", 1);
        resultMap.put("aimeId", aimeId);
        resultMap.put("registerLevel", "none");

        logger.info("Response: " + logMapper.write(resultMap));

        ByteBuf respSrc = Unpooled.copiedBuffer(new byte[0x0130]);
        respSrc.setShortLE(0x0004, 0x0010);
        respSrc.setShortLE(0x0008, (int) resultMap.get("status"));
        respSrc.setLongLE(0x0020, (long) resultMap.get("aimeId"));
        respSrc.setByte(0x0024, 0);

        ctx.writeAndFlush(respSrc);
    }
}
