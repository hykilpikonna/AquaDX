package icu.samnyan.aqua.sega.chunithm.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChuniUserDuel")
@Table(name = "chuni_user_duel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDuel {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int duelId;

    private int progress;

    private int point;

    @JsonProperty("isClear")
    private boolean isClear;

    private LocalDateTime lastPlayDate;

    private int param1;

    private int param2;

    private int param3;

    private int param4;

    public UserDuel(UserData userData) {
        user = userData;
    }
}
