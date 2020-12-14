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
public class PowerOnResponseV2 implements PowerOnResponse {
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
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private String setting;
    private String timezone;
    private String res_class;

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
                "&year=" + year +
                "&month=" + month +
                "&day=" + day +
                "&hour=" + hour +
                "&minute=" + minute +
                "&second=" + second +
                "&setting=" + setting +
                "&timezone=" + timezone +
                "&res_class=PowerOnResponseV2";
    }
}