package icu.samnyan.aqua.sega.maimai2.model.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSetting {
    @JsonProperty("isMaintenance")
    private boolean isMaintenance;
    private int requestInterval;
    private String rebootStartTime;
    private String rebootEndTime;
    private int movieUploadLimit;
    private int movieStatus;
    private String movieServerUri;
    private String deliverServerUri;
    private String oldServerUri;
    private String usbDlServerUri;
    private int rebootInterval;
}
