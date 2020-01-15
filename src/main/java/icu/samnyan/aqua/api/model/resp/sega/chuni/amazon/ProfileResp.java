package icu.samnyan.aqua.api.model.resp.sega.chuni.amazon;

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
public class ProfileResp {

    private String userName;

    private int level;

    private String exp;

    private long point;

    private long totalPoint;

    private int playCount;

    private int playerRating;

    private int highestRating;

    private int nameplateId;

    private int frameId;

    private int characterId;

    private int trophyId;

    private int totalMapNum;

    private long totalHiScore;

    private long totalBasicHighScore;

    private long totalAdvancedHighScore;

    private long totalExpertHighScore;

    private long totalMasterHighScore;

    private int friendCount;

    private LocalDateTime firstPlayDate;

    private LocalDateTime lastPlayDate;

    private int courseClass;
}
