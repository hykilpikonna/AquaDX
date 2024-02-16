package icu.samnyan.aqua.sega.maimai2.model.userdata;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserFriendSeasonRanking")
@Table(name = "maimai2_user_friend_season_ranking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendSeasonRanking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int seasonId;
    private int point;
    @Column(name = "\"rank\"")
    private int rank;
    private boolean rewardGet;
    private String userName;
    private String recordDate;

    public UserFriendSeasonRanking(UserDetail user) {
        this.user = user;
    }
}