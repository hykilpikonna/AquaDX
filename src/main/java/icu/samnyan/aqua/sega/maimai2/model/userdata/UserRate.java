package icu.samnyan.aqua.sega.maimai2.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRate {
    private int musicId;
    private int level;
    private int romVersion;
    private int achievement;
}
