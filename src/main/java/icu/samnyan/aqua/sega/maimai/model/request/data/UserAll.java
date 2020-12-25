package icu.samnyan.aqua.sega.maimai.model.request.data;

import icu.samnyan.aqua.sega.maimai.model.response.data.UserGradeStatus;
import icu.samnyan.aqua.sega.maimai.model.response.data.UserRecentRating;
import icu.samnyan.aqua.sega.maimai.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAll implements Serializable {
    private List<UserPlaylog> userPlaylogList;
    private List<UserData> userData;
    private List<UserOption> userOption;
    private Integer userId;
    private List<UserWebOption> userWebOption;
    private List<UserMusicDetail> userMusicDetailList;
    private List<UserItem> userItemList;
    private List<UserRecentRating> userRecentRatingList;
    private List<UserActivity> userActivityList;
    private List<UserGradeStatus> userGradeStatusList;
    private List<UserBoss> userBossList;
    private List<UserCharacter> userCharacterList;
    private String isNewCharacterList;
}
