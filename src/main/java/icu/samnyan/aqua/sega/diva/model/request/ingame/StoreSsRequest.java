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
public class StoreSsRequest extends BaseRequest {
    private long pd_id;
    private String ss_dat_id;
    private int ss_pv_id;
    private int ss_sel_pv_id;
    private int[] ss_mdl_id;
    private int[] ss_sel_mdl_id;
    private int[] ss_c_itm_id;
    private int[] ss_pxl_sz;
}
