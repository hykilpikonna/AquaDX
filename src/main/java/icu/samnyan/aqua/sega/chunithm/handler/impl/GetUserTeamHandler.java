package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GetUserTeamHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserTeamHandler.class);

    private final StringMapper mapper;


    public GetUserTeamHandler(StringMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = (String) request.get("userId");

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("teamId", 1);
        resultMap.put("teamRank", 1);
        resultMap.put("teamName", "ParadiseLeakWhen");
        resultMap.put("userTeamPoint", 114514);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}