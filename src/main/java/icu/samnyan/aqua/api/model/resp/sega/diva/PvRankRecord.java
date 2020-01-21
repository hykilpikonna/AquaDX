package icu.samnyan.aqua.api.model.resp.sega.diva;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sam_nya (samnya@outlook.com)
 */
@Data
@AllArgsConstructor
public class PvRankRecord {
    private long id;
    private String playerName;
    private int score;
    private int attain;
}
