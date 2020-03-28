package icu.samnyan.aqua.sega.chunithm.model.requet;

import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.sega.chunithm.model.userdata.*;
import icu.samnyan.aqua.sega.general.model.response.UserRecentRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

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
    private List<UserGameOptionEx> userGameOptionEx;

    @Nullable
    private List<UserMap> userMapList;

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
    private List<UserCharge> userChargeList;

    @Nullable
    private List<UserPlaylog> userPlaylogList;

    @Nullable
    private List<UserCourse> userCourseList;

    @Nullable
    private List<UserDataEx> userDataEx;

    @Nullable
    private List<UserDuel> userDuelList;

    @Nullable
    @JsonProperty("isNewMapList")
    private String isNewMapList;

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

}
