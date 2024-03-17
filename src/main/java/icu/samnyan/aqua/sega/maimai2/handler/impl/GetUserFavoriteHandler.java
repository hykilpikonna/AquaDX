package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserFavoriteRepo;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserFavorite;
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
@Component("Maimai2GetUserFavoriteHandler")
public class GetUserFavoriteHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserFavoriteHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserFavoriteRepo userFavoriteRepository;

    public GetUserFavoriteHandler(BasicMapper mapper, Mai2UserFavoriteRepo userFavoriteRepository) {
        this.mapper = mapper;
        this.userFavoriteRepository = userFavoriteRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        int itemKind = ((Number) request.get("itemKind")).intValue();

        List<UserFavorite> userFavoriteList = userFavoriteRepository.findByUserIdAndItemKind(userId, itemKind);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userFavoriteData", userFavoriteList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
