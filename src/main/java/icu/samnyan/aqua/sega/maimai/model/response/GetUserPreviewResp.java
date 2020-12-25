package icu.samnyan.aqua.sega.maimai.model.response;

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

    private Long userId = 0L;
    private String userName = "";
    @JsonProperty("isLogin")
    private boolean isLogin = false;
    private int lastDataVersion = 0;
    private String lastLoginDate = null;
    private String lastPlayDate = null;
    private int playerRating = 0;
    private int nameplateId = 0;
    private int frameId = 0;
    private int iconId = 0;
    private int trophyId = 0;
    private int dispRate = 1;
    private int dispRank = 1;
    private int dispHomeRanker = 1;
    private int dispTotalLv = 1;
    private int totalLv = 0;
}
