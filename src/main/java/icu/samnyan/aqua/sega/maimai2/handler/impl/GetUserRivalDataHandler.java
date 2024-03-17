package icu.samnyan.aqua.sega.maimai2.handler.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.model.UserDataRepository;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRivalData;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;

@Component("Maimai2GetUserRivalDataHandler")
public class GetUserRivalDataHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(icu.samnyan.aqua.sega.maimai2.handler.impl.GetUserRivalDataHandler.class);

    private final StringMapper mapper;
    private final UserDataRepository userDataRepository;

    @Autowired
    public GetUserRivalDataHandler(StringMapper mapper, UserDataRepository userDataRepository) {
        this.mapper = mapper;
        this.userDataRepository = userDataRepository;
    }

    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        long rivalId = ((Number) request.get("rivalId")).intValue();

        Optional<UserDetail> detailOptional = userDataRepository.findByCardExtId(rivalId);
        UserRivalData rivalData;
        if (detailOptional.isPresent()) {
            rivalData = new UserRivalData(rivalId, detailOptional.get().getUserName());
        } else {
            rivalData = new UserRivalData(rivalId, "");
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userRivalData", rivalData);

        String json = this.mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
