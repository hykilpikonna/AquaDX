package icu.samnyan.aqua.sega.maimai2.model.request.data;

import icu.samnyan.aqua.sega.maimai2.model.response.data.UserActivity;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRating;
import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
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
		public List<UserDetail> userData;
		public List<UserExtend> userExtend;
		public List<UserOption> userOption;
		public List<UserCharacter> userCharacterList;
		public List<UserGhost> userGhost;
		public List<UserMap> userMapList;
		public List<UserLoginBonus> userLoginBonusList;
		public List<UserRating> userRatingList;
		public List<UserItem> userItemList;
		public List<UserMusicDetail> userMusicDetailList;
		public List<UserFavorite> userFavoriteList;
		public List<UserActivity> userActivityList;
		public String isNewCharacterList;
		public String isNewMapList;
		public String isNewLoginBonusList;
		public String isNewItemList;
		public String isNewMusicDetailList;
		public String isNewFavoriteList;
}
