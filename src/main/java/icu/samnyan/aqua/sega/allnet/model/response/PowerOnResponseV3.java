package icu.samnyan.aqua.sega.allnet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerOnResponseV3 implements PowerOnResponse {
    private int stat;
    private String uri;
    private String host;
    private String place_id;
    private String name;
    private String nickname;
    private String region0;
    private String region_name0;
    private String region_name1;
    private String region_name2;
    private String region_name3;
    private String country;
    private String allnet_id;
    private String client_timezone;
    private String utc_time;
    private String setting;
    private String res_ver;
    private String token;

    @Override
    public String toString() {
        return "stat=" + stat +
                "&uri=" + uri +
                "&host=" + host +
                "&place_id=" + place_id +
                "&name=" + name +
                "&nickname=" + nickname +
                "&region0=" + region0 +
                "&region_name0=" + region_name0 +
                "&region_name1=" + region_name1 +
                "&region_name2=" + region_name2 +
                "&region_name3=" + region_name3 +
                "&country=" + country +
                "&allnet_id=" + allnet_id +
                "&client_timezone=" + client_timezone +
                "&utc_time=" + utc_time +
                "&setting=" + setting +
                "&res_ver=" + res_ver +
                "&token=" + token;
    }
}