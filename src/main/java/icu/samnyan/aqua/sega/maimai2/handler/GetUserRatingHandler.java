package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.model.Mai2UserDataRepo;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserGeneralDataRepo;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserUdemaeRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRating;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserGeneralData;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserRate;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserUdemae;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserRatingHandler")
public class GetUserRatingHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserRatingHandler.class);

    private final BasicMapper mapper;
    private final Mai2UserGeneralDataRepo userGeneralDataRepository;
    private final Mai2UserUdemaeRepo userUdemaeRepository;
    private final Mai2UserDataRepo userDataRepository;

    public GetUserRatingHandler(BasicMapper mapper, Mai2UserUdemaeRepo userUdemaeRepository, Mai2UserGeneralDataRepo userGeneralDataRepository,
                                Mai2UserDataRepo userDataRepository) {
        this.mapper = mapper;
        this.userGeneralDataRepository = userGeneralDataRepository;
        this.userUdemaeRepository = userUdemaeRepository;
        this.userDataRepository = userDataRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        Optional<UserGeneralData> recentOptional = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(userId, "recent_rating");
        Optional<UserGeneralData> recentNewOptional = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(userId, "recent_rating_new");
        Optional<UserGeneralData> recentNextOptional = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(userId, "recent_rating_next");
        Optional<UserGeneralData> recentNextNewOptional = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(userId, "recent_rating_next_new");
        List<UserRate> emptyRating = new ArrayList<>();

        UserRating userRating = new UserRating();

        Optional<UserDetail> userDataOptional = userDataRepository.findByCardExtId(userId);
        if (userDataOptional.isPresent() && userDataOptional.get().getUserName() != null) {
            UserDetail user = userDataOptional.get();
            userRating.setRating(user.getPlayerRating());
        }

        // Old charts (standard) = 25
        if (recentOptional.isPresent()) {
            String val = recentOptional.get().getPropertyValue();
            userRating.setRatingList(loadRateData(val));
        } else {
            userRating.setRatingList(emptyRating);
        }

        // New charts (DX) = 15
        if (recentNewOptional.isPresent()) {
            String val = recentNewOptional.get().getPropertyValue();
            userRating.setNewRatingList(loadRateData(val));
        } else {
            userRating.setNewRatingList(emptyRating);
        }
        
        // ??
        if (recentNextOptional.isPresent()) {
            String val = recentNextOptional.get().getPropertyValue();
            userRating.setNextRatingList(loadRateData(val));
        } else {
            userRating.setNextRatingList(emptyRating);
        }

        if (recentNextNewOptional.isPresent()) {
            String val = recentNextNewOptional.get().getPropertyValue();
            userRating.setNextNewRatingList(loadRateData(val));
        } else {
            userRating.setNextNewRatingList(emptyRating);
        }

        Optional<UserUdemae> optionalUserUdemae = userUdemaeRepository.findSingleByUser_Card_ExtId(userId);
        if (optionalUserUdemae.isPresent()) {
            UserUdemae userUdemae = optionalUserUdemae.get();
            userRating.setUdemae(userUdemae);
        } else {
            userRating.setUdemae(new UserUdemae());
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userRating", userRating);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }

    private List<UserRate> loadRateData(String val) {
        List<UserRate> rateList = new LinkedList<>();

        if(StringUtils.isNotBlank(val) && val.contains(",")) {
            String[] records = val.split(",");
            for (String record :
                    records) {
                String[] value = record.split(":");
                rateList.add(new UserRate(
                        Integer.parseInt(value[0]),
                        Integer.parseInt(value[1]),
                        Integer.parseInt(value[2]),
                        Integer.parseInt(value[3])
                ));
            }
        }

        return rateList;
    }

}
