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
public class StageResultRequest extends BaseRequest {
    public long pd_id;
    public int accept_idx;
    public int start_idx;
    public int hp_vol;
    public boolean btn_se_vol;
    public int btn_se_vol2;
    public int sldr_se_vol2;
    public boolean use_pv_mdl_eqp;
    public int vcld_pts;
    public int nxt_pv_id;
    public int nxt_dffclty;
    public int nxt_edtn;
    public int sort_kind;
    public int nblss_ltt_stts;
    public int nblss_ltt_tckt;
    public int[] my_qst_id;
    public int[] my_qst_sts;
    public boolean free_play;
    public int game_type;
    public int[] stg_difficulty;
    public int[] stg_edtn;
    public int[] stg_ply_pv_id;
    public int[] stg_sel_pv_id;
    public int[] stg_scrpt_ver;
    public int[] stg_score;
    public int[] stg_chllng_kind;
    public int[] stg_chllng_result;
    public int[] stg_clr_kind;
    public int[] stg_vcld_pts;
    public int[] stg_cool_cnt;
    public int[] stg_cool_pct;
    public int[] stg_fine_cnt;
    public int[] stg_fine_pct;
    public int[] stg_safe_cnt;
    public int[] stg_safe_pct;
    public int[] stg_sad_cnt;
    public int[] stg_sad_pct;
    public int[] stg_wt_wg_cnt;
    public int[] stg_wt_wg_pct;

    public int[] stg_max_cmb;
    public int[] stg_chance_tm;
    public int[] stg_sm_hl;
    public int[] stg_atn_pnt;
    public int[] stg_skin_id;
    public int[] stg_btn_se;
    public int[] stg_btn_se_vol;
    public int[] stg_sld_se;
    public int[] stg_chn_sld_se;
    public int[] stg_sldr_tch_se;
    public int[] stg_mdl_id;
    public int[] stg_sel_mdl_id;
    public int[] stg_rvl_pd_id;
    public int[] stg_rvl_wl;
    public int[] stg_cpt_rslt;
    public int[] stg_sld_scr;
    public int[] stg_is_sr_gm;
    public int[] stg_pv_brnch_rslt;
    public int[] stg_vcl_chg;
    public int[] stg_c_itm_id;
    public int[] stg_ms_itm_flg;
    public int[] stg_rgo;
    public int[] stg_ss_num;
    public int[] stg_is_cs_scs;
    public int[] stg_is_nppg_use;
    public int[] stg_p_std_lo_id;
    public int[] stg_p_std_is_to;
    public int[] stg_p_std_is_ccu;
    public int[] stg_p_std_is_tiu;
    public int[] stg_p_std_is_iu;
    public int[] stg_p_std_is_npu;
    public int[] stg_p_std_is_du;

    public int ply_pv_id;

    public int ttl_vp_add;
    public int ttl_vp_sub;

    public int continue_cnt;
    public int[] gu_cmd;

    public int[] mdl_eqp_cmn_ary;
    public int[] c_itm_eqp_cmn_ary;
    public int[] ms_itm_flg_cmn_ary;
    public int[] mdl_eqp_pv_ary;
    public int[] c_itm_eqp_pv_ary;
    public int[] ms_itm_flg_pv_ary;
    public int[] stg_mdl_s_sts;

    public int cr_cid;
    public int cr_sc;
    public int cr_tv;
    public int cr_if;
    public String[] cr_sp;
}
