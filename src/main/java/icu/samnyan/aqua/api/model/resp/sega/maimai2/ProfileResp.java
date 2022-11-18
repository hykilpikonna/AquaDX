package icu.samnyan.aqua.api.model.resp.sega.maimai2;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResp {

    private String userName;
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
    private int playCount;
    private String eventWatchedDate;
    private String lastRomVersion;
    private String lastDataVersion;
    private String lastPlayDate;
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
}
