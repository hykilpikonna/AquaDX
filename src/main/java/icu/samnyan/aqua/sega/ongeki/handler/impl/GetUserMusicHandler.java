package icu.samnyan.aqua.sega.ongeki.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserMusicDetailRepository;
import icu.samnyan.aqua.sega.ongeki.handler.BaseHandler;
import icu.samnyan.aqua.sega.ongeki.model.response.data.UserMusicListItem;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserMusicDetail;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("OngekiGetUserMusicHandler")
public class GetUserMusicHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserMusicHandler.class);

    private final BasicMapper mapper;

    private final UserMusicDetailRepository userMusicDetailRepository;

    @Autowired
    public GetUserMusicHandler(BasicMapper mapper, UserMusicDetailRepository userMusicDetailRepository) {
        this.mapper = mapper;
        this.userMusicDetailRepository = userMusicDetailRepository;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        Integer userId = (Integer) request.get("userId");
        Integer maxCount = (Integer) request.get("maxCount");
        Integer nextIndex = (Integer) request.get("nextIndex");
        int pageNum = nextIndex / maxCount;

        Page<UserMusicDetail> dbPage = userMusicDetailRepository.findByUser_Card_ExtId(userId, PageRequest.of(pageNum, maxCount));

        Map<Integer, UserMusicListItem> userMusicMap = new LinkedHashMap<>();
        dbPage.getContent().forEach(userMusicDetail -> {
            UserMusicListItem list;
            if (userMusicMap.containsKey(userMusicDetail.getMusicId())) {
                list = userMusicMap.get(userMusicDetail.getMusicId());
            } else {
                list = new UserMusicListItem(0, new ArrayList<>());
                userMusicMap.put(userMusicDetail.getMusicId(), list);
            }
            list.getUserMusicDetailList().add(userMusicDetail);
            list.setLength(list.getUserMusicDetailList().size());
        });

        long currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("length", userMusicMap.size());
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? -1 : currentIndex);
        resultMap.put("userMusicList", userMusicMap.values());

        String json = mapper.write(resultMap);

        logger.info("Response: " + json);
        return json;
    }
}
