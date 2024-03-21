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
public class Maimai2DataImport {
    private String gameId;
    private ExternalUserData userData;
    private Mai2UserExtend userExtend;
    private Mai2UserOption userOption;
    private List<Mai2MapEncountNpc> mapEncountNpcList;
    private List<Mai2UserAct> userActList;
    private List<Mai2UserCharacter> userCharacterList;
    private List<Mai2UserCharge> userChargeList;
    private List<Mai2UserCourse> userCourseList;
    private List<Mai2UserFavorite> userFavoriteList;
    private List<Mai2UserFriendSeasonRanking> userFriendSeasonRankingList;
    private List<Mai2UserGeneralData> userGeneralDataList;
    private List<Mai2UserGhost> userGhostList;
    private List<Mai2UserItem> userItemList;
    private List<Mai2UserLoginBonus> userLoginBonusList;
    private List<Mai2UserMap> userMapList;
    private List<Mai2UserMusicDetail> userMusicDetailList;
    private List<Mai2UserPlaylog> userPlaylogList;
    private List<Mai2UserRate> userRateList;
    private Mai2UserUdemae userUdemae;
}
