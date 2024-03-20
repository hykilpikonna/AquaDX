package icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external;

import icu.samnyan.aqua.sega.chusan.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is use for exporting chusan profile
 * @author samnyan (publicamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chu3DataExport {
    public String gameId = "SDHD";
    public UserData userData;
    public UserGameOption userGameOption;
    public List<UserActivity> userActivityList;
    public List<UserCharacter> userCharacterList;
    public List<UserCharge> userChargeList;
    public List<UserCourse> userCourseList;
    public List<UserDuel> userDuelList;
    public List<UserItem> userItemList;
    public List<UserMapArea> userMapList;
    public List<UserMusicDetail> userMusicDetailList;
    public List<UserPlaylog> userPlaylogList;
    public List<UserLoginBonus> userLoginBonusList;
}
