package icu.samnyan.aqua.sega.ongeki.model.response.data;

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
    private String dataVersion;
    private String onlineDataVersion;
    @JsonProperty("isMaintenance")
    private boolean isMaintenance;
    private int requestInterval;
    private String rebootStartTime;
    private String rebootEndTime;
    @JsonProperty("isBackgroundDistribute")
    private boolean isBackgroundDistribute;
    private int maxCountCharacter;
    private int maxCountCard;
    private int maxCountItem;
    private int maxCountMusic;
    private int maxCountMusicItem;
    private int maxCountRivalMusic;
}
