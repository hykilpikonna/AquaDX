package icu.samnyan.aqua.sega.ongeki.model.userdata;

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
@Entity(name = "OngekiUserPlaylog")
@Table(name = "ongeki_user_playlog")
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
    private UserData user;

    private int sortNumber;

    private int placeId;

    private String placeName;

    private String playDate;

    private String userPlayDate;

    private int musicId;

    private int level;

    private int playKind;

    private int eventId;

    private String eventName;

    private int eventPoint;

    private int playedUserId1;

    private int playedUserId2;

    private int playedUserId3;

    private String playedUserName1;

    private String playedUserName2;

    private String playedUserName3;

    private int playedMusicLevel1;

    private int playedMusicLevel2;

    private int playedMusicLevel3;

    private int cardId1;

    private int cardId2;

    private int cardId3;

    private int cardLevel1;

    private int cardLevel2;

    private int cardLevel3;

    private int cardAttack1;

    private int cardAttack2;

    private int cardAttack3;

    private int bossCharaId;

    private int bossLevel;

    private int bossAttribute;

    private int clearStatus;

    private int techScore;

    private int techScoreRank;

    private int battleScore;

    private int battleScoreRank;

    private int platinumScore;

    private int maxCombo;

    private int judgeMiss;

    private int judgeHit;

    private int judgeBreak;

    private int judgeCriticalBreak;

    private int rateTap;

    private int rateHold;

    private int rateFlick;

    private int rateSideTap;

    private int rateSideHold;

    private int bellCount;

    private int totalBellCount;

    private int damageCount;

    private int overDamage;

    @JsonProperty("isTechNewRecord")
    private boolean isTechNewRecord;

    @JsonProperty("isBattleNewRecord")
    private boolean isBattleNewRecord;

    @JsonProperty("isOverDamageNewRecord")
    private boolean isOverDamageNewRecord;

    @JsonProperty("isFullCombo")
    private boolean isFullCombo;

    @JsonProperty("isFullBell")
    private boolean isFullBell;

    @JsonProperty("isAllBreak")
    private boolean isAllBreak;

    private int playerRating;

    private int battlePoint;

    @Override
    public int getAchievement() {
        return techScore;
    }

    @Override
    public int getAfterRating() {
        return playerRating;
    }

    @Override
    public int getBeforeRating() {
        return playerRating; // TODO: Get before rating
    }

    @Override
    public boolean isAllPerfect() {
        return isAllBreak;
    }
}
