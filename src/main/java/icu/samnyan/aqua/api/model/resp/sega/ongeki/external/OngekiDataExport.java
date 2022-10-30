package icu.samnyan.aqua.api.model.resp.sega.ongeki.external;

import icu.samnyan.aqua.sega.ongeki.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngekiDataExport {
    private String gameId = "SDDT";
    private UserData userData;
    private List<UserActivity> userActivityList;
    private List<UserCard> userCardList;
    private List<UserChapter> userChapterList;
    private List<UserCharacter> userCharacterList;
    private List<UserDeck> userDeckList;
    private List<UserEventPoint> userEventPointList;
    private List<UserGeneralData> userGeneralDataList;
    private List<UserItem> userItemList;
    private List<UserLoginBonus> userLoginBonusList;
    private List<UserMissionPoint> userMissionPointList;
    private List<UserMusicDetail> userMusicDetailList;
    private List<UserMusicItem> userMusicItemList;
    private UserOption userOption;
    private List<UserPlaylog> userPlaylogList;
    private List<UserStory> userStoryList;
    private List<UserTrainingRoom> userTrainingRoomList;
    private List<UserTradeItem> userTradeItemList;
    private List<UserEventMusic> userEventMusicList;
    private List<UserTechEvent> userTechEventList;
    private List<UserKop> userKopList;
    private List<UserMemoryChapter> userMemoryChapterList;
    private List<UserScenario> userScenarioList;
    private List<UserBoss> userBossList;
    private List<UserTechCount> userTechCountList;
}
