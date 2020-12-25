package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.*;
import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static icu.samnyan.aqua.util.CardHelper.getCard;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChuniRepositoryTest {

    LocalDateTime now = LocalDateTime.now();

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;
    @Autowired
    private UserCharacterRepository userCharacterRepository;
    @Autowired
    private UserChargeRepository userChargeRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private UserDataExRepository userDataExRepository;
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private UserDuelRepository userDuelRepository;
    @Autowired
    private UserGameOptionExRepository userGameOptionExRepository;
    @Autowired
    private UserGameOptionRepository userGameOptionRepository;
    @Autowired
    private UserGeneralDataRepository userGeneralDataRepository;
    @Autowired
    private UserItemRepository userItemRepository;
    @Autowired
    private UserMapRepository userMapRepository;
    @Autowired
    private UserMusicDetailRepository userMusicDetailRepository;
    @Autowired
    private UserPlaylogRepository userPlaylogRepository;

    @Test
    void userData_SaveLoad() {
        var c = cardRepository.save(getCard());
        userDataRepository.save(getUser(c));

        var u = userDataRepository.findByCard_ExtId(114514L);

        assertThat(u).isPresent().hasValueSatisfying(i -> assertThat(i.getUserName()).isEqualTo("Hello"));
    }

    @Test
    void userActivity_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var aL = userActivityRepository.saveAll(List.of(
                getActivity(u, 1),
                getActivity(u, 2),
                getActivity(u, 10010)
        ));

        var aRL = userActivityRepository.findAllByUser_Card_ExtId(114514L);

        assertThat(aRL).hasSize(3);
    }

    @Test
    void userCharacter_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var cL = userCharacterRepository.saveAll(List.of(
                getCharacter(u, 10011),
                getCharacter(u, 10012),
                getCharacter(u, 10013)
        ));

        var cRL = userCharacterRepository.findByUser_Card_ExtId(114514L);

        assertThat(cRL).hasSize(3);
    }

    @Test
    void userCharge_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var cL = userChargeRepository.saveAll(List.of(
                getCharge(u, 10011)
        ));

        var cRL = userChargeRepository.findByUser_Card_ExtId(114514L);

        assertThat(cRL).hasSize(1);
    }

    @Test
    void userCourse_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var cL = userCourseRepository.saveAll(List.of(
                getCourse(u, 10011),
                getCourse(u, 10012)
        ));

        var cRL = userCourseRepository.findByUser_Card_ExtId(114514L);

        assertThat(cRL).hasSize(2);
    }

    @Test
    void userDuel_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var dL = userDuelRepository.saveAll(List.of(
                getDuel(u, 10011),
                getDuel(u, 10012)
        ));

        var dRL = userDuelRepository.findByUser_Card_ExtId(114514L);

        assertThat(dRL).hasSize(2);
    }

    @Test
    void userDataEx_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var d = userDataExRepository.save(getDataEx(u));

        var dR = userDataExRepository.findByUser_Card_ExtId(114514L);

        assertThat(dR).isPresent();
    }

    @Test
    void userGameOptionEx_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var g = userGameOptionExRepository.save(getGameOptionEx(u));

        var gR = userGameOptionExRepository.findByUser_Card_ExtId(114514L);

        assertThat(gR).isPresent();
    }

    @Test
    void userGameOption_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var g = userGameOptionRepository.save(getGameOption(u));

        var gR = userGameOptionRepository.findByUser_Card_ExtId(114514L);

        assertThat(gR).isPresent();
    }

    @Test
    void userGeneralData_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var g = userGeneralDataRepository.saveAll(List.of(
                getGeneralData(u, "RATING", "VALUE1"),
                getGeneralData(u, "RECENT_RATING", "VALUE2")
        ));

        var gR = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(114514L, "RATING");

        assertThat(gR).isPresent().hasValueSatisfying(i -> assertThat(i.getPropertyValue()).isEqualTo("VALUE1"));
    }

    @Test
    void userItem_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var iL = userItemRepository.saveAll(List.of(
                getUserItem(u, 1),
                getUserItem(u, 2)
        ));

        var iRL = userItemRepository.findAllByUser_Card_ExtId(114514L);

        assertThat(iRL).hasSize(2);
    }

    @Test
    void userMap_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var mL = userMapRepository.saveAll(List.of(
                getUserMap(u, 1),
                getUserMap(u, 2)
        ));

        var mRL = userMapRepository.findAllByUser_Card_ExtId(114514L);

        assertThat(mRL).hasSize(2);
    }

    @Test
    void userMusicDetail_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var mL = userMusicDetailRepository.saveAll(List.of(
                getUserMusicDetail(u, 1),
                getUserMusicDetail(u, 2)
        ));

        var mRL = userMusicDetailRepository.findByUser_Card_ExtId(114514L);

        assertThat(mRL).hasSize(2);
    }

    @Test
    void userPlaylog_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        var pL = userPlaylogRepository.saveAll(List.of(
                getUserPlaylog(u, 1),
                getUserPlaylog(u, 2)
        ));

        var pRL = userPlaylogRepository.findByUser_Card_ExtId(114514L);

        assertThat(pRL).hasSize(2);
    }

    private UserData getUser(Card card) {
        return new UserData(1,
                card,
                "Hello",
                now,
                false,
                "",
                10,
                0,
                "",
                10,
                10,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                now,
                1,
                false,
                "SDBT",
                "1.00.00",
                "1.00.00",
                now,
                "SDBT",
                "1.00.00",
                "1.00.00",
                now,
                1,
                "",
                "",
                "",
                "",
                ""
        );
    }

    private UserActivity getActivity(UserData u, Integer activityId) {
        return new UserActivity(-1, u, 1, activityId, 0, 0, 0, 0, 0);
    }

    private UserCharacter getCharacter(UserData u, Integer characterId) {
        return new UserCharacter(-1, u, characterId, 1, 1, 1, 1, true, false, 0, 0);
    }

    private UserCharge getCharge(UserData u, Integer chargeId) {
        return new UserCharge(-1, u, chargeId, 1, now, now, 0, 0, now);
    }

    private UserCourse getCourse(UserData u, Integer courseId) {
        return new UserCourse(-1, u, courseId, 1, 1, 1, true, true, true, 1, 1, now, 1, 1, 1, 1, true);
    }

    private UserDuel getDuel(UserData u, Integer duelId) {
        return new UserDuel(-1, u, duelId, 1, 1, true, now, 1, 1, 1, 1);
    }

    private UserDataEx getDataEx(UserData u) {
        return new UserDataEx(-1, u, "", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "", "", "", "", "", 0L, 0L, 0L, 0L, 0L);
    }

    private UserGameOptionEx getGameOptionEx(UserData u) {
        return new UserGameOptionEx(-1, u, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    private UserGameOption getGameOption(UserData u) {
        return new UserGameOption(-1, u, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    private UserGeneralData getGeneralData(UserData u, String key, String value) {
        return new UserGeneralData(-1, u, key, value);
    }

    private UserItem getUserItem(UserData u, Integer itemId) {
        return new UserItem(-1, u, 1, itemId, 1, true);
    }

    private UserMap getUserMap(UserData u, Integer mapId) {
        return new UserMap(-1, u, mapId, 1, true, 1, 1, 1, 1, 1, true);
    }

    private UserMusicDetail getUserMusicDetail(UserData u, Integer musicId) {
        return new UserMusicDetail(-1, u, musicId, 1, 1, 1, 1, 1, 1, 1, 1, true, true, true, 1, 1, 1, true);
    }

    private UserPlaylog getUserPlaylog(UserData u, Integer musicId) {
        return new UserPlaylog(-1, u, 0, 0, 0, now, now, musicId, 1, 1, 1, 1, 1, "", "", "", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, true, true, 1, true, true, false, 1, 1, 1, true, 1, 1, "", false);
    }
}