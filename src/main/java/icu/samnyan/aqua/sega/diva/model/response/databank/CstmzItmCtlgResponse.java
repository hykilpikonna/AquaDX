package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class CstmzItmCtlgResponse extends BaseResponse {
    private String cstmz_itm_ctlg_lut;
    private String cstmz_itm_ctlg;

    public CstmzItmCtlgResponse(String cmd, String req_id, String stat, String cstmz_itm_ctlg_lut, String cstmz_itm_ctlg) {
        super(cmd, req_id, stat);
        this.cstmz_itm_ctlg_lut = cstmz_itm_ctlg_lut;
        this.cstmz_itm_ctlg = cstmz_itm_ctlg;
    }
}
