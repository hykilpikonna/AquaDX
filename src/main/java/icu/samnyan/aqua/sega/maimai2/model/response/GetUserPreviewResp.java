package icu.samnyan.aqua.sega.maimai2.model.response;

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
public class GetUserPreviewResp {

    private long userId;
    private String userName;
    @JsonProperty("isLogin")
    private boolean isLogin;
    private String lastGameId;
    private String lastDataVersion;
    private String lastRomVersion;
    private String lastLoginDate;
    private String lastPlayDate;
    private int playerRating;
    private int nameplateId;
    private int iconId;
    private int trophyId;
    private int partnerId;
    private int frameId;
    private int dispRate;
    private int totalAwake;
    private int isNetMember;
    private String dailyBonusDate;
    private int headPhoneVolume;
    @JsonProperty("isInherit")
    private boolean isInherit;
    private int banState;
}
