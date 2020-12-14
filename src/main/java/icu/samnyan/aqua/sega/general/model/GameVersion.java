package icu.samnyan.aqua.sega.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "SegaGameVersion")
@Table(name = "sega_game_version")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameVersion {
    @Id
    private String uuid;
    private String romVersion;
    private String dataVersion;
    private LocalDateTime lastTime;
}
