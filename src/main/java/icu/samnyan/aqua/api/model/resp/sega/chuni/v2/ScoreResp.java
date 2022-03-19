package icu.samnyan.aqua.api.model.resp.sega.chuni.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResp {

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
}
