package icu.samnyan.aqua.sega.ongeki.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.model.response.data.UserRecentRating;
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserPlaylogRepository;
import icu.samnyan.aqua.sega.ongeki.handler.BaseHandler;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserPlaylog;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("OngekiGetUserRecentRatingHandler")
public class GetUserRecentRatingHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserRecentRatingHandler.class);

    private final BasicMapper mapper;

    private final UserPlaylogRepository userPlaylogRepository;

    @Autowired
    public GetUserRecentRatingHandler(BasicMapper mapper, UserPlaylogRepository userPlaylogRepository) {
        this.mapper = mapper;
        this.userPlaylogRepository = userPlaylogRepository;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        Integer userId = (Integer) request.get("userId");

        Pageable page = PageRequest.of(0, 30, Sort.by(Sort.Direction.DESC, "id"));
        List<UserPlaylog> playlogList = userPlaylogRepository.findByUser_Card_ExtId(userId, page).getContent();
        List<UserRecentRating> ratingList = playlogList.stream().map(log -> new UserRecentRating(log.getMusicId(), log.getLevel(), "1000000", log.getTechScore()))
                .collect(Collectors.toList());


        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", ratingList.size());
        resultMap.put("userRecentRatingList", ratingList);

        String json = mapper.write(resultMap);

        logger.info("Response: " + json);
        return json;
    }
}
