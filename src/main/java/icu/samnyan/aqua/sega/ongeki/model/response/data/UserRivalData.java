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
public class UserRivalData {
    private long rivalUserId;
    private String rivalUserName;
}
