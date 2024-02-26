package icu.samnyan.aqua.sega.maimai2.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.net.utils.IGenericGamePlaylog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserPlaylog")
@Table(name = "maimai2_user_playlog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlaylog implements Serializable, IGenericGamePlaylog {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int orderId;

    private long playlogId;

    private int version;

    private int placeId;

    private String placeName;

    private long loginDate;

    private String playDate;

    private String userPlayDate;

    private int type;

    private int musicId;

    private int level;

    private int trackNo;

    private int vsMode;

    private String vsUserName;

    private int vsStatus;

    private int vsUserRating;

    private int vsUserAchievement;

    private int vsUserGradeRank;

    private int vsRank;

    private int playerNum;

    private long playedUserId1;

    private String playedUserName1;

    private int playedMusicLevel1;

    private long playedUserId2;

    private String playedUserName2;

    private int playedMusicLevel2;

    private long playedUserId3;

    private String playedUserName3;

    private int playedMusicLevel3;

    private int characterId1;

    private int characterLevel1;

    private int characterAwakening1;

    private int characterId2;

    private int characterLevel2;

    private int characterAwakening2;

    private int characterId3;

    private int characterLevel3;

    private int characterAwakening3;

    private int characterId4;

    private int characterLevel4;

    private int characterAwakening4;

    private int characterId5;

    private int characterLevel5;

    private int characterAwakening5;

    private int achievement;

    private int deluxscore;

    private int scoreRank;

    private int maxCombo;

    private int totalCombo;

    private int maxSync;

    private int totalSync;

    private int tapCriticalPerfect;

    private int tapPerfect;

    private int tapGreat;

    private int tapGood;

    private int tapMiss;

    private int holdCriticalPerfect;

    private int holdPerfect;

    private int holdGreat;

    private int holdGood;

    private int holdMiss;

    private int slideCriticalPerfect;

    private int slidePerfect;

    private int slideGreat;

    private int slideGood;

    private int slideMiss;

    private int touchCriticalPerfect;

    private int touchPerfect;

    private int touchGreat;

    private int touchGood;

    private int touchMiss;

    private int breakCriticalPerfect;

    private int breakPerfect;

    private int breakGreat;

    private int breakGood;

    private int breakMiss;

    @JsonProperty("isTap")
    private boolean isTap;

    @JsonProperty("isHold")
    private boolean isHold;

    @JsonProperty("isSlide")
    private boolean isSlide;

    @JsonProperty("isTouch")
    private boolean isTouch;

    @JsonProperty("isBreak")
    private boolean isBreak;

    @JsonProperty("isCriticalDisp")
    private boolean isCriticalDisp;

    @JsonProperty("isFastLateDisp")
    private boolean isFastLateDisp;

    private int fastCount;

    private int lateCount;

    @JsonProperty("isAchieveNewRecord")
    private boolean isAchieveNewRecord;

    @JsonProperty("isDeluxscoreNewRecord")
    private boolean isDeluxscoreNewRecord;

    private int comboStatus;

    private int syncStatus;

    @JsonProperty("isClear")
    private boolean isClear;

    private int beforeRating;

    private int afterRating;

    private int beforeGrade;

    private int afterGrade;

    private int afterGradeRank;

    private int beforeDeluxRating;

    private int afterDeluxRating;

    @JsonProperty("isPlayTutorial")
    private boolean isPlayTutorial;

    @JsonProperty("isEventMode")
    private boolean isEventMode;

    @JsonProperty("isFreedomMode")
    private boolean isFreedomMode;

    private int playMode;

    @JsonProperty("isNewFree")
    private boolean isNewFree;

    private int trialPlayAchievement;

    private int extNum1;

    private int extNum2;

    private int extNum4;

    @JsonProperty("extBool1")
    private boolean extBool1;

    @NotNull
    @Override
    public Object getDate() {
        return playDate;
    }
}
