package icu.samnyan.aqua.sega.maimai.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiGameEvent")
@Table(name = "maimai_game_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    public int type;
    @JsonProperty("id")
    public int eventId;
    public String startDate;
    public String endDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
}
