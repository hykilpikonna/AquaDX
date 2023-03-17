package icu.samnyan.aqua.sega.chusan.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.sega.chusan.model.userdata.*;
import icu.samnyan.aqua.sega.general.model.response.UserRecentRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserAll implements Serializable {

    @Nullable
    private List<UserData> userData;

    @Nullable
    private List<UserGameOption> userGameOption;

    @Nullable
    private List<UserCharacter> userCharacterList;

    @Nullable
    private List<UserItem> userItemList;

    @Nullable
    private List<UserMusicDetail> userMusicDetailList;

    @Nullable
    private List<UserActivity> userActivityList;

    @Nullable
    private List<UserRecentRating> userRecentRatingList;

    @Nullable
    private List<UserPlaylog> userPlaylogList;

    @Nullable
    private List<UserCharge> userChargeList;

    @Nullable
    private List<UserCourse> userCourseList;

    @Nullable
    private List<UserDuel> userDuelList;

    @Nullable
    private List<Map<String, Object>> userTeamPoint;

    @Nullable
    private List<Map<String, Object>> userRatingBaseHotList;

    @Nullable
    private List<Map<String, Object>> userRatingBaseList;

    @Nullable
    private List<Map<String, Object>> userRatingBaseNextList;

    @Nullable
    private List<Map<String, Object>> userLoginBonusList;

    @Nullable
    private List<UserMapArea> userMapAreaList;

    @Nullable
    private List<Map<String, Object>> userOverPowerList;

    @Nullable
    private List<Map<String, Object>> userNetBattlelogList;

    @Nullable
    private List<Map<String, Object>> userEmoneyList;

    @Nullable
    private List<Map<String, Object>> userNetBattleData;

    @Nullable
    @JsonProperty("isNewCharacterList")
    private String isNewCharacterList;

    @Nullable
    @JsonProperty("isNewMusicDetailList")
    private String isNewMusicDetailList;

    @Nullable
    @JsonProperty("isNewItemList")
    private String isNewItemList;

    @Nullable
    @JsonProperty("isNewCourseList")
    private String isNewCourseList;

    @Nullable
    @JsonProperty("isNewDuelList")
    private String isNewDuelList;

    @Nullable
    @JsonProperty("isNewMapAreaList")
    private String isNewMapAreaList;

}
