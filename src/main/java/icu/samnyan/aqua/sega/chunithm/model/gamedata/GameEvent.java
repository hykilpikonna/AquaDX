package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniGameEvent")
@Table(name = "chuni_game_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private int type;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @JsonIgnore
    private boolean enable;
}
