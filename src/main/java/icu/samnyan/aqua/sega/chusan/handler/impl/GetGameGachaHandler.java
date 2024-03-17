package icu.samnyan.aqua.sega.chusan.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chusan.model.GameGachaRepository;
import icu.samnyan.aqua.sega.chusan.handler.BaseHandler;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameGacha;
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
@Component("ChusanGetGameGachaHandler")
public class GetGameGachaHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameGachaHandler.class);
    private final GameGachaRepository gameGachaRepository;
    private final BasicMapper mapper;

    @Autowired
    public GetGameGachaHandler(GameGachaRepository gameGachaRepository, BasicMapper mapper) {
        this.gameGachaRepository = gameGachaRepository;
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        List<GameGacha> gameGachaList = gameGachaRepository.findAll();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("length", gameGachaList.size());
        resultMap.put("gameGachaList", gameGachaList);
        resultMap.put("registIdList", List.of());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
