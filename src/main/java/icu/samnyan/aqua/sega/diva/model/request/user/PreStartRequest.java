package icu.samnyan.aqua.sega.diva.model.request.user;

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
public class PreStartRequest extends BaseRequest {
    private String pmm;
    private String idm;
    private String mmgameid;
    private String mmuid;
    private String a_code;
    private int aime_id;
    private String aime_a_code;
    private String key_obj_type;
    private boolean exec_vu;
}
