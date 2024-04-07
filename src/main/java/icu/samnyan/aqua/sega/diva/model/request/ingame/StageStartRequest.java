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
public class StageStartRequest extends BaseRequest {
    public long pd_id;
    public int accept_idx;
    public int start_idx;
    public boolean free_play;
    public int game_type;
    public int[] stg_difficulty;
    public int[] stg_edtn;
    public int[] stg_ply_pv_id;
    public int[] stg_sel_pv_id; //
    public int[] stg_scrpt_ver;

    public int[] stg_skin_id;
    public int[] stg_btn_se;
    public int[] stg_btn_se_vol;
    public int[] stg_sld_se;
    public int[] stg_chn_sld_se;
    public int[] stg_sldr_tch_se;
    public int[] stg_mdl_id;
    public int[] stg_sel_mdl_id;
    public int[] stg_rvl_pd_id;
    public int[] stg_c_itm_id;
    public int[] stg_ms_itm_flg;
    public int[] stg_rgo;
    public int[] stg_ss_num;
    public int[] stg_is_cs_scs;

    public int continue_cnt;

}
