package icu.samnyan.aqua.sega.chusan.model.gamedata;

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
@Entity(name = "ChusanCharacter")
@Table(name = "chusan_game_character")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String name;

    private String releaseTag;

    private String worksName;

    private String illustratorName;

    private String addImages;

}
