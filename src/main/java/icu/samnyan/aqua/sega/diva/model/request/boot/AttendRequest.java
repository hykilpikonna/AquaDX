package icu.samnyan.aqua.sega.diva.model.request.boot;

import icu.samnyan.aqua.sega.diva.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <url>https://dev.s-ul.eu/mikumiku/minime/wikis/Command/attend</url>
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendRequest extends BaseRequest {
    public String atnd_lut; // Date-Time of last attend state update
    public String atnd_prm1; // Etc. Parameter, only 14 are used, but array of 100 int
    public String atnd_prm2; // Game balance parameter, only 89 are used of 100
    public String atnd_prm3; // Dispersal parameter, only 6 used of 100
}
