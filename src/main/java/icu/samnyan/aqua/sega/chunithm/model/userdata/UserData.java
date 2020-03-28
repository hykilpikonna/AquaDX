package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.util.jackson.AccessCodeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserData")
@Table(name = "chuni_user_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "accessCode",
        "userName",
        "isWebJoin",
        "webLimitDate", "level",
        "reincarnationNum",
        "exp",
        "point",
        "totalPoint",
        "playCount",
        "multiPlayCount",
        "multiWinCount",
        "requestResCount",
        "acceptResCount",
        "successResCount",
        "playerRating",
        "highestRating",
        "nameplateId",
        "frameId",
        "characterId",
        "trophyId",
        "playedTutorialBit",
        "firstTutorialCancelNum",
        "masterTutorialCancelNum",
        "totalRepertoireCount",
        "totalMapNum",
        "totalHiScore",
        "totalBasicHighScore",
        "totalAdvancedHighScore",
        "totalExpertHighScore",
        "totalMasterHighScore",
        "eventWatchedDate",
        "friendCount",
        "isMaimai",
        "firstGameId",
        "firstRomVersion",
        "firstDataVersion",
        "firstPlayDate",
        "lastGameId",
        "lastRomVersion",
        "lastDataVersion",
        "lastPlayDate",
        "lastPlaceId",
        "lastPlaceName",
        "lastRegionId",
        "lastRegionName",
        "lastAllNetId",
        "lastClientId"})
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonSerialize(using = AccessCodeSerializer.class)
    @JsonProperty(value = "accessCode", access = JsonProperty.Access.READ_ONLY)
    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;
    // Access code in card

    private String userName;

    private LocalDateTime lastLoginDate;

    @JsonProperty("isWebJoin")
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

    // Currently selected UserCharacter
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

    private String lastClientId;
}
