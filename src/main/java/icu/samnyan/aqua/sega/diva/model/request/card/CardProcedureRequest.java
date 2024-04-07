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
public class CardProcedureRequest extends BaseRequest {
    private int cd_adm_cmd;
    private String a_code;
    private long aime_id;
    private String aime_a_code;
}
