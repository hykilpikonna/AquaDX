package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
