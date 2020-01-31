package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.chunithm.model.response.data.UserMusicListItem;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserMusicDetail;
import icu.samnyan.aqua.sega.chunithm.service.GameMusicService;
import icu.samnyan.aqua.sega.chunithm.service.UserMusicDetailService;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Response:
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetUserMusicHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserMusicHandler.class);

    private final StringMapper mapper;

    private final UserMusicDetailService userMusicDetailService;

    private final GameMusicService gameMusicService;

    @Autowired
    public GetUserMusicHandler(StringMapper mapper, UserMusicDetailService userMusicDetailService, GameMusicService gameMusicService) {
        this.mapper = mapper;
        this.userMusicDetailService = userMusicDetailService;
        this.gameMusicService = gameMusicService;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = (String) request.get("userId");
        int nextIndex = Integer.parseInt((String) request.get("nextIndex"));
        int maxCount = Integer.parseInt((String) request.get("maxCount"));
        int pageNum = nextIndex / maxCount;

        Page<UserMusicDetail> dbPage = userMusicDetailService.getByUser(userId,pageNum,maxCount);


        // Convert to result format
        // Result Map
        Map<Integer, UserMusicListItem> userMusicMap = new LinkedHashMap<>();

        dbPage.getContent().forEach(userMusicDetail -> {
            UserMusicListItem list;
            if (userMusicMap.containsKey(userMusicDetail.getMusicId())) {
                list = userMusicMap.get(userMusicDetail.getMusicId());
            } else {
                list = new UserMusicListItem(0, new ArrayList<>());
                userMusicMap.put(userMusicDetail.getMusicId(), list);
            }
            list.getUserMusicDetailList().add(userMusicDetail);
            list.setLength(list.getUserMusicDetailList().size());
        });


        long currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", userMusicMap.size());
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? -1 : currentIndex);
        resultMap.put("userMusicList", userMusicMap.values());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
