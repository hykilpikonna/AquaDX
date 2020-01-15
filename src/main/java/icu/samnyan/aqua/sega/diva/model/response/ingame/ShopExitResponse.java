package icu.samnyan.aqua.sega.diva.model.response.ingame;

import icu.samnyan.aqua.sega.diva.model.common.Result;
import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class ShopExitResponse extends BaseResponse {
    private Result shp_rslt;

    public ShopExitResponse(String cmd, String req_id, String stat, Result shp_rslt) {
        super(cmd, req_id, stat);
        this.shp_rslt = shp_rslt;
    }
}
