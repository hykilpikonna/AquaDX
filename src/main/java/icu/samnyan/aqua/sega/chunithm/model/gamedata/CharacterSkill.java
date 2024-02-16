package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniCharacterSkill")
@Table(name = "chuni_game_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String name;

    private String category;
}
