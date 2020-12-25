package icu.samnyan.aqua.sega.maimai.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.sega.maimai.model.response.data.GameSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetGameSettingResp {
    @JsonProperty("isAouAccession")
    private boolean isAouAccession;
    private GameSetting gameSetting;
}
