package icu.samnyan.aqua.sega.diva.handler.ingame;

import icu.samnyan.aqua.sega.diva.dao.userdata.GameSessionRepository;
import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerProfileRepository;
import icu.samnyan.aqua.sega.diva.exception.ProfileNotFoundException;
import icu.samnyan.aqua.sega.diva.exception.SessionNotFoundException;
import icu.samnyan.aqua.sega.diva.handler.BaseHandler;
import icu.samnyan.aqua.sega.diva.model.request.ingame.StageStartRequest;
import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import icu.samnyan.aqua.sega.diva.model.userdata.GameSession;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import icu.samnyan.aqua.sega.diva.util.DivaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class StageStartHandler extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(StageResultHandler.class);

    private final GameSessionRepository gameSessionRepository;
    private final PlayerProfileRepository profileRepository;

    public StageStartHandler(DivaMapper mapper, GameSessionRepository gameSessionRepository, PlayerProfileRepository profileRepository) {
        super(mapper);
        this.gameSessionRepository = gameSessionRepository;
        this.profileRepository = profileRepository;
    }

    public String handle(StageStartRequest request) {
        if (request.getPd_id() != -1) {
            PlayerProfile profile = profileRepository.findByPdId(request.getPd_id()).orElseThrow(ProfileNotFoundException::new);
            GameSession session = gameSessionRepository.findByPdId(profile).orElseThrow(SessionNotFoundException::new);

            if(session.getStageResultIndex() <= session.getStageIndex()) {
                session.setStageIndex(session.getStageIndex() + 1);
                gameSessionRepository.save(session);
            } else {
                logger.warn("Stage index is greater than stage result index. Maybe due to duplicated request.");
            }
        }

        BaseResponse response = new BaseResponse(
                request.getCmd(),
                request.getReq_id(),
                "ok");

        String resp = this.build(mapper.toMap(response));
        logger.info("Response: {}", resp);

        return resp;
    }
}
