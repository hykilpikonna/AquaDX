package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserDataRepo;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserOptionRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.response.GetUserPreviewResp;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserOption;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserPreviewHandler")
public class GetUserPreviewHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserPreviewHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserDataRepo userDataRepository;
    private final Mai2UserOptionRepo userOptionRepository;

    public GetUserPreviewHandler(BasicMapper mapper, Mai2UserDataRepo userDataRepository, Mai2UserOptionRepo userOptionRepository) {
        this.mapper = mapper;
        this.userDataRepository = userDataRepository;
        this.userOptionRepository = userOptionRepository;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        Optional<UserDetail> userDataOptional = userDataRepository.findByCardExtId(userId);

        GetUserPreviewResp resp = new GetUserPreviewResp();
        resp.setUserId(userId);
        String json;
        if (userDataOptional.isPresent() && userDataOptional.get().getUserName() != null) {
            UserDetail user = userDataOptional.get();
            Optional<UserOption> userOptionOptional = userOptionRepository.findSingleByUser_Card_ExtId(userId);
            resp.setUserName(user.getUserName());
            resp.setLogin(false);
            resp.setLastGameId(user.getLastGameId());
            resp.setLastDataVersion(user.getLastDataVersion());
            resp.setLastRomVersion(user.getLastRomVersion());
            resp.setLastLoginDate(user.getLastPlayDate());
            resp.setLastPlayDate(user.getLastPlayDate());
            resp.setPlayerRating(user.getPlayerRating());
            resp.setNameplateId(user.getPlateId());
            resp.setIconId(user.getIconId());
            resp.setTrophyId(0);
            resp.setPartnerId(user.getPartnerId());
            resp.setFrameId(user.getFrameId());
            resp.setTotalAwake(user.getTotalAwake());
            resp.setIsNetMember(user.isNetMember());
            resp.setDailyBonusDate(user.getDailyBonusDate());
            if (userOptionOptional.isPresent()) {
                UserOption option = userOptionOptional.get();
                resp.setHeadPhoneVolume(option.getHeadPhoneVolume());
                resp.setDispRate(option.getDispRate());
            }
            resp.setInherit(false);
            resp.setBanState(user.getBanState());

            json = mapper.write(resp);
        } else {
            json = "{}";
        }

        logger.info("Response: " + json);
        return json;
    }
}
