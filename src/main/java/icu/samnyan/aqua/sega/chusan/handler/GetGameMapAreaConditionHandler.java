package icu.samnyan.aqua.sega.chusan.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chusan.handler.BaseHandler;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component("ChusanGetGameMapAreaConditionHandler")
public class GetGameMapAreaConditionHandler implements BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetGameMapAreaConditionHandler.class);

    private final StringMapper mapper;

    @Autowired
    public GetGameMapAreaConditionHandler(StringMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        logger.info("MapAreaCondition Dummy Handler");

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("mapAreaConditionList", new LinkedHashMap<>());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
