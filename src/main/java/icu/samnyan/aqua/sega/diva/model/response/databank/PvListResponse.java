package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class PvListResponse extends BaseResponse {
    private LocalDateTime pvl_lut;
    private String pv_lst;

    public PvListResponse(String cmd, String req_id, String stat, LocalDateTime pvl_lut, String pv_lst) {
        super(cmd, req_id, stat);
        this.pvl_lut = pvl_lut;
        this.pv_lst = pv_lst;
    }
}
