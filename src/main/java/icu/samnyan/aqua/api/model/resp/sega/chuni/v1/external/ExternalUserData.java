package icu.samnyan.aqua.api.model.resp.sega.chuni.v1.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class is use for exported UserData class. Using access code as identifier
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUserData {

    // Access Code of the card
    private String accessCode;

    private String userName;

    private LocalDateTime lastLoginDate;

    private boolean isWebJoin;

    private String webLimitDate;

    private int level;

    private int reincarnationNum;

    private String exp;

    private long point;

    private long totalPoint;

    private int playCount;

    private int multiPlayCount;

    private int multiWinCount;

    private int requestResCount;

    private int acceptResCount;

    private int successResCount;

    private int playerRating;

    private int highestRating;

    private int nameplateId;

    private int frameId;

    private int characterId;

    private int trophyId;

    private int playedTutorialBit;

    private int firstTutorialCancelNum;

    private int masterTutorialCancelNum;

    private int totalRepertoireCount;

    private int totalMapNum;

    private long totalHiScore;

    private long totalBasicHighScore;

    private long totalAdvancedHighScore;

    private long totalExpertHighScore;

    private long totalMasterHighScore;

    private LocalDateTime eventWatchedDate;

    private int friendCount;

    @JsonProperty("isMaimai")
    private boolean isMaimai;

    private String firstGameId;

    private String firstRomVersion;

    private String firstDataVersion;

    private LocalDateTime firstPlayDate;

    private String lastGameId;

    private String lastRomVersion;

    private String lastDataVersion;

    private LocalDateTime lastPlayDate;

    private int lastPlaceId;

    private String lastPlaceName;

    private String lastRegionId;

    private String lastRegionName;

    private String lastAllNetId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastClientId;
}
