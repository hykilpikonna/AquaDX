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
@Entity(name = "OngekiUserCharacter")
@Table(name = "ongeki_user_character")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCharacter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int characterId;
    
    private int costumeId;

    private int attachmentId;

    private int playCount;

    private int intimateLevel;

    private int intimateCount;

    private int intimateCountRewarded;

    private String intimateCountDate;

    @JsonProperty("isNew")
    private boolean isNew;

    public UserCharacter(UserData userData) {
        this.user = userData;
    }
}
