package icu.samnyan.aqua.sega.cardmaker.model.response.data;

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
    private String ongekiCmVersion;
    private String chuniCmVersion;
    private String maimaiCmVersion;
    @JsonProperty("isMaintenance")
    private boolean isMaintenance;
    private int requestInterval;
    private String rebootStartTime;
    private String rebootEndTime;
    @JsonProperty("isBackgroundDistribute")
    private boolean isBackgroundDistribute;
    private int maxCountCharacter;
    private int maxCountItem;
    private int maxCountCard;
    private boolean watermark;
}
