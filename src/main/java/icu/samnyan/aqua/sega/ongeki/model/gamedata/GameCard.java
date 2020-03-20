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
@Entity(name = "OngekiGameCard")
@Table(name = "ongeki_game_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private String name;

    private String nickName;

    private String attribute;

    private int charaId;

    private String school;

    private String gakunen;

    private String rarity;

    // csv
    private String levelParam;

    private int skillId;

    private int choKaikaSkillId;

    private String cardNumber;

    private String version;

}
