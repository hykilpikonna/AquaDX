package icu.samnyan.aqua.sega.chusan.model.userdata;

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
@Entity(name = "ChusanUserDuel")
@Table(name = "chusan_user_duel", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "duel_id"})})
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

    @Column(name = "duel_id")
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
