package icu.samnyan.aqua.api.model.resp.sega.maimai2.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalUserData implements Serializable {

    private String accessCode;
    private String userName;
    private int isNetMember;
    private int iconId;
    private int plateId;
    private int titleId;
    private int partnerId;
    private int frameId;
    private int selectMapId;
    private int totalAwake;
    private int gradeRating;
    private int musicRating;
    private int playerRating;
    private int highestRating;
    private int gradeRank;
    private int classRank;
    private int courseRank;
    private List<Integer> charaSlot;
    private List<Integer> charaLockSlot;
    private long contentBit;
    private int playCount;
    private String eventWatchedDate;
    private String lastGameId;
    private String lastRomVersion;
    private String lastDataVersion;
    private String lastLoginDate;
    private String lastPlayDate;
    private int lastPlayCredit;
    private int lastPlayMode;
    private int lastPlaceId;
    private String lastPlaceName;
    private int lastAllNetId;
    private int lastRegionId;
    private String lastRegionName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastClientId;
    private String lastCountryCode;
    private int lastSelectEMoney;
    private int lastSelectTicket;
    private int lastSelectCourse;
    private int lastCountCourse;
    private String firstGameId;
    private String firstRomVersion;
    private String firstDataVersion;
    private String firstPlayDate;
    private String compatibleCmVersion;
    private String dailyBonusDate;
    private String dailyCourseBonusDate;
    private String lastPairLoginDate;
    private String lastTrialPlayDate;
    private int playVsCount;
    private int playSyncCount;
    private int winCount;
    private int helpCount;
    private int comboCount;
    private long totalDeluxscore;
    private long totalBasicDeluxscore;
    private long totalAdvancedDeluxscore;
    private long totalExpertDeluxscore;
    private long totalMasterDeluxscore;
    private long totalReMasterDeluxscore;
    private int totalSync;
    private int totalBasicSync;
    private int totalAdvancedSync;
    private int totalExpertSync;
    private int totalMasterSync;
    private int totalReMasterSync;
    private long totalAchievement;
    private long totalBasicAchievement;
    private long totalAdvancedAchievement;
    private long totalExpertAchievement;
    private long totalMasterAchievement;
    private long totalReMasterAchievement;
    private long playerOldRating;
    private long playerNewRating;
    private int banState;
    private long dateTime;
}
