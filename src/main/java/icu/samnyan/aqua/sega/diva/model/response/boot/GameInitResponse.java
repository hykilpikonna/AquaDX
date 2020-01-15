package icu.samnyan.aqua.sega.diva.model.response.boot;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class GameInitResponse extends BaseResponse {
    private String db_close;
    private String retry_time;

    public GameInitResponse(String cmd, String req_id, String stat, String db_close, String retry_time) {
        super(cmd, req_id, stat);
        this.db_close = db_close;
        this.retry_time = retry_time;
    }
}
