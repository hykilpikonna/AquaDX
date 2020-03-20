package icu.samnyan.aqua.sega.ongeki.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.ongeki.handler.BaseHandler;
import icu.samnyan.aqua.sega.ongeki.model.response.data.GameIdListItem;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("OngekiGetGameIdlistHandler")
public class GetGameIdlistHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameIdlistHandler.class);


    private final BasicMapper mapper;

    @Autowired
    public GetGameIdlistHandler(BasicMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        Integer type = (Integer) request.get("type");

        List<GameIdListItem> idList = new ArrayList<>();
        if(type == 1) {
            for (int i = 1; i <= 230; i++) {
                idList.add(new GameIdListItem(i,type));
            }
            for (int i = 8000; i <= 8050; i++) {
                idList.add(new GameIdListItem(i,type));
            }
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("type", type);
        resultMap.put("length", idList.size());
        resultMap.put("gameIdlistList", idList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
