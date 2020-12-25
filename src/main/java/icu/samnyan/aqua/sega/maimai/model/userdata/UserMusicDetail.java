package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaiMaiUserMusicDetail")
@Table(name = "maimai_user_music_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMusicDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int musicId;

    private int level;

    private int playCount;

    private int scoreMax;

    private int syncRateMax;

    private boolean isAllPerfect;

    private int isAllPerfectPlus;

    private int fullCombo;

    private int maxFever;

    private int achievement;

    public UserMusicDetail(UserData user) {
        this.user = user;
    }
}
