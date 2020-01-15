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
public class ChangeNameRequest extends BaseRequest {
    private String a_code;
    private int aime_id;
    private String aime_a_code;
    private int pd_id;
    private int accept_idx;
    private String player_name;
    private int chg_name_price;
}
