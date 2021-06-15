package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserExtendRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserExtend;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetUserExtendHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserExtendHandler.class);

    private final BasicMapper mapper;

    private final UserExtendRepository userExtendRepository;

    public GetUserExtendHandler(BasicMapper mapper, UserExtendRepository userExtendRepository) {
        this.mapper = mapper;
        this.userExtendRepository = userExtendRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        UserExtend userExtend = userExtendRepository.findByUser_Card_ExtId(userId).orElseThrow();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userExtend", userExtend);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
