package icu.samnyan.aqua.sega.diva.model.request.ingame;

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
public class GetPvPdRequest extends BaseRequest {
    private int pd_id;
    private int difficulty;
    private int[] pd_pv_id_lst;
}
