package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserActRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserActivity;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserAct;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserActivityHandler")
public class GetUserActivityHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserActivityHandler.class);

    private final BasicMapper mapper;

    private final UserActRepository userActRepository;

    public GetUserActivityHandler(BasicMapper mapper, UserActRepository userActRepository) {
        this.mapper = mapper;
        this.userActRepository = userActRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        // kind 1 = playList, kind 2 = musicList
        // maimaiDX require these two
        List<UserAct> userPlayList = userActRepository.findByUser_Card_ExtIdAndKind(userId, 1);
        List<UserAct> userMusicList = userActRepository.findByUser_Card_ExtIdAndKind(userId, 2);

        UserActivity userActivity = new UserActivity();
        userActivity.setMusicList(userMusicList);
        userActivity.setPlayList(userPlayList);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userActivity", userActivity);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
