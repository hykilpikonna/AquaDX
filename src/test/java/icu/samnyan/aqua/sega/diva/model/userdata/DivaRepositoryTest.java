package icu.samnyan.aqua.sega.diva.model.userdata;

import icu.samnyan.aqua.sega.diva.dao.userdata.*;
import icu.samnyan.aqua.sega.diva.model.common.*;
import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static icu.samnyan.aqua.util.CardHelper.getCard;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DivaRepositoryTest {

    LocalDateTime now = LocalDateTime.now();

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;
    @Autowired
    private PlayerContestRepository playerContestRepository;
    @Autowired
    private PlayerCustomizeRepository playerCustomizeRepository;
    @Autowired
    private PlayerInventoryRepository playerInventoryRepository;
    @Autowired
    private PlayerModuleRepository playerModuleRepository;
    @Autowired
    private PlayerProfileRepository playerProfileRepository;
    @Autowired
    private PlayerPvCustomizeRepository playerPvCustomizeRepository;
    @Autowired
    private PlayerPvRecordRepository playerPvRecordRepository;
    @Autowired
    private PlayerScreenShotRepository playerScreenShotRepository;
    @Autowired
    private PlayLogRepository playLogRepository;

    @Test
    void playerProfile_SaveLoad() {
        var c = cardRepository.save(getCard());
        playerProfileRepository.save(getProfile(c));

        var p = playerProfileRepository.findByPdId((int) c.getExtId());

        assertThat(p).isPresent().hasValueSatisfying(v -> assertThat(v.getPdId()).isEqualTo(c.getExtId()));
    }

    @Test
    void gameSession_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        gameSessionRepository.save(getSession(p));

        var s = gameSessionRepository.findByPdId(p);

        assertThat(s).isPresent();
    }

    @Test
    void playerContest_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerContestRepository.saveAll(List.of(
                getContest(p, 1),
                getContest(p, 2)
        ));

        var co = playerContestRepository.findByPdIdAndContestId(p, 1);

        assertThat(co).isPresent();
    }

    @Test
    void playerCustomize_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerCustomizeRepository.saveAll(List.of(
                getCustomize(p, 1),
                getCustomize(p, 2),
                getCustomize(p, 3)
        ));

        var cL = playerCustomizeRepository.findByPdId(p);

        assertThat(cL).hasSize(3);

        var cPL = playerCustomizeRepository.findByPdId_PdId(p.getPdId(), PageRequest.of(0, 10));

        assertThat(cPL).hasSize(3);
    }

    @Test
    void playerInventory_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerInventoryRepository.saveAll(List.of(
                getInventory(p, "1", "1"),
                getInventory(p, "2", "1"),
                getInventory(p, "3", "1")
        ));

        var io = playerInventoryRepository.findByPdIdAndTypeAndValue(p, "1", "2");

        assertThat(io).isPresent();
    }

    @Test
    void playerModule_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerModuleRepository.saveAll(List.of(
                getModule(p, 1),
                getModule(p, 2)
        ));

        var mL = playerModuleRepository.findByPdId(p);

        assertThat(mL).hasSize(2);

        var mPL = playerModuleRepository.findByPdId_PdId(p.getPdId(), PageRequest.of(0, 10));

        assertThat(mPL).hasSize(2);
    }

    @Test
    void playerPvCustomize_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerPvCustomizeRepository.saveAll(List.of(
                getPvCustomize(p, 1),
                getPvCustomize(p, 2)
        ));

        var pL = playerPvCustomizeRepository.findByPdIdAndPvId(p, 1);

        assertThat(pL).isPresent();
    }

    @Test
    void playerPvRecord_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerPvRecordRepository.saveAll(List.of(
                getPvRecord(p, 1),
                getPvRecord(p, 2),
                getPvRecord(p, 3)
        ));

        var rL = playerPvRecordRepository.findByPdId(p);

        assertThat(rL).hasSize(3);
    }

    @Test
    void playerScreenShot_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playerScreenShotRepository.saveAll(List.of(
                getScreenShot(p, 1),
                getScreenShot(p, 2),
                getScreenShot(p, 3)
        ));

        var rL = playerScreenShotRepository.findByPdId_PdId(p.getPdId());

        assertThat(rL).hasSize(3);
    }

    @Test
    void playLog_SaveLoad() {
        var c = cardRepository.save(getCard());
        var p = playerProfileRepository.save(getProfile(c));
        playLogRepository.saveAll(List.of(
                getPlayLog(p, 1),
                getPlayLog(p, 2)
        ));

        var rL = playLogRepository.findByPdId_PdIdOrderByDateTimeDesc(p.getPdId(), PageRequest.of(0, 10));

        assertThat(rL).hasSize(2);

        var rPL = playLogRepository.findByPdId_PdIdOrderByDateTimeDesc(p.getPdId(), PageRequest.of(0, 10));

        assertThat(rPL).hasSize(2);
    }

    private PlayerProfile getProfile(Card c) {
        var p = new PlayerProfile();
        p.setPdId((int) c.getExtId());
        return p;
    }

    private GameSession getSession(PlayerProfile p) {
        return new GameSession(-1, 0, p, StartMode.START, now, now, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    private PlayerContest getContest(PlayerProfile p, Integer contestId) {
        return new PlayerContest(-1, p, contestId, 1, ContestBorder.SILVER, 10000, 1, now);
    }

    private PlayerCustomize getCustomize(PlayerProfile p, Integer customizeId) {
        return new PlayerCustomize(-1, p, customizeId);
    }

    private PlayerInventory getInventory(PlayerProfile p, String value, String type) {
        return new PlayerInventory(-1L, p, value, type);
    }

    private PlayerModule getModule(PlayerProfile p, Integer moduleId) {
        return new PlayerModule(-1, p, moduleId);
    }

    private PlayerPvCustomize getPvCustomize(PlayerProfile p, Integer pvId) {
        var c = new PlayerPvCustomize();
        c.setPdId(p);
        c.setPvId(pvId);
        return c;
    }

    private PlayerPvRecord getPvRecord(PlayerProfile p, Integer pvId) {
        var r = new PlayerPvRecord();
        r.setPdId(p);
        r.setPvId(pvId);
        return r;
    }

    private PlayerScreenShot getScreenShot(PlayerProfile p, Integer pvId) {
        return new PlayerScreenShot(-1, p, pvId, "img.jpg", "0,0,0", "0,0,0,0,0");
    }

    private PlayLog getPlayLog(PlayerProfile p, Integer pvId) {
        return new PlayLog(-1, p, pvId, Difficulty.EXTREME, Edition.EXTRA, 1, 10000, ChallengeKind.COMPLETED, 1, ClearResult.EXCELLENT, 100, 999, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "0,0,0", 0, 0, 0, "0", "0,0,0", 0, now);
    }
}