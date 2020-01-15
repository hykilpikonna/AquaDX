package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCourse;
import icu.samnyan.aqua.sega.chunithm.service.UserCourseService;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetUserCourseHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserCourseHandler.class);

    private final StringMapper mapper;

    private final UserCourseService userCourseService;

    @Autowired
    public GetUserCourseHandler(StringMapper mapper, UserCourseService userCourseService) {
        this.mapper = mapper;
        this.userCourseService = userCourseService;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = (String) request.get("userId");

        int nextIndex = Integer.parseInt((String) request.get("nextIndex"));
        int maxCount = Integer.parseInt((String) request.get("maxCount"));

        int pageNum = nextIndex / maxCount;

        Page<UserCourse> dbPage = userCourseService.getByUser(userId, pageNum, maxCount);

        long currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", dbPage.getNumberOfElements());
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? -1 : currentIndex);
        resultMap.put("userCourseList", dbPage.getContent());

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
