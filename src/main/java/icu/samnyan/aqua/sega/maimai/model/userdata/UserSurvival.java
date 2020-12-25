package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Survival Course
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiUserSurvival")
@Table(name = "maimai_user_survival")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSurvival implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int survivalId;

    private int totalScore;

    private int totalAchieve;

    @JsonProperty("isClear")
    private boolean isClear;

    @JsonProperty("isNoDamage")
    private boolean isNoDamage;

    public UserSurvival(UserData user) {
        this.user = user;
    }
}