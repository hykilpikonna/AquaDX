package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserCharacter")
@Table(name = "chuni_user_character")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"characterId", "playCount", "level", "skillId", "friendshipExp", "isValid", "isNewMark", "param1", "param2"})
public class UserCharacter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int characterId;

    private int playCount;

    private int level;

    private int skillId;

    private int friendshipExp;

    @JsonProperty("isValid")
    private boolean isValid;

    @JsonProperty("isNewMark")
    private boolean isNewMark;

    private int param1;

    private int param2;

    public UserCharacter(UserData userData) {
        user = userData;
    }
}
