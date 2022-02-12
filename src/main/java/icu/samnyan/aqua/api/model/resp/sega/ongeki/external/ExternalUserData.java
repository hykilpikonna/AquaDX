package icu.samnyan.aqua.api.model.resp.sega.ongeki.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.util.jackson.AccessCodeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalUserData implements Serializable {

    private String accessCode;

    private String userName;

    private int level;

    private int reincarnationNum;

    private long exp;

    private long point;

    private long totalPoint;

    private int playCount;

    private int jewelCount;

    private int totalJewelCount;

    private int medalCount;

    private int playerRating;

    private int highestRating;

    private int battlePoint;

    private int bestBattlePoint;

    private int overDamageBattlePoint;

    private int nameplateId;

    private int trophyId;

    private int cardId;

    private int characterId;

    private int tabSetting;

    private int tabSortSetting;

    private int cardCategorySetting;

    private int cardSortSetting;

    private int rivalScoreCategorySetting;

    private int playedTutorialBit;

    private int firstTutorialCancelNum;

    private long sumTechHighScore;

    private long sumTechBasicHighScore;

    private long sumTechAdvancedHighScore;

    private long sumTechExpertHighScore;

    private long sumTechMasterHighScore;

    private long sumTechLunaticHighScore;

    private long sumBattleHighScore;

    private long sumBattleBasicHighScore;

    private long sumBattleAdvancedHighScore;

    private long sumBattleExpertHighScore;

    private long sumBattleMasterHighScore;

    private long sumBattleLunaticHighScore;

    private String eventWatchedDate;

    private String cmEventWatchedDate;

    private String firstGameId;

    private String firstRomVersion;

    private String firstDataVersion;

    private String firstPlayDate;

    private String lastGameId;

    private String lastRomVersion;

    private String lastDataVersion;

    private String compatibleCmVersion;

    private String lastPlayDate;

    private int lastPlaceId;

    private String lastPlaceName;

    private int lastRegionId;

    private String lastRegionName;

    private int lastAllNetId;

    private String lastClientId;

    private int lastUsedDeckId;

    private int lastPlayMusicLevel;

    private int lastEmoneyBrand;

}
