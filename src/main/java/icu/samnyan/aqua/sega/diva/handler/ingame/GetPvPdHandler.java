package icu.samnyan.aqua.sega.diva.handler.ingame;

import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerProfileRepository;
import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerPvCustomizeRepository;
import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerPvRecordRepository;
import icu.samnyan.aqua.sega.diva.exception.ProfileNotFoundException;
import icu.samnyan.aqua.sega.diva.handler.BaseHandler;
import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import icu.samnyan.aqua.sega.diva.model.request.ingame.GetPvPdRequest;
import icu.samnyan.aqua.sega.diva.model.response.ingame.GetPvPdResponse;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerPvCustomize;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerPvRecord;
import icu.samnyan.aqua.sega.diva.util.DivaDateTimeUtil;
import icu.samnyan.aqua.sega.diva.util.DivaMapper;
import icu.samnyan.aqua.sega.util.URIEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetPvPdHandler extends BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetPvPdHandler.class);

    private final PlayerPvRecordRepository pvRecordRepository;
    private final PlayerPvCustomizeRepository pvCustomizeRepository;
    private final PlayerProfileRepository profileRepository;

    public GetPvPdHandler(DivaMapper mapper, PlayerPvRecordRepository pvRecordRepository, PlayerPvCustomizeRepository pvCustomizeRepository, PlayerProfileRepository profileRepository) {
        super(mapper);
        this.pvRecordRepository = pvRecordRepository;
        this.pvCustomizeRepository = pvCustomizeRepository;
        this.profileRepository = profileRepository;
    }

    public String handle(GetPvPdRequest request) {

        PlayerProfile profile = profileRepository.findByPdId(request.getPd_id()).orElseThrow(ProfileNotFoundException::new);
        StringBuilder pd = new StringBuilder();

        for (int pvId :
                request.getPd_pv_id_lst()) {
            if (pvId == -1) {
                pd.append("***").append(",");
            } else {
                int diff = request.getDifficulty();
                Difficulty difficulty = Difficulty.fromValue(diff);
                Optional<PlayerPvRecord> edition0optional = pvRecordRepository.findByPdIdAndPvIdAndEditionAndDifficulty(profile, pvId, Edition.ORIGINAL, difficulty);
                PlayerPvRecord edition0 = edition0optional.orElseGet(() -> new PlayerPvRecord(pvId, Edition.ORIGINAL));
                PlayerPvRecord edition1 = pvRecordRepository.findByPdIdAndPvIdAndEditionAndDifficulty(profile, pvId, Edition.EXTRA, difficulty).orElseGet(() -> new PlayerPvRecord(pvId, Edition.EXTRA));
                PlayerPvCustomize customize = pvCustomizeRepository.findByPdIdAndPvId(profile, pvId).orElseGet(() -> new PlayerPvCustomize(profile, pvId));

                String str = getString(edition0, customize) + "," + getString(edition1, customize);
//                logger.info(str);
                pd.append(URIEncoder.encode(str)).append(",");
            }
        }
        pd.deleteCharAt(pd.length() - 1);


        GetPvPdResponse response = new GetPvPdResponse(
                request.getCmd(),
                request.getReq_id(),
                "ok",
                pd.toString(),
                false,
                DivaDateTimeUtil.getString(LocalDateTime.now())
        );

        String resp = this.build(mapper.toMap(response));
        logger.info("Response: {}", resp);

        return resp;
    }


    private String getString(PlayerPvRecord record, PlayerPvCustomize customize) {
        return
                "" + record.getPvId() + "," +
                        record.getEdition().getValue() + "," +
                        record.getResult().getValue() + "," +
                        record.getMaxScore() + "," +
                        record.getMaxAttain() + "," +
                        record.getChallengeKind().getValue() + "," +
                        customize.getModule() + "," +
                        customize.getCustomize() + "," +
                        customize.getCustomizeFlag() + "," +
                        customize.getSkin() + "," +
                        customize.getButtonSe() + "," +
                        customize.getSlideSe() + "," +
                        customize.getChainSlideSe() + "," +
                        customize.getSliderTouchSe() + "," +
                        "15," +
                        "0," +
                        "0," +
                        "-1,-1," +
                        pvRecordRepository.rankByPvIdAndPdIdAndEditionAndDifficulty(record.getPvId(), record.getPdId(), record.getEdition(), record.getDifficulty()) + "," +
                        record.getRgoPurchased() + "," +
                        record.getRgoPlayed();
    }
}
