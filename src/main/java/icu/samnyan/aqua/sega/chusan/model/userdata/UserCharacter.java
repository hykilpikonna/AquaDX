package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanUserCharacter")
@Table(name = "chusan_user_character", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "character_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"characterId", "playCount", "level", "friendshipExp", "isValid", "isNewMark", "exMaxLv", "assignIllust", "param1", "param2"})
public class UserCharacter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Chu3UserData user;

    @Column(name = "character_id")
    private int characterId;

    private int playCount = 0;

    private int level = 1;

    private int friendshipExp = 0;

    @JsonProperty("isValid")
    private boolean isValid = true;

    @JsonProperty("isNewMark")
    private boolean isNewMark = true;

    private int exMaxLv = 0;

    private int assignIllust = 0;

    private int param1 = 0;

    private int param2 = 0;

    public UserCharacter(Chu3UserData userData) {
        user = userData;
    }
}
