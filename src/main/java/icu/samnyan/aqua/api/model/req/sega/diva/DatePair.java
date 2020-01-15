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
public class DatePair {
    @JsonProperty("Start")
    private LocalDateTime Start;
    @JsonProperty("End")
    private LocalDateTime End;
}
