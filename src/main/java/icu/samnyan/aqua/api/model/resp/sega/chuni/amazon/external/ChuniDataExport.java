package icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.external;

import icu.samnyan.aqua.sega.chunithm.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is use for exporting CHUNITHM profile
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChuniDataExport {
    private UserData userData;
    private List<UserActivity> userActivityList;
    private List<UserCharacter> userCharacterList;
    private List<UserCharge> userChargeList;
    private List<UserCourse> userCourseList;
    private UserDataEx userDataEx;
    private List<UserDuel> userDuelList;
    private UserGameOption userGameOption;
    private UserGameOptionEx userGameOptionEx;
    private List<UserItem> userItemList;
    private List<UserMap> userMapList;
    private List<UserMusicDetail> userMusicDetailList;
    private List<UserPlaylog> userPlaylogList;
}
