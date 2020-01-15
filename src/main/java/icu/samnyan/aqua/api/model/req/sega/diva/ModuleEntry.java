package icu.samnyan.aqua.api.model.req.sega.diva;

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
public class ModuleEntry {
    @JsonProperty("ID")
    private int ID;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Price")
    private int Price;
    @JsonProperty("ReleaseDate")
    private LocalDateTime ReleaseDate;
    @JsonProperty("EndDate")
    private LocalDateTime EndDate;
    @JsonProperty("SortOrder")
    private int SortOrder;
}
