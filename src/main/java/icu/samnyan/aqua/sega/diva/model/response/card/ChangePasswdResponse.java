package icu.samnyan.aqua.sega.diva.model.response.card;

import icu.samnyan.aqua.sega.diva.model.common.Result;
import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class ChangePasswdResponse extends BaseResponse {
    private Result cd_adm_result;
    private int accept_idx;
    private int pd_id;

    public ChangePasswdResponse(String cmd, String req_id, String stat, Result cd_adm_result, int accept_idx, int pd_id) {
        super(cmd, req_id, stat);
        this.cd_adm_result = cd_adm_result;
        this.accept_idx = accept_idx;
        this.pd_id = pd_id;
    }

    public ChangePasswdResponse(String cmd, String req_id, String stat, Result cd_adm_result) {
        super(cmd, req_id, stat);
        this.cd_adm_result = cd_adm_result;
    }
}
