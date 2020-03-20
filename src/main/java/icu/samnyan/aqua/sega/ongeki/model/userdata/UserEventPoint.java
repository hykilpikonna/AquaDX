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
@Entity(name = "OngekiUserEventPoint")
@Table(name = "ongeki_user_event_point")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventPoint implements Serializable {

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

    @JsonProperty("isRankingRewarded")
    private boolean isRankingRewarded;

    public UserEventPoint(UserData userData) {
        this.user = userData;
    }
}
