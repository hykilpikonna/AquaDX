package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.model.GameSellingCardRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameSellingCard;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2CMGetSellingCardHandler")
public class CMGetSellingCardHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(CMGetSellingCardHandler.class);
    private final GameSellingCardRepository gameSellingCardRepository;
    private final BasicMapper mapper;

    @Autowired
    public CMGetSellingCardHandler(GameSellingCardRepository gameSellingCardRepository, BasicMapper mapper) {
        this.gameSellingCardRepository = gameSellingCardRepository;
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        List<GameSellingCard> sellingCardList = gameSellingCardRepository.findAll();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("length", sellingCardList.size());
        resultMap.put("sellingCardList", sellingCardList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
