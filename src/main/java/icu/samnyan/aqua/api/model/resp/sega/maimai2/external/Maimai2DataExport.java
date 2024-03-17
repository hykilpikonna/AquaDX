package icu.samnyan.aqua.api.model.resp.sega.maimai2.external;

import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maimai2DataExport {
    public String gameId = "SDEZ";
    public UserDetail userData;
    public UserExtend userExtend;
    public UserOption userOption;
    public List<MapEncountNpc> mapEncountNpcList;
    public List<UserAct> userActList;
    public List<UserCharacter> userCharacterList;
    public List<UserCharge> userChargeList;
    public List<UserCourse> userCourseList;
    public List<UserFavorite> userFavoriteList;
    public List<UserFriendSeasonRanking> userFriendSeasonRankingList;
    public List<UserGeneralData> userGeneralDataList;
    public List<UserItem> userItemList;
    public List<UserLoginBonus> userLoginBonusList;
    public List<UserMap> userMapList;
    public List<UserMusicDetail> userMusicDetailList;
    public List<UserPlaylog> userPlaylogList;
    public UserUdemae userUdemae;
}
