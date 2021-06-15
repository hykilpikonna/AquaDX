package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserLoginBonus")
@Table(name = "maimai2_user_login_bonus")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"bonusId", "point", "isCurrent", "isComplete"})
public class UserLoginBonus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int bonusId;
    private int point;
    @JsonProperty("isCurrent")
    private boolean isCurrent;
    @JsonProperty("isComplete")
    private boolean isComplete;

    public UserLoginBonus(UserDetail user) {
        this.user = user;
    }
}
