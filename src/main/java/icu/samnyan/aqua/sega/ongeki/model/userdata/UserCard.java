package icu.samnyan.aqua.sega.ongeki.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiUserCard")
@Table(name = "ongeki_user_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int cardId = -1;

    private int digitalStock = 1;

    private int analogStock = 0;

    private int level = 0;

    private int maxLevel = 10;

    private int exp = 0;

    private int printCount = 0;

    private int useCount = 0;

    @JsonProperty("isNew")
    private boolean isNew = true;

    private String kaikaDate = "0000-00-00 00:00:00.0";

    private String choKaikaDate = "0000-00-00 00:00:00.0";

    private int skillId;

    @JsonProperty("isAcquired")
    private boolean isAcquired = true;

    private String created = "0000-00-00 00:00:00.0";

    public UserCard(UserData userData) {
        this.user = userData;
    }

    public UserCard(UserData userData, int cardId, int skillId) {
        this.user = userData;
        this.cardId = cardId;
        this.skillId = skillId;
    }
}
