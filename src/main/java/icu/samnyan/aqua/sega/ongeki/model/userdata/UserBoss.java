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
@Entity(name = "OngekiUserBoss")
@Table(name = "ongeki_user_boss")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBoss implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int musicId;

    private int damage;

    @JsonProperty("isClear")
    private boolean isClear;

    private int eventId;

    public UserBoss(UserData userData) {
        this.user = userData;
    }
}
