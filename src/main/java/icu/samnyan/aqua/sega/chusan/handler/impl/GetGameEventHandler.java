package icu.samnyan.aqua.sega.chusan.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameEventRepository;
import icu.samnyan.aqua.sega.chusan.handler.BaseHandler;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameEvent;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
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
@Component("ChusanGetGameEventHandler")
public class GetGameEventHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameEventHandler.class);

    private final GameEventRepository gameEventRepository;

    private final StringMapper mapper;

    @Autowired
    public GetGameEventHandler(GameEventRepository gameEventRepository, StringMapper mapper) {
        this.gameEventRepository = gameEventRepository;
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String type = (String) request.get("type");

        List<GameEvent> gameEventList = gameEventRepository.findByEnable(true);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("type", type);
        resultMap.put("length", gameEventList.size());
        resultMap.put("gameEventList", gameEventList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
