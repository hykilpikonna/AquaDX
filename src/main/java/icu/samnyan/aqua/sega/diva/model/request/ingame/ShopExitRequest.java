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
public class ShopExitRequest extends BaseRequest {
    private int pd_id;
    private int accept_idx;
    private int start_idx;
    private int use_pv_mdl_eqp;
    private int ply_pv_id;
    private int[] mdl_eqp_cmn_ary;
    private int[] c_itm_eqp_cmn_ary;
    private int[] ms_itm_flg_cmn_ary;
    private int[] mdl_eqp_pv_ary;
    private int[] c_itm_eqp_pv_ary;
    private int[] ms_itm_flg_pv_ary;
}
