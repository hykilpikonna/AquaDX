package icu.samnyan.aqua.sega.chusan.model.gamedata;

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
