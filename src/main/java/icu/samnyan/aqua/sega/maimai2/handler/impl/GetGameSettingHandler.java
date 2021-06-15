package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.response.GetGameSettingResp;
import icu.samnyan.aqua.sega.maimai2.model.response.data.GameSetting;
import icu.samnyan.aqua.sega.general.dao.PropertyEntryRepository;
import icu.samnyan.aqua.sega.general.model.PropertyEntry;
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

    private final PropertyEntryRepository propertyEntryRepository;

    @Autowired
    public GetGameSettingHandler(StringMapper mapper, PropertyEntryRepository propertyEntryRepository) {
        this.mapper = mapper;
        this.propertyEntryRepository = propertyEntryRepository;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        PropertyEntry start = propertyEntryRepository.findByPropertyKey("reboot_start_time")
                .orElseGet(() -> new PropertyEntry("reboot_start_time", "2020-01-01 23:59:00.0"));
        PropertyEntry end = propertyEntryRepository.findByPropertyKey("reboot_end_time")
                .orElseGet(() -> new PropertyEntry("reboot_end_time", "2020-01-01 23:59:00.0"));

        GameSetting gameSetting = new GameSetting(
                false,
                10,
                start.getPropertyValue(),
                end.getPropertyValue(),
                10000,
                0,
                "",
                "",
                "",
                "",
                0);

        GetGameSettingResp resp = new GetGameSettingResp(
                true,
                gameSetting
        );

        String json = mapper.write(resp);

        logger.info("Response: " + json);
        return json;
    }
}
