package icu.samnyan.aqua.sega.diva.handler.ingame;

import icu.samnyan.aqua.sega.diva.dao.userdata.GameSessionRepository;
import icu.samnyan.aqua.sega.diva.dao.userdata.PlayLogRepository;
import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerProfileRepository;
import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerPvRecordRepository;
import icu.samnyan.aqua.sega.diva.exception.ProfileNotFoundException;
import icu.samnyan.aqua.sega.diva.exception.SessionNotFoundException;
import icu.samnyan.aqua.sega.diva.handler.BaseHandler;
import icu.samnyan.aqua.sega.diva.model.common.*;
import icu.samnyan.aqua.sega.diva.model.request.ingame.StageResultRequest;
import icu.samnyan.aqua.sega.diva.model.response.ingame.StageResultResponse;
import icu.samnyan.aqua.sega.diva.model.userdata.GameSession;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayLog;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerPvRecord;
import icu.samnyan.aqua.sega.diva.util.DivaCalculator;
import icu.samnyan.aqua.sega.diva.util.DivaMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static icu.samnyan.aqua.sega.diva.model.common.Const.NULL_QUEST;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class StageResultHandler extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(StageResultHandler.class);

    private final GameSessionRepository gameSessionRepository;
    private final PlayerPvRecordRepository pvRecordRepository;
    private final PlayerProfileRepository profileRepository;
    private final PlayLogRepository playLogRepository;

    private final DivaCalculator divaCalculator;

    public StageResultHandler(DivaMapper mapper, GameSessionRepository gameSessionRepository, PlayerPvRecordRepository pvRecordRepository, PlayerProfileRepository profileRepository, PlayLogRepository playLogRepository, DivaCalculator divaCalculator) {
        super(mapper);
        this.gameSessionRepository = gameSessionRepository;
        this.pvRecordRepository = pvRecordRepository;
        this.profileRepository = profileRepository;
        this.playLogRepository = playLogRepository;
        this.divaCalculator = divaCalculator;
    }

    public String handle(StageResultRequest request) {
        StageResultResponse response;
        if (request.getPd_id() != -1) {
            PlayerProfile profile = profileRepository.findByPdId(request.getPd_id()).orElseThrow(ProfileNotFoundException::new);
            GameSession session = gameSessionRepository.findByPdId(profile).orElseThrow(SessionNotFoundException::new);

            // Get the last played index
            int[] pvIds = request.getStg_ply_pv_id();
            int stageIndex = session.getStageIndex();

            // Convert to play log object
            PlayLog log = getLog(request, profile, stageIndex);
            logger.debug("Stage Result Object: {}", log.toString());

            PlayerPvRecord record = pvRecordRepository.findByPdIdAndPvIdAndEditionAndDifficulty(profile, log.getPvId(), log.getEdition(), log.getDifficulty())
                    .orElseGet(() -> new PlayerPvRecord(profile, log.getPvId(), log.getEdition(), log.getDifficulty()));

            // Update pvRecord field
            record.setMaxScore(Math.max(record.getMaxScore(), log.getScore()));
            record.setMaxAttain(Math.max(record.getMaxAttain(), log.getAttainPoint()));

            if (record.getResult().getValue() < log.getClearResult().getValue()) {
                record.setResult(log.getClearResult());
            }

            String[] updateRgo = log.getRhythmGameOptions().split(",");
            String[] oldRgo = record.getRgoPlayed().split(",");
            for (int i = 0; i < updateRgo.length; i++) {
                if (updateRgo[i].equals("1")) {
                    oldRgo[i] = "1";
                }
            }
            record.setRgoPlayed(StringUtils.join(oldRgo, ","));

            session.setVp(session.getVp() + log.getVp());
            session.setLastPvId(log.getPvId());
            session.setLastUpdateTime(LocalDateTime.now());

            LevelInfo levelInfo = divaCalculator.getLevelInfo(profile);
            session.setOldLevelNumber(session.getLevelNumber());
            session.setOldLevelExp(session.getLevelExp());
            session.setLevelNumber(levelInfo.getLevelNumber());
            session.setLevelExp(levelInfo.getLevelExp());

            pvRecordRepository.save(record);
            playLogRepository.save(log);
            gameSessionRepository.save(session);
//        profileRepository.save(profile);

            String cnp_sp = StringUtils.join(request.getCr_sp(), ",");
            cnp_sp = cnp_sp.substring(cnp_sp.indexOf(",") + 1) + ",-1";
            response = new StageResultResponse(
                    request.getCmd(),
                    request.getReq_id(),
                    "ok",
                    ChallengeKind.UNDEFINED.getValue(),
                    session.getOldLevelNumber(),
                    session.getOldLevelExp(),
                    session.getLevelNumber(),
                    session.getLevelExp(),
                    profile.getLevelTitle(),
                    profile.getPlateEffectId(),
                    profile.getPlateId(),
                    session.getVp(),
                    0,
                    request.getCr_cid(),
                    request.getCr_tv(),
                    cnp_sp,
                    "xxx",
                    "-1,-1,-1,-1,-1",
                    "xxx",
                    "xxx",
                    "xxx",
                    "xxx",
                    "xxx",
                    0,
                    LocalDateTime.now(),
                    -1,
                    -1,
                    0,
                    0,
                    0,
                    -1,
                    NULL_QUEST,
                    NULL_QUEST,
                    NULL_QUEST,
                    NULL_QUEST,
                    NULL_QUEST,
                    "-1,-1,-1,-1,-1",
                    "-1,-1,-1,-1,-1",
                    "-1,-1,-1,-1,-1"
            );
        } else {
            response = new StageResultResponse(
                    request.getCmd(),
                    request.getReq_id(),
                    "ok"
            );
        }

        String resp = this.build(mapper.toMap(response));
        logger.info("Response: {}", resp);

        return resp;
    }

    private PlayLog getLog(StageResultRequest request, PlayerProfile profile, int i) {
        return new PlayLog(
                profile,
                request.getStg_ply_pv_id()[i],
                Difficulty.fromValue(request.getStg_difficulty()[i]),
                Edition.fromValue(request.getStg_edtn()[i]),
                request.getStg_scrpt_ver()[i],
                request.getStg_score()[i],
                ChallengeKind.fromValue(request.getStg_chllng_kind()[i]),
                request.getStg_chllng_result()[i],
                ClearResult.fromValue(request.getStg_clr_kind()[i]),
                request.getStg_vcld_pts()[i],
                request.getStg_cool_cnt()[i],
                request.getStg_cool_pct()[i],
                request.getStg_fine_cnt()[i],
                request.getStg_fine_pct()[i],
                request.getStg_safe_cnt()[i],
                request.getStg_safe_pct()[i],
                request.getStg_sad_cnt()[i],
                request.getStg_sad_pct()[i],
                request.getStg_wt_wg_cnt()[i],
                request.getStg_wt_wg_pct()[i],
                request.getStg_max_cmb()[i],
                request.getStg_chance_tm()[i],
                request.getStg_sm_hl()[i],
                request.getStg_atn_pnt()[i],
                request.getStg_skin_id()[i],
                request.getStg_btn_se()[i],
                request.getStg_btn_se_vol()[i],
                request.getStg_sld_se()[i],
                request.getStg_chn_sld_se()[i],
                request.getStg_sldr_tch_se()[i],
                slice(request.getStg_mdl_id(), 3, i),
                request.getStg_cpt_rslt()[i],
                request.getStg_sld_scr()[i],
                request.getStg_vcl_chg()[i],
                slice(request.getStg_c_itm_id(), 12, i),
                slice(request.getStg_rgo(), 3, i),
                request.getStg_ss_num()[i],
                request.getTime_stamp().toLocalDateTime()
//                ZonedDateTime.parse(request.getTime_stamp()).toLocalDateTime()
        );
    }

    public String slice(int[] arr, int length, int offset) {
        StringBuilder sb = new StringBuilder();

        for (int i = length * offset; i < length * (offset + 1); i++) {
            sb.append(arr[i]).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
