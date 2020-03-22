package icu.samnyan.aqua.sega.ongeki.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fro getGameRanking request
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRankingItem {
    private long id;
    // this 2 field never use in game code,
    // maybe for the future update like in game player ranking
    private long point;
    private String userName;
}
