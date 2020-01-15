package icu.samnyan.aqua.sega.allnet.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerOnRequest {

    private String game_id;
    private String ver;
    private String serial;
    private String ip;
    private String firm_ver;
    private String boot_ver;
    private String encode;
    private String format_ver;
    private String hops;
    private String token;
}
