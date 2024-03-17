package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.model.Mai2UserCardRepo;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCard;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserCardHandler")
public class GetUserCardHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserDataHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserCardRepo userCardRepository;

    public GetUserCardHandler(Mai2UserCardRepo userCardRepository, BasicMapper mapper) {
        this.mapper = mapper;
        this.userCardRepository = userCardRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        int nextIndex = ((Number) request.get("nextIndex")).intValue();
        int maxCount = ((Number) request.get("maxCount")).intValue();

        int pageNum = nextIndex / maxCount;

        Page<UserCard> dbPage = userCardRepository.findByUser_Card_ExtId(userId, PageRequest.of(pageNum, maxCount));

        int currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? 0 : currentIndex);
        resultMap.put("userCardList", dbPage.getContent());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
