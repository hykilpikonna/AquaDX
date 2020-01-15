package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.chunithm.model.response.GetGameSettingResp;
import icu.samnyan.aqua.sega.chunithm.model.response.data.GameSetting;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetGameSettingHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameSettingHandler.class);

    private final StringMapper mapper;

    @Autowired
    public GetGameSettingHandler(StringMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        GameSetting gameSetting = new GameSetting(
                1,
                false,
                10,
                0,
                0,
                false,
                999,
                999,
                999);

        GetGameSettingResp resp = new GetGameSettingResp(
                gameSetting,
                false,
                false
        );

        String json = mapper.write(resp);

        logger.info("Response: " + json);
        return json;
    }
}
