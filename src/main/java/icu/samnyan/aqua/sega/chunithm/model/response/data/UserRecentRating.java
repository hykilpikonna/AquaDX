package icu.samnyan.aqua.sega.chunithm.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecentRating {
    private int musicId;
    private int difficultId;
    private String romVersionCode;
    private int score;
}
