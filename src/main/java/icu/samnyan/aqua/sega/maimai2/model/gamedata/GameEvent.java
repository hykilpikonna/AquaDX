package icu.samnyan.aqua.sega.maimai2.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2GameEvent")
@Table(name = "maimai2_game_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private int type;

    private String startDate;

    private String endDate;

    @JsonIgnore
    private boolean enable;
}
