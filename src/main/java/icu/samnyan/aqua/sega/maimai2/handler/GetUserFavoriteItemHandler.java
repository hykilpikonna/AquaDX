package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserFavoriteItem;
import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserGeneralData;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserGeneralDataRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Maimai2GetUserFavoriteItemHandler")
public class GetUserFavoriteItemHandler implements BaseHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(GetUserFavoriteItemHandler.class);

    private final StringMapper mapper;
    private final Mai2UserGeneralDataRepo userGeneralDataRepository;

    @Autowired
    public GetUserFavoriteItemHandler(StringMapper mapper, Mai2UserGeneralDataRepo userGeneralDataRepository) {
        this.mapper = mapper;
        this.userGeneralDataRepository = userGeneralDataRepository;
    }

    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();
        int kind = ((Number) request.get("kind")).intValue();

        Optional<Mai2UserGeneralData> favOptional;
        List<UserFavoriteItem> items = new LinkedList<>();
        switch (kind) {
            case 1:
                favOptional = this.userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(userId,
                        "favorite_music");
                break;
            case 2:
                favOptional = this.userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(userId, "favorite_rival");
                break;
            default:
                favOptional = Optional.empty();
                break;
        }
        if (favOptional.isPresent()) {
            String val = ((Mai2UserGeneralData) favOptional.get()).getPropertyValue();
            if (StringUtils.isNotBlank(val)) {
                String[] records = val.split(",");
                int order = 0;
                for (String record : records) {
                    items.add(new UserFavoriteItem(Integer.parseInt(record), order));
                    order += 1;
                }
            }
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("kind", Integer.valueOf(kind));
        resultMap.put("length", Integer.valueOf(items.size()));
        resultMap.put("nextIndex", Integer.valueOf(0));
        resultMap.put("userFavoriteItemList", items);
        String json = this.mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
