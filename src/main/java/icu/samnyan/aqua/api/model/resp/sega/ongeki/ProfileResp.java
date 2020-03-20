package icu.samnyan.aqua.api.model.resp.sega.ongeki;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResp {

    private String userName;

    private int level;

    private long exp;

    private long point;

    private long totalPoint;

    private int playCount;

    private int jewelCount;

    private int totalJewelCount;

    private int playerRating;

    private int highestRating;

    private int battlePoint;

    private int nameplateId;

    private int trophyId;

    private int cardId;

    private int characterId;

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

}
