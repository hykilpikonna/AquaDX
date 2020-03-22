package icu.samnyan.aqua.sega.ongeki.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * For mission event
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiUserMissionPoint")
@Table(name = "ongeki_user_mission_point")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int eventId;

    private long point;

    public UserMissionPoint(UserData userData) {
        this.user = userData;
    }
}
