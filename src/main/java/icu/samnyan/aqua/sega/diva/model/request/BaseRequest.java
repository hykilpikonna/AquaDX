package icu.samnyan.aqua.sega.diva.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * Data format from <url>https://dev.s-ul.eu/mikumiku/minime/wikis/home</url>
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {
    private String cmd; // Command
    private String req_id; // Request Id
    private String game_id; // Game Id
    private String r_ver; // Game Version
    private String kc_serial; // KeyChip Serial
    private String b_serial; // Board Serial
    private String place_id; // Place Id
    private ZonedDateTime time_stamp;
    private String start_up_mode; // Satellite mode, as on dipsw#1, 0 is SUB and 1 is MAIN
    private String cmm_dly_mod;
    private String cmm_dly_sec;
    private String cmm_err_mod;
    private String country_code;
    private String region_code;
}
