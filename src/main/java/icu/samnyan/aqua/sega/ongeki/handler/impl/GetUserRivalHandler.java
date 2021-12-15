package icu.samnyan.aqua.sega.ongeki.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.ongeki.handler.BaseHandler;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GEEKiDoS (geek_ds@foxmail.com)
 */
@Component("OngekiGetUserRivalHandler")
public class GetUserRivalHandler implements BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserRivalHandler.class);

    private final BasicMapper mapper;

    @Autowired
    public GetUserRivalHandler(BasicMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        var userRivalId = ((Number) request.get("userId")).longValue();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userRivalId);
        resultMap.put("length", 0);
        resultMap.put("userRivalList", new List[]{});

        String json = mapper.write(resultMap);

        logger.info("Response: " + json);
        return json;
    }
}
