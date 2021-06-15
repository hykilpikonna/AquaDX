package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserRateRepository;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserUdemaeRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRating;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserRate;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserUdemae;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetUserRatingHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserRatingHandler.class);

    private final BasicMapper mapper;

    private final UserRateRepository userRateRepository;
    private final UserUdemaeRepository userUdemaeRepository;
    private final UserDataRepository userDataRepository;

    public GetUserRatingHandler(BasicMapper mapper, UserRateRepository userRateRepository, UserUdemaeRepository userUdemaeRepository,
    UserDataRepository userDataRepository) {
        this.mapper = mapper;
        this.userRateRepository = userRateRepository;
        this.userUdemaeRepository = userUdemaeRepository;
        this.userDataRepository = userDataRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        List<UserRate> userRate = userRateRepository.findByUser_Card_ExtId(userId);
        List<UserRate> emptyRating = new ArrayList<>();

        UserRating userRating = new UserRating();

        Optional<UserDetail> userDataOptional = userDataRepository.findByCard_ExtId(userId);
        if (userDataOptional.isPresent() && userDataOptional.get().getUserName() != null) {
            UserDetail user = userDataOptional.get();
            userRating.setRating(user.getPlayerRating());
        }

        // TODO: Fix these, rating is incorrect

        // Old charts (standard) = 25
        userRating.setRatingList(userRate);

        // New charts (DX) = 15
        userRating.setNewRatingList(emptyRating);

        // ??
        userRating.setNextRatingList(emptyRating);
        userRating.setNextNewRatingList(emptyRating);

        Optional<UserUdemae> optionalUserUdemae = userUdemaeRepository.findByUser_Card_ExtId(userId);
        if (optionalUserUdemae.isPresent()) {
            UserUdemae userUdemae = optionalUserUdemae.get();
            userRating.setUdemae(userUdemae);
        } else {
            userRating.setUdemae(new UserUdemae());
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userRating", userRating);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
