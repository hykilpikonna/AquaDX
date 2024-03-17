package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserChargeRepo;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCharge;
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
@Component("Maimai2GetUserChargeHandler")
public class GetUserChargeHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserCharacterHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserChargeRepo UserChargeRepository;

    public GetUserChargeHandler(BasicMapper mapper, Mai2UserChargeRepo UserChargeRepository) {
        this.mapper = mapper;
        this.UserChargeRepository = UserChargeRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        List<UserCharge> userChargeList = UserChargeRepository.findByUser_Card_ExtId(userId);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", userChargeList.size());
        resultMap.put("userChargeList", userChargeList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
