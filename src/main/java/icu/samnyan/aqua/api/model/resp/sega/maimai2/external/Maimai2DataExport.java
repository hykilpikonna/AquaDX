package icu.samnyan.aqua.api.model.resp.sega.maimai2.external;

import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
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
public class Maimai2DataExport {
    private String gameId = "SDEZ";
    private UserDetail userData;
    private UserExtend userExtend;
    private UserOption userOption;
    private List<MapEncountNpc> mapEncountNpcList;
    private List<UserAct> userActList;
    private List<UserCharacter> userCharacterList;
    private List<UserCharge> userChargeList;
    private List<UserCourse> userCourseList;
    private List<UserFavorite> userFavoriteList;
    private List<UserFriendSeasonRanking> userFriendSeasonRankingList;
    private List<UserGeneralData> userGeneralDataList;
    private List<UserGhost> userGhostList;
    private List<UserItem> userItemList;
    private List<UserLoginBonus> userLoginBonusList;
    private List<UserMap> userMapList;
    private List<UserMusicDetail> userMusicDetailList;
    private List<UserPlaylog> userPlaylogList;
    private List<UserRate> userRateList;
    private UserUdemae userUdemae;
}
