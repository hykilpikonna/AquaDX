package icu.samnyan.aqua.sega.diva.model.request.ingame;

import icu.samnyan.aqua.sega.diva.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyCstmzItmRequest extends BaseRequest {
    private long pd_id;
    private int accept_idx;
    private int start_idx;
    private int cstmz_itm_id;
    private int cstmz_itm_price;
}
