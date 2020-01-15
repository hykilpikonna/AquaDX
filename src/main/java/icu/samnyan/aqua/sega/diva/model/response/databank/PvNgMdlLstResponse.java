package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class PvNgMdlLstResponse extends BaseResponse {
    private String pnml_lut;
    private String pnm_lst;

    public PvNgMdlLstResponse(String cmd, String req_id, String stat, String pnml_lut, String pnm_lst) {
        super(cmd, req_id, stat);
        this.pnml_lut = pnml_lut;
        this.pnm_lst = pnm_lst;
    }
}
