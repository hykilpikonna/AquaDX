package icu.samnyan.aqua.sega.ongeki.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.ongeki.handler.BaseHandler;
import icu.samnyan.aqua.sega.ongeki.model.response.GetGameSettingResp;
import icu.samnyan.aqua.sega.ongeki.model.response.data.GameSetting;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("OngekiGetGameSettingHandler")
public class GetGameSettingHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameSettingHandler.class);

    private final BasicMapper mapper;

    @Autowired
    public GetGameSettingHandler(BasicMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        GameSetting gameSetting = new GameSetting(
                "1.05.00",
                false,
                10,
                // So I test the game code that the game just
                // can't run over 24 hour? Patch the isAutoRebootNeeded return false instead.
                LocalDateTime.now().minusMinutes(1).minusSeconds(1),
                LocalDateTime.now().minusMinutes(1),
                false,
                300,
                300,
                300);

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
