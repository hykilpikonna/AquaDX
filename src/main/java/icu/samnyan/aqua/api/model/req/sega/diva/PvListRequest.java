package icu.samnyan.aqua.api.model.req.sega.diva;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PvListRequest {
    @JsonProperty("CreationDate")
    private LocalDateTime CreationDate;
    @JsonProperty("Easy")
    private List<PvListEntry> Easy;
    @JsonProperty("Normal")
    private List<PvListEntry> Normal;
    @JsonProperty("Hard")
    private List<PvListEntry> Hard;
    @JsonProperty("Extreme")
    private List<PvListEntry> Extreme;
}
