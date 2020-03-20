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
@Entity(name = "OngekiGameMusic")
@Table(name = "ongeki_game_music")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameMusic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String name;

    private String sortName;

    private String artistName;

    private String genre;

    private int bossCardId;

    private int bossLevel;

    private String level0;

    private String level1;

    private String level2;

    private String level3;

    private String level4;

}
