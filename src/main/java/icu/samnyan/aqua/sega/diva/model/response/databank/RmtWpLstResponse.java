package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class RmtWpLstResponse extends BaseResponse {
    private String rwl_lut;
    private String rw_lst;

    public RmtWpLstResponse(String cmd, String req_id, String stat, String rwl_lut, String rw_lst) {
        super(cmd, req_id, stat);
        this.rwl_lut = rwl_lut;
        this.rw_lst = rw_lst;
    }
}
