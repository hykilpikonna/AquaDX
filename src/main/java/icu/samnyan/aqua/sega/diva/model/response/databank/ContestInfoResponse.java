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
public class ContestInfoResponse extends BaseResponse {
    private LocalDateTime ci_lut;
    private String ci_str;

    public ContestInfoResponse(String cmd, String req_id, String stat, LocalDateTime ci_lut, String ci_str) {
        super(cmd, req_id, stat);
        this.ci_lut = ci_lut;
        this.ci_str = ci_str;
    }
}
