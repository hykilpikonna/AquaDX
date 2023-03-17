package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaiMai2UserMusicDetail")
@Table(name = "maimai2_user_music_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMusicDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int musicId;
    private int level;
    private int playCount;
    private int achievement;
    private int comboStatus;
    private int syncStatus;
    private int deluxscoreMax;
    private int scoreRank;
    private int extNum1;

    public UserMusicDetail(UserDetail user) {
        this.user = user;
    }
}
