package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserMusicDetail")
@Table(name = "chuni_user_music_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "musicId",
        "level",
        "playCount",
        "scoreMax",
        "resRequestCount",
        "resAcceptCount",
        "resSuccessCount",
        "missCount",
        "maxComboCount",
        "isFullCombo",
        "isAllJustice",
        "isSuccess",
        "fullChain",
        "maxChain",
        "scoreRank",
        "isLock"
})
public class UserMusicDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int musicId;

    private int level;

    private int playCount;

    private int scoreMax;

    private int resRequestCount;

    private int resAcceptCount;

    private int resSuccessCount;

    private int missCount;

    private int maxComboCount;

    @JsonProperty("isFullCombo")
    private boolean isFullCombo;

    @JsonProperty("isAllJustice")
    private boolean isAllJustice;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    private int fullChain;

    private int maxChain;

    private int scoreRank;

    @JsonProperty("isLock")
    private boolean isLock;


    public UserMusicDetail(UserData userData) {
        user = userData;
    }

    public UserMusicDetail(int musicId, int level, int playCount, int scoreMax, int resRequestCount, int resAcceptCount, int resSuccessCount, int missCount, int maxComboCount, boolean isFullCombo, boolean isAllJustice, boolean isSuccess, int fullChain, int maxChain, int scoreRank, boolean isLock) {
        this.musicId = musicId;
        this.level = level;
        this.playCount = playCount;
        this.scoreMax = scoreMax;
        this.resRequestCount = resRequestCount;
        this.resAcceptCount = resAcceptCount;
        this.resSuccessCount = resSuccessCount;
        this.missCount = missCount;
        this.maxComboCount = maxComboCount;
        this.isFullCombo = isFullCombo;
        this.isAllJustice = isAllJustice;
        this.isSuccess = isSuccess;
        this.fullChain = fullChain;
        this.maxChain = maxChain;
        this.scoreRank = scoreRank;
        this.isLock = isLock;
    }
}
