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
@Component("OngekiGetUserRivalMusicHandler")
public class GetUserRivalMusicHandler implements BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserRivalMusicHandler.class);

    private final BasicMapper mapper;

    @Autowired
    public GetUserRivalMusicHandler(BasicMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        var userId = (long)request.get("userId");
        var rivalUserId = (int)request.get("rivalUserId");
/*      var nextIndex = (int)request.get("nextIndex");
        var maxCount = (int)request.get("maxCount"); */

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("rivalUserId", rivalUserId);
        resultMap.put("length", 0);
        resultMap.put("nextIndex", 0);
        resultMap.put("userRivalMusicList", new List[]{});

        String json = mapper.write(resultMap);

        logger.info("Response: " + json);
        return json;
    }
}
