package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.chunithm.model.response.data.UserRecentRating;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserPlaylog;
import icu.samnyan.aqua.sega.chunithm.service.UserPlaylogService;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Return the recent play to calculate rating. Rating base on top 30 songs plus top 10 in recent 30 plays.
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetUserRecentRatingHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserRecentRatingHandler.class);

    private final StringMapper mapper;

    private final UserPlaylogService userPlaylogService;

    @Autowired
    public GetUserRecentRatingHandler(StringMapper mapper, UserPlaylogService userPlaylogService) {
        this.mapper = mapper;
        this.userPlaylogService = userPlaylogService;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = (String) request.get("userId");

        List<UserPlaylog> top = userPlaylogService.getRecent30Plays(userId);
        List<UserRecentRating> rating = top.stream().map(log -> new UserRecentRating(log.getMusicId(), log.getLevel(), "1030000", log.getScore()))
                .collect(Collectors.toList());

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", rating.size());
        resultMap.put("userRecentRatingList", rating);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
