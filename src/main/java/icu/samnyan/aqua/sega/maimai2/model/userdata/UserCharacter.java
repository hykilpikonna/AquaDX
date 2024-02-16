package icu.samnyan.aqua.sega.maimai2.model.userdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
    @JsonInclude
    @Transient
    private int point = 0;
    @JsonInclude
    @Transient
    private int count = 0;
    private int level;
    @JsonInclude
    @Transient
    private int nextAwake = 0;
    @JsonInclude
    @Transient
    private int nextAwakePercent = 0;
    @JsonInclude
    @Transient
    private boolean favorite = false;
    private int awakening;
    private int useCount;

    public UserCharacter(UserDetail user) {
        this.user = user;
    }
}
