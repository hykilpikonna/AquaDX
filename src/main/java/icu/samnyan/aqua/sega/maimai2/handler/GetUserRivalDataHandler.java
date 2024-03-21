package icu.samnyan.aqua.sega.maimai2.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import icu.samnyan.aqua.sega.general.BaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.model.Mai2UserDataRepo;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRivalData;
import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserDetail;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;

@Component("Maimai2GetUserRivalDataHandler")
public class GetUserRivalDataHandler implements BaseHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(GetUserRivalDataHandler.class);

    private final StringMapper mapper;
    private final Mai2UserDataRepo userDataRepository;

    @Autowired
    public GetUserRivalDataHandler(StringMapper mapper, Mai2UserDataRepo userDataRepository) {
        this.mapper = mapper;
        this.userDataRepository = userDataRepository;
    }

    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        long rivalId = ((Number) request.get("rivalId")).intValue();

        Optional<Mai2UserDetail> detailOptional = userDataRepository.findByCardExtId(rivalId);
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
