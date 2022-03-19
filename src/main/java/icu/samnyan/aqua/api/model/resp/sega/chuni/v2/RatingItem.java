package icu.samnyan.aqua.api.model.resp.sega.chuni.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingItem {

    private int musicId;

    private String musicName;

    private String artistName;

    private int level;

    private int score;

    private int ratingBase;

    private int rating;
}
