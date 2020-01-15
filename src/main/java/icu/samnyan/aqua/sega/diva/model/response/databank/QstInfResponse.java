package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class QstInfResponse extends BaseResponse {
    private String qi_lut;
    private String qhi_str;
    private String qrai_str;

    public QstInfResponse(String cmd, String req_id, String stat, String qi_lut, String qhi_str, String qrai_str) {
        super(cmd, req_id, stat);
        this.qi_lut = qi_lut;
        this.qhi_str = qhi_str;
        this.qrai_str = qrai_str;
    }
}
