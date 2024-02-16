package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiUserPresentEvent")
@Table(name = "maimai_user_present_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPresentEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int presentEventId;

    private int point;

    private int presentCount;

    private int rate;

    public UserPresentEvent(UserData user) {
        this.user = user;
    }
}
