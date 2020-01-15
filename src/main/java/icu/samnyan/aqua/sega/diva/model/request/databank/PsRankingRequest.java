package icu.samnyan.aqua.sega.diva.model.request.databank;

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
public class PsRankingRequest extends BaseRequest {
    private int[] rnk_ps_pv_id_lst;
    private int rnk_ps_idx;
}
