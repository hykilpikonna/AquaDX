package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.UserCourseRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCourse;
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
@Component("Maimai2GetUserCourseHandler")
public class GetUserCourseHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserMusicHandler.class);

    private final BasicMapper mapper;

    private final UserCourseRepository userCourseRepository;

    public GetUserCourseHandler(BasicMapper mapper, UserCourseRepository userCourseRepository) {
        this.mapper = mapper;
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        int nextIndexVal = ((Number) request.get("nextIndex")).intValue();

        //TODO: find what game actually wants. Stub value 10 used for now.
        int maxCount = 10;

        int pageNum = nextIndexVal / maxCount;

        Page<UserCourse> dbPage = userCourseRepository.findByUser_Card_ExtId(userId, PageRequest.of(pageNum, maxCount));

        long currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? 0 : currentIndex);
        
        resultMap.put("userCourseList", dbPage.getContent());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
