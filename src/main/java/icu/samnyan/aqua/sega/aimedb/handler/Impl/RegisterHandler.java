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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class RegisterHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);

    private final LogMapper logMapper;

    private final CardRepository cardRepository;

    @Autowired
    public RegisterHandler(LogMapper logMapper, CardRepository cardRepository) {
        this.logMapper = logMapper;
        this.cardRepository = cardRepository;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf msg) throws JsonProcessingException {
        Map<String, Object> requestMap = AimeDbUtil.getBaseInfo(msg);
        requestMap.put("type", "register");
        requestMap.put("luid", ByteBufUtil.hexDump(msg.slice(0x0020, 0x002a - 0x0020)));

        logger.info("Request: " + logMapper.write(requestMap));

        if (((String) requestMap.get("luid")).equals("0c1ea200000000000000")) {
            ctx.close();
            return;
        }

        if (((String) requestMap.get("luid")).equals("37deac01000000000000")) {
            ctx.close();
            return;
        }

        Card card = new Card();
        card.setLuid((String) requestMap.get("luid"));
        card.setExtId(ThreadLocalRandom.current().nextLong(99999999));
        card.setRegisterTime(LocalDateTime.now());
        card.setAccessTime(LocalDateTime.now());

        cardRepository.save(card);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", "register");
        resultMap.put("status", 1);
        resultMap.put("aimeId", card.getExtId());

        logger.info("Response: " + logMapper.write(resultMap));

        ByteBuf respSrc = Unpooled.copiedBuffer(new byte[0x0030]);
        respSrc.setShortLE(0x0004, 0x0006);
        respSrc.setShortLE(0x0008, (int) resultMap.get("status"));
        respSrc.setLongLE(0x0020, (long) resultMap.get("aimeId"));

        ctx.writeAndFlush(respSrc);

    }
}
