package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class PvDefChrLstResponse extends BaseResponse {
    private String pdcl_lut;
    private String pdc_lst;

    public PvDefChrLstResponse(String cmd, String req_id, String stat, String pdcl_lut, String pdc_lst) {
        super(cmd, req_id, stat);
        this.pdcl_lut = pdcl_lut;
        this.pdc_lst = pdc_lst;
    }
}
