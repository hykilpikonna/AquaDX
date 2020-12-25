package icu.samnyan.aqua.sega.maimai.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResp {
    public int returnCode = 1;
    public String lastLoginDate = "2020-01-01 00:00:00.0";
    public int loginCount = 0;
    public int consecutiveLoginCount = 0;
}
