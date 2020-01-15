package icu.samnyan.aqua.sega.chunithm.model.response.data;

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
    private int dataVersion;
    @JsonProperty("isMaintenance")
    private boolean isMaintenance;
    private int requestInterval;
    private int rebootStartTime;
    private int rebootEndTime;
    @JsonProperty("isBackgroundDistribute")
    private boolean isBackgroundDistribute;
    private int maxCountCharacter;
    private int maxCountItem;
    private int maxCountMusic;
}
