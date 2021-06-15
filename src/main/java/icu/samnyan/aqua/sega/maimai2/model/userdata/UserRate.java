package icu.samnyan.aqua.sega.maimai2.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserRate")
@Table(name = "maimai2_user_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int musicId;
    private int level;
    private int romVersion;
    private int achievement;

    public UserRate(UserDetail user) {
        this.user = user;
    }
}
