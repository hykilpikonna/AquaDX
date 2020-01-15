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


        List<UserMusicDetail> userMusicDetailList = userMusicDetailService.getByUser(userId);


        Map<Integer, Map<Integer, UserMusicDetail>> allMusicMap = new LinkedHashMap<>();

        userMusicDetailList.forEach(userMusicDetail -> {

            int musicId = userMusicDetail.getMusicId();
            int level = userMusicDetail.getLevel();

            if (allMusicMap.containsKey(musicId)) {
                allMusicMap.get(musicId).put(level, userMusicDetail);
            } else {
                Map<Integer, UserMusicDetail> levelMap = new HashMap<>();
                levelMap.put(level, userMusicDetail);
                allMusicMap.put(musicId, levelMap);
            }
        });

        // Convert to result format
        // Result Map
        Map<Integer, UserMusicListItem> userMusicMap = new LinkedHashMap<>();

        allMusicMap.forEach((mid, lvMap) -> {
            UserMusicListItem list;
            if (userMusicMap.containsKey(mid)) {
                list = userMusicMap.get(mid);
            } else {
                list = new UserMusicListItem(0, new ArrayList<>());
                userMusicMap.put(mid, list);
            }
            list.getUserMusicDetailList().addAll(lvMap.values());
            list.setLength(lvMap.size());
        });

        List<UserMusicListItem> result = userMusicMap.values().stream().skip(nextIndex).limit(maxCount).collect(Collectors.toList());


        long currentIndex = result.size();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", result.size());
        resultMap.put("nextIndex", allMusicMap.size() < maxCount ? -1 : currentIndex);
        resultMap.put("userMusicList", result);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
