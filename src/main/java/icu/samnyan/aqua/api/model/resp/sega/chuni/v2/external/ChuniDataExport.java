package icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external;

import icu.samnyan.aqua.sega.chusan.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is use for exporting chusan profile
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChuniDataExport {
    private String gameId = "SDHD";
    private UserData userData;
    private List<UserActivity> userActivityList;
    private List<UserCharacter> userCharacterList;
    private List<UserCharge> userChargeList;
    private List<UserCourse> userCourseList;
    private List<UserDuel> userDuelList;
    private UserGameOption userGameOption;
    private List<UserItem> userItemList;
    private List<UserMapArea> userMapList;
    private List<UserMusicDetail> userMusicDetailList;
    private List<UserPlaylog> userPlaylogList;
}
