package icu.samnyan.aqua.sega.diva.model.response;

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
public class BaseResponse {
    private String cmd;
    private String req_id;
    private String stat;

}
