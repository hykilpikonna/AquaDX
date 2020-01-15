package icu.samnyan.aqua.api.model.req.sega.diva;

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
public class PvListEntry {
    @JsonProperty("PVID")
    private int PVID;
    @JsonProperty("Version")
    private int Version;
    @JsonProperty("Edition")
    private int Edition;
    @JsonProperty("AdvDemo")
    private DatePair AdvDemo;
    @JsonProperty("Playable")
    private DatePair Playable;
}
