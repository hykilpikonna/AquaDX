package icu.samnyan.aqua.sega.maimai2.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecommendRateMusic {
    private int musicId;
    private int level;
    private int averageAchievement;
}
