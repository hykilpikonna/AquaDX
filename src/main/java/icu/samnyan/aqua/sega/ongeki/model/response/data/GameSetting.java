package icu.samnyan.aqua.sega.ongeki.model.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSetting {
    private String dataVersion;
    @JsonProperty("isMaintenance")
    private boolean isMaintenance;
    private int requestInterval;
    private LocalDateTime rebootStartTime;
    private LocalDateTime rebootEndTime;
    @JsonProperty("isBackgroundDistribute")
    private boolean isBackgroundDistribute;
    private int maxCountCharacter;
    private int maxCountItem;
    private int maxCountMusic;
}
