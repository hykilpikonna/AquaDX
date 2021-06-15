package icu.samnyan.aqua.sega.maimai2.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserCharacter")
@Table(name = "maimai2_user_character")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCharacter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int characterId;
    private int level;
    private int awakening;
    private int useCount;

    public UserCharacter(UserDetail user) {
        this.user = user;
    }
}
