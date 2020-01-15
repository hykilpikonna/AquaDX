package icu.samnyan.aqua.sega.chunithm.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.sega.chunithm.model.response.data.GameSetting;
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

    private GameSetting gameSetting;
    @JsonProperty("isDumpUpload")
    private boolean isDumpUpload;
    @JsonProperty("isAou")
    private boolean isAou;
}
