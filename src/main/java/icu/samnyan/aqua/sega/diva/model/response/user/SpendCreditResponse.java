package icu.samnyan.aqua.sega.diva.model.response.user;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class SpendCreditResponse extends BaseResponse {
    private String cmpgn_rslt;
    private int cmpgn_rslt_num;
    private int vcld_pts;
    private String lv_str;
    private int lv_efct_id;
    private int lv_plt_id;

    public SpendCreditResponse(String cmd, String req_id, String stat, String cmpgn_rslt, int cmpgn_rslt_num, int vcld_pts, String lv_str, int lv_efct_id, int lv_plt_id) {
        super(cmd, req_id, stat);
        this.cmpgn_rslt = cmpgn_rslt;
        this.cmpgn_rslt_num = cmpgn_rslt_num;
        this.vcld_pts = vcld_pts;
        this.lv_str = lv_str;
        this.lv_efct_id = lv_efct_id;
        this.lv_plt_id = lv_plt_id;
    }
}
