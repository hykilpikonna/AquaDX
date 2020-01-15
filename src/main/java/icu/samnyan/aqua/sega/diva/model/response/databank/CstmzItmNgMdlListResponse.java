package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class CstmzItmNgMdlListResponse extends BaseResponse {
    private String cinml_lut;
    private String cinm_lst;

    public CstmzItmNgMdlListResponse(String cmd, String req_id, String stat, String cinml_lut, String cinm_lst) {
        super(cmd, req_id, stat);
        this.cinml_lut = cinml_lut;
        this.cinm_lst = cinm_lst;
    }
}
