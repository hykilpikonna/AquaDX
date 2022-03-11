package icu.samnyan.aqua.sega.chusan.model.response.data;

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
    private String romVersion;
    private String dataVersion;
    @JsonProperty("isMaintenance")
    private boolean isMaintenance;
    private int requestInterval;
    private String rebootStartTime;
    private String rebootEndTime;
    @JsonProperty("isBackgroundDistribute")
    private boolean isBackgroundDistribute;
    private int maxCountCharacter;
    private int maxCountItem;
    private int maxCountMusic;
    private String matchStartTime;
    private String matchEndTime;
    private int matchTimeLimit;
    private int matchErrorLimit;
    private String matchingUri;
    private String udpHolePunchUri;
    private String reflectorUri;
}
