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
public class GameEventItem {
    private long id;
    private int type;
    private String startDate;
    private String endDate;
}
