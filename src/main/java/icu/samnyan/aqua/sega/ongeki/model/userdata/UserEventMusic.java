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
@Entity(name = "OngekiUserEventMusic")
@Table(name = "ongeki_user_event_music")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventMusic implements Serializable {

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

    private int type;

    private int musicId;

    private int level;

    private int techScoreMax;

    private int platinumScoreMax;

    public String techRecordDate;

    @JsonProperty("isTechNewRecord")
    public boolean isTechNewRecord;

    public UserEventMusic(UserData userData) {
        this.user = userData;
    }
}
