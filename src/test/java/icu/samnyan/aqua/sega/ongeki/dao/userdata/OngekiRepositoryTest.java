package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.ongeki.model.userdata.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static icu.samnyan.aqua.util.CardHelper.getCard;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OngekiRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserActivityRepository userActivityRepository;
    @Autowired
    private UserBossRepository userBossRepository;
    @Autowired
    private UserCardRepository userCardRepository;
    @Autowired
    private UserChapterRepository userChapterRepository;
    @Autowired
    private UserCharacterRepository userCharacterRepository;
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private UserDeckRepository userDeckRepository;
    @Autowired
    private UserEventPointRepository userEventPointRepository;
    @Autowired
    private UserGeneralDataRepository userGeneralDataRepository;
    @Autowired
    private UserItemRepository userItemRepository;
    @Autowired
    private UserLoginBonusRepository userLoginBonusRepository;
    @Autowired
    private UserMissionPointRepository userMissionPointRepository;
    @Autowired
    private UserMusicDetailRepository userMusicDetailRepository;
    @Autowired
    private UserMusicItemRepository userMusicItemRepository;
    @Autowired
    private UserOptionRepository userOptionRepository;
    @Autowired
    private UserPlaylogRepository userPlaylogRepository;
    @Autowired
    private UserScenarioRepository userScenarioRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private UserTechCountRepository userTechCountRepository;
    @Autowired
    private UserTrainingRoomRepository userTrainingRoomRepository;

    @Test
    void userData_SaveLoad() {
        var c = cardRepository.save(getCard());
        userDataRepository.save(getUser(c));

        var u = userDataRepository.findByCard_ExtId(c.getExtId());

        assertThat(u).isPresent().hasValueSatisfying(i -> assertThat(i.getUserName()).isEqualTo("Hello"));
    }

    @Test
    void userActivity_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userActivityRepository.saveAll(List.of(
                getActivity(u, 1),
                getActivity(u, 2)
        ));

        var aL = userActivityRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userBoss_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userBossRepository.saveAll(List.of(
                getBoss(u, 1),
                getBoss(u, 2)
        ));

        var aL = userBossRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userCard_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userCardRepository.saveAll(List.of(
                getUserCard(u, 1),
                getUserCard(u, 2)
        ));

        var aL = userCardRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userChapter_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userChapterRepository.saveAll(List.of(
                getChapter(u, 1),
                getChapter(u, 2)
        ));

        var aL = userChapterRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userCharacter_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userCharacterRepository.saveAll(List.of(
                getCharacter(u, 1),
                getCharacter(u, 2)
        ));

        var aL = userCharacterRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userDeck_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userDeckRepository.saveAll(List.of(
                getDeck(u, 1),
                getDeck(u, 2)
        ));

        var aL = userDeckRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userEventPoint_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userEventPointRepository.saveAll(List.of(
                getEventPoint(u, 1),
                getEventPoint(u, 2)
        ));

        var aL = userEventPointRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userGeneralData_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userGeneralDataRepository.saveAll(List.of(
                getGeneralData(u, "RATING", "TEST1"),
                getGeneralData(u, "RECENT_RATING", "TEST2")
        ));

        var g = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(c.getExtId(), "RATING");

        assertThat(g).isPresent().hasValueSatisfying(v -> assertThat(v.getPropertyValue()).isEqualTo("TEST1"));
    }

    @Test
    void userItem_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userItemRepository.saveAll(List.of(
                getItem(u, 1),
                getItem(u, 2)
        ));

        var aL = userItemRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userLoginBonus_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userLoginBonusRepository.saveAll(List.of(
                getLoginBonus(u, 1),
                getLoginBonus(u, 2)
        ));

        var aL = userLoginBonusRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userMissionPoint_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userMissionPointRepository.saveAll(List.of(
                getMissionPoint(u, 1),
                getMissionPoint(u, 2)
        ));

        var aL = userMissionPointRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userMusicDetail_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userMusicDetailRepository.saveAll(List.of(
                getMusicDetail(u, 1),
                getMusicDetail(u, 2)
        ));

        var aL = userMusicDetailRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userMusicItem_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userMusicItemRepository.saveAll(List.of(
                getMusicItem(u, 1),
                getMusicItem(u, 2)
        ));

        var aL = userMusicItemRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userOption_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userOptionRepository.save(getOption(u));

        var aL = userOptionRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).isPresent();
    }

    @Test
    void userPlaylog_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userPlaylogRepository.saveAll(List.of(
                getPlaylog(u, 1),
                getPlaylog(u, 2)
        ));

        var aL = userPlaylogRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userScenario_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userScenarioRepository.saveAll(List.of(
                getScenario(u, 1),
                getScenario(u, 2)
        ));

        var aL = userScenarioRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userStory_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userStoryRepository.saveAll(List.of(
                getStory(u, 1),
                getStory(u, 2)
        ));

        var aL = userStoryRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userTechCount_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userTechCountRepository.saveAll(List.of(
                getTechCount(u, 1),
                getTechCount(u, 2)
        ));

        var aL = userTechCountRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    @Test
    void userTrainingRoom_SaveLoad() {
        var c = cardRepository.save(getCard());
        var u = userDataRepository.save(getUser(c));
        userTrainingRoomRepository.saveAll(List.of(
                getTrainingRoom(u, 1),
                getTrainingRoom(u, 2)
        ));

        var aL = userTrainingRoomRepository.findByUser_Card_ExtId(c.getExtId());

        assertThat(aL).hasSize(2);
    }

    private UserData getUser(Card c) {
        return new UserData(-1, c, "Hello", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "2020", "SDDT", "1.00.00", "1.00.00", "2020", "SDDT", "1.00.00", "1.00.00", "", "2020", 0, "0", 0, "123", 0, "A000000", 0, 0);
    }

    private UserActivity getActivity(UserData u, Integer activityId) {
        return new UserActivity(-1, u, 1, activityId, 0, 0, 0, 0, 0);
    }

    private UserBoss getBoss(UserData u, Integer musicId) {
        return new UserBoss(-1, u, musicId, 10, false);
    }

    private UserCard getUserCard(UserData u, Integer cardId) {
        return new UserCard(-1, u, cardId, 10, 10, 10, 50, 10, 1, 1, false, "2020", "2020", 1, true, "2020");
    }

    private UserChapter getChapter(UserData u, Integer chapterId) {
        return new UserChapter(-1, u, chapterId, 10, 1, 1, true, true, 1, 1);
    }

    private UserCharacter getCharacter(UserData u, Integer characterId) {
        return new UserCharacter(-1, u, characterId, 10, 1, 1, 1, "2020", false);
    }

    private UserDeck getDeck(UserData u, Integer deckId) {
        return new UserDeck(-1, u, deckId, 1, 1, 1);
    }

    private UserEventPoint getEventPoint(UserData u, Integer eventId) {
        return new UserEventPoint(-1, u, eventId, 1, false);
    }

    private UserGeneralData getGeneralData(UserData u, String key, String value) {
        return new UserGeneralData(-1, u, key, value);
    }

    private UserItem getItem(UserData u, Integer itemId) {
        return new UserItem(-1, u, 1, itemId, 1, true);
    }

    private UserLoginBonus getLoginBonus(UserData u, Integer bonusId) {
        return new UserLoginBonus(-1, u, bonusId, 1);
    }

    private UserMissionPoint getMissionPoint(UserData u, Integer eventId) {
        return new UserMissionPoint(-1, u, eventId, 1);
    }

    private UserMusicDetail getMusicDetail(UserData u, Integer musicId) {
        return new UserMusicDetail(-1, u, musicId, 1, 1, 100, 1, 100, 1, 100, 100, 100, true, true, true, false, 1, true);
    }

    private UserMusicItem getMusicItem(UserData u, Integer musicId) {
        return new UserMusicItem(-1, u, musicId, 1);
    }

    private UserOption getOption(UserData u) {
        return new UserOption(-1, u, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    private UserPlaylog getPlaylog(UserData u, Integer musicId) {
        return new UserPlaylog(-1, u, 0, 0, "", "", "", musicId, 1, 1, 1, "", 1, 1, 1, 1, "", "", "", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, true, true, true, true, true, true, 0, 0);
    }

    private UserScenario getScenario(UserData u, Integer scenarioId) {
        return new UserScenario(-1, u, scenarioId, 1);
    }

    private UserStory getStory(UserData u, Integer storyId) {
        return new UserStory(-1, u, storyId, 0, 0, 0, 0, 0);
    }

    private UserTechCount getTechCount(UserData u, Integer levelId) {
        return new UserTechCount(-1, u, levelId, 1, 1);
    }

    private UserTrainingRoom getTrainingRoom(UserData u, Integer roomId) {
        return new UserTrainingRoom(-1, u, "", roomId, 1, "");
    }
}