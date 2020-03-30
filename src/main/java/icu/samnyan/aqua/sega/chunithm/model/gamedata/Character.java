package icu.samnyan.aqua.sega.chunithm.model.gamedata;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniCharacter")
@Table(name = "chuni_game_character")
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

    private Integer firstSkillId;

    // Format: level:skillId,level:skillId
    // Keep 0 skillId
    private String skills;

    private String addImages;

}
