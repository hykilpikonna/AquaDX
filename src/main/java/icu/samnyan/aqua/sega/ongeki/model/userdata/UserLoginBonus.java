package icu.samnyan.aqua.sega.ongeki.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiUserLoginBonus")
@Table(name = "ongeki_user_login_bonus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginBonus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int bonusId;

    private int bonusCount;

    private String lastUpdateDate;

    public UserLoginBonus(UserData userData) {
        this.user = userData;
    }
}
