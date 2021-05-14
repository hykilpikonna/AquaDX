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
        String playDate = (String) request.get("playDate");

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("teamId", 0);
        resultMap.put("teamRank", 0);
        resultMap.put("teamName", "");

        Map<String, Object> userTeamMap = new LinkedHashMap<>();
        userTeamMap.put("userId", userId);
        userTeamMap.put("teamId", 0);
        userTeamMap.put("orderId", 0);
        userTeamMap.put("teamPoint", 0);
        userTeamMap.put("aggrDate", playDate);

        resultMap.put("userTeamPoint", userTeamMap);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}