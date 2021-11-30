package icu.samnyan.aqua.sega.maimai2.model.request.data;

import icu.samnyan.aqua.sega.maimai2.model.response.data.UserActivity;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRating;
import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAll implements Serializable {
	private List<UserDetail> userData;
	private List<UserExtend> userExtend;
	private List<UserOption> userOption;
	private List<UserCharacter> userCharacterList;
	private List<UserGhost> userGhost;
	private List<UserMap> userMapList;
	private List<UserLoginBonus> userLoginBonusList;
	private List<UserRating> userRatingList;
	private List<UserItem> userItemList;
	private List<UserMusicDetail> userMusicDetailList;
	private List<UserCourse> userCourseList;
	private List<UserCharge> userChargeList;
	private List<UserFavorite> userFavoriteList;
	private List<UserActivity> userActivityList;
	private List<Map<String, Object>> userGamePlaylogList;
	private String isNewCharacterList;
	private String isNewMapList;
	private String isNewLoginBonusList;
	private String isNewItemList;
	private String isNewMusicDetailList;
	private String isNewCourseList;
	private String isNewFavoriteList;
}
