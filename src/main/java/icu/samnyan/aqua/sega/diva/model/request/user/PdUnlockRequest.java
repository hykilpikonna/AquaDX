package icu.samnyan.aqua.sega.diva.model.request.user;

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
public class PdUnlockRequest extends BaseRequest {
    private long pd_id;
    private int accept_idx;
}
