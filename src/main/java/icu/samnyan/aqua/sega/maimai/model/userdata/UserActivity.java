package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiUserActivity")
@Table(name = "maimai_user_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int kind;

    @JsonProperty("id")
    private int activityId;

    private long sortNumber;

    private int param1;

    private int param2;

    private int param3;

    private int param4;

    public UserActivity(UserData user) {
        this.user = user;
    }
}
