package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class PstdItemNgLstResponse extends BaseResponse {
    private String p_std_i_n_lut;
    private String p_std_i_ie_n_lst;
    private String p_std_i_se_n_lst;

    public PstdItemNgLstResponse(String cmd, String req_id, String stat, String p_std_i_n_lut, String p_std_i_ie_n_lst, String p_std_i_se_n_lst) {
        super(cmd, req_id, stat);
        this.p_std_i_n_lut = p_std_i_n_lut;
        this.p_std_i_ie_n_lst = p_std_i_ie_n_lst;
        this.p_std_i_se_n_lst = p_std_i_se_n_lst;
    }
}
