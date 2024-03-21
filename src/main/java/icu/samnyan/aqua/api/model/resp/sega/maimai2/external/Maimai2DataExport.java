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
    public String gameId = "SDEZ";
    public Mai2UserDetail userData;
    public Mai2UserExtend userExtend;
    public Mai2UserOption userOption;
    public List<Mai2MapEncountNpc> mapEncountNpcList;
    public List<Mai2UserAct> userActList;
    public List<Mai2UserCharacter> userCharacterList;
    public List<Mai2UserCharge> userChargeList;
    public List<Mai2UserCourse> userCourseList;
    public List<Mai2UserFavorite> userFavoriteList;
    public List<Mai2UserFriendSeasonRanking> userFriendSeasonRankingList;
    public List<Mai2UserGeneralData> userGeneralDataList;
    public List<Mai2UserItem> userItemList;
    public List<Mai2UserLoginBonus> userLoginBonusList;
    public List<Mai2UserMap> userMapList;
    public List<Mai2UserMusicDetail> userMusicDetailList;
    public List<Mai2UserPlaylog> userPlaylogList;
    public Mai2UserUdemae userUdemae;
}
