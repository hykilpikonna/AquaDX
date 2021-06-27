package icu.samnyan.aqua.sega.ongeki.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTechEventRankingItem {
    private int eventId;
    private String date;
    private int rank;
    private int totalTechScore;
    private int totalPlatinumScore;
}
