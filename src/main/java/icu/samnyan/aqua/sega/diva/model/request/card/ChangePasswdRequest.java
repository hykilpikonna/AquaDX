package icu.samnyan.aqua.sega.diva.model.request.card;

import icu.samnyan.aqua.sega.diva.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswdRequest extends BaseRequest {
    private String a_code;
    private long aime_id;
    private String aime_a_code;
    private long pd_id;
    private int accept_idx;
    private String new_passwd;
}
