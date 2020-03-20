package icu.samnyan.aqua.sega.ongeki.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiGameEvent")
@Table(name = "ongeki_game_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;
}
