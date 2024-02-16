package icu.samnyan.aqua.sega.ongeki.model.userdata;

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
@Entity(name = "OngekiUserTechEvent")
@Table(name = "ongeki_user_tech_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTechEvent implements Serializable {

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

    private int totalTechScore;

    private int totalPlatinumScore;

    private String techRecordDate;

    @JsonProperty("isRankingRewarded")
    private boolean isRankingRewarded;

    @JsonProperty("isTotalTechNewRecord")
    private boolean isTotalTechNewRecord;

    public UserTechEvent(UserData userData) {
        this.user = userData;
    }
}
