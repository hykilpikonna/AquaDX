package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserMapRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserMap;
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
@Component("Maimai2GetUserMapHandler")
public class GetUserMapHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserMapHandler.class);

    private final BasicMapper mapper;

    private final UserMapRepository userMapRepository;

    public GetUserMapHandler(BasicMapper mapper, UserMapRepository userMapRepository) {
        this.mapper = mapper;
        this.userMapRepository = userMapRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        int nextIndexVal = ((Number) request.get("nextIndex")).intValue();
        int maxCount = ((Number) request.get("maxCount")).intValue();

        int pageNum = nextIndexVal / maxCount;

        Page<UserMap> dbPage = userMapRepository.findByUser_Card_ExtId(userId, PageRequest.of(pageNum, maxCount));

        long currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? 0 : currentIndex);
        resultMap.put("userMapList", dbPage.getContent());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
