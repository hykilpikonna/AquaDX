package icu.samnyan.aqua.sega.chusan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class UserMusicDetailForAncientChusan implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    private int fullChain;

    private int maxChain;

    private int scoreRank;

    @JsonProperty("isLock")
    private boolean isLock;

    private int theoryCount;

    private int ext1;

    public UserMusicDetailForAncientChusan(int musicId, int level, int playCount, int scoreMax, int missCount, int maxComboCount, boolean isFullCombo, boolean isAllJustice, int isSuccess, int fullChain, int maxChain, int scoreRank, boolean isLock, int theoryCount, int ext1) {
        this.musicId = musicId;
        this.level = level;
        this.playCount = playCount;
        this.scoreMax = scoreMax;
        this.missCount = missCount;
        this.maxComboCount = maxComboCount;
        this.isFullCombo = isFullCombo;
        this.isAllJustice = isAllJustice;
        this.isSuccess = isSuccess > 0;
        this.fullChain = fullChain;
        this.maxChain = maxChain;
        this.scoreRank = scoreRank;
        this.isLock = isLock;
        this.theoryCount = theoryCount;
        this.ext1 = ext1;
    }
}
