package icu.samnyan.aqua.sega.diva.model.common.attend;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
public class GameBalanceTrialBid {
    private int pay;
    private int win;

    public String getString() {
        return pay + "," + win;
    }
}
