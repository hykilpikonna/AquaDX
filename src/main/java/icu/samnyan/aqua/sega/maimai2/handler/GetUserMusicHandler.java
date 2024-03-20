package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserMusicDetailRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserMusic;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserMusicDetail;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserMusicHandler")
public class GetUserMusicHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserMusicHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserMusicDetailRepo userMusicDetailRepository;

    public GetUserMusicHandler(BasicMapper mapper, Mai2UserMusicDetailRepo userMusicDetailRepository) {
        this.mapper = mapper;
        this.userMusicDetailRepository = userMusicDetailRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        int nextIndexVal = ((Number) request.get("nextIndex")).intValue();
        int maxCount = ((Number) request.get("maxCount")).intValue();

        int pageNum = nextIndexVal / maxCount;

        Page<UserMusicDetail> dbPage = userMusicDetailRepository.findByUser_Card_ExtId(userId, PageRequest.of(pageNum, maxCount));

        long currentIndex = maxCount * pageNum + dbPage.getNumberOfElements();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("nextIndex", dbPage.getNumberOfElements() < maxCount ? 0 : currentIndex);

        UserMusic userMusic = new UserMusic();
        userMusic.setUserMusicDetailList(dbPage.getContent());

        List<UserMusic> userMusicList = new ArrayList<>();
        userMusicList.add(userMusic);
        
        resultMap.put("userMusicList", userMusicList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
