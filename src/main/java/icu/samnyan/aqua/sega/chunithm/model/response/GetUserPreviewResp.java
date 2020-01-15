package icu.samnyan.aqua.sega.chunithm.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCharacter;
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
public class GetUserPreviewResp {

    private String userId;
    @JsonProperty("isLogin")
    private boolean isLogin;
    private LocalDateTime lastLoginDate;
    private String userName;
    private int reincarnationNum;
    private int level;
    private String exp;
    private int playerRating;
    private String lastGameId;
    private String lastRomVersion;
    private String lastDataVersion;
    private LocalDateTime lastPlayDate;
    private int trophyId;
    private UserCharacter userCharacter;
    private int playerLevel;
    private int rating;
    private int headphone;

}
