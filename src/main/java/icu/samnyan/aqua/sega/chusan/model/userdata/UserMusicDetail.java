package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import icu.samnyan.aqua.sega.chusan.util.BooleanToIntegerDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanUserMusicDetail")
@Table(name = "chusan_user_music_detail", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "music_id", "level"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "musicId",
        "level",
        "playCount",
        "scoreMax",
        "missCount",
        "maxComboCount",
        "isFullCombo",
        "isAllJustice",
        "isSuccess",
        "fullChain",
        "maxChain",
        "isLock",
        "theoryCount",
        "ext1"
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

    @Column(name = "music_id")
    private int musicId;

    private int level;

    private int playCount;

    private int scoreMax;

    private int missCount;

    private int maxComboCount;

    @JsonProperty("isFullCombo")
    private boolean isFullCombo;

    @JsonProperty("isAllJustice")
    private boolean isAllJustice;

    @JsonDeserialize(using = BooleanToIntegerDeserializer.class)
    @JsonProperty("isSuccess")
    private int isSuccess;

    private int fullChain;

    private int maxChain;

    private int scoreRank;

    @JsonProperty("isLock")
    private boolean isLock;

    private int theoryCount;

    private int ext1;

    public UserMusicDetail(UserData userData) {
        user = userData;
    }

    public UserMusicDetail(int musicId, int level, int playCount, int scoreMax, int missCount, int maxComboCount, boolean isFullCombo, boolean isAllJustice, int isSuccess, int fullChain, int maxChain, int scoreRank, boolean isLock, int theoryCount, int ext1) {
        this.musicId = musicId;
        this.level = level;
        this.playCount = playCount;
        this.scoreMax = scoreMax;
        this.missCount = missCount;
        this.maxComboCount = maxComboCount;
        this.isFullCombo = isFullCombo;
        this.isAllJustice = isAllJustice;
        this.isSuccess = isSuccess;
        this.fullChain = fullChain;
        this.maxChain = maxChain;
        this.scoreRank = scoreRank;
        this.isLock = isLock;
        this.theoryCount = theoryCount;
        this.ext1 = ext1;
    }
}
