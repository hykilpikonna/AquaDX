package icu.samnyan.aqua.api.model.resp.sega.chuni.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentResp {

    private LocalDateTime playDate;

    private LocalDateTime userPlayDate;

    private int musicId;

    private int level;

    private int customId;

    private int playedCustom1;

    private int playedCustom2;

    private int playedCustom3;

    private int track;

    private int score;

    private int rank;

    private int maxCombo;

    private int maxChain;

    private int rateTap;

    private int rateHold;

    private int rateSlide;

    private int rateAir;

    private int rateFlick;

    private int judgeGuilty;

    private int judgeAttack;

    private int judgeJustice;

    private int judgeCritical;

    private int playerRating;

    @JsonProperty("isNewRecord")
    private boolean isNewRecord;

    @JsonProperty("isFullCombo")
    private boolean isFullCombo;

    private int fullChainKind;

    @JsonProperty("isAllJustice")
    private boolean isAllJustice;

    private int characterId;

    private int skillId;

    private int playKind;

    @JsonProperty("isClear")
    private boolean isClear;

    private int skillLevel;

    private int skillEffect;
}
