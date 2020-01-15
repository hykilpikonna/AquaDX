package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserGameOption")
@Table(name = "chuni_user_game_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "bgInfo",
        "fieldColor",
        "guideSound",
        "soundEffect",
        "guideLine",
        "speed",
        "optionSet",
        "matching",
        "judgePos",
        "rating",
        "judgeJustice",
        "judgeAttack",
        "headphone",
        "playerLevel",
        "successTap",
        "successExTap",
        "successSlideHold",
        "successAir",
        "successFlick",
        "successSkill",
        "successTapTimbre",
        "privacy"
})
public class UserGameOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int bgInfo;

    private int fieldColor;

    private int guideSound;

    private int soundEffect;

    private int guideLine;

    private int speed;

    private int optionSet;

    private int matching;

    private int judgePos;

    private int rating;

    private int judgeJustice;

    private int judgeAttack;

    private int headphone;

    private int playerLevel;

    private int successTap;

    private int successExTap;

    private int successSlideHold;

    private int successAir;

    private int successFlick;

    private int successSkill;

    private int successTapTimbre;

    private int privacy;


    public UserGameOption(UserData userData) {
        user = userData;
    }
}
