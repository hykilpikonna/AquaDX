package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class PstdHCtrlResponse extends BaseResponse {
    private String p_std_hc_lut;
    private String p_std_hc_str;

    public PstdHCtrlResponse(String cmd, String req_id, String stat, String p_std_hc_lut, String p_std_hc_str) {
        super(cmd, req_id, stat);
        this.p_std_hc_lut = p_std_hc_lut;
        this.p_std_hc_str = p_std_hc_str;
    }
}
