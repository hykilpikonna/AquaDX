package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaiMai2UserCourse")
@Table(name = "maimai2_user_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int courseId;
    @JsonProperty("isLastClear")
    private boolean isLastClear;
    private int totalRestlife;
    private int totalAchievement;
    private int totalDeluxscore;
    private int playCount;
    private String clearDate;
    private String lastPlayDate;
    private int bestAchievement;
    private String bestAchievementDate;
    private int bestDeluxscore;
    private String bestDeluxscoreDate;

    public UserCourse(UserDetail user) {
        this.user = user;
    }
}
