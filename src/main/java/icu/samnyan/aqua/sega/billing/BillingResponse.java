package icu.samnyan.aqua.sega.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingResponse {
    private int result;
    private int waittime;
    private int linelimit;
    private String message;
    private int playlimit;
    private String playlimitsig;
    private String protocolver;
    private int nearfull;
    private String nearfullsig;
    private int fixlogcnt;
    private int fixinterval;
    private String playhistory;

    @Override
    public String toString() {
        return "result=" + result +
                "&waittime=" + waittime +
                "&linelimit=" + linelimit +
                "&message=" + message +
                "&playlimit=" + playlimit +
                "&playlimitsig=" + playlimitsig +
                "&protocolver=" + protocolver +
                "&nearfull=" + nearfull +
                "&nearfullsig=" + nearfullsig +
                "&fixlogcnt=" + fixlogcnt +
                "&fixinterval=" + fixinterval +
                "&playhistory=" + playhistory;
    }
}