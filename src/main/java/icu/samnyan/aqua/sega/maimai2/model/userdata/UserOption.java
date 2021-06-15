package icu.samnyan.aqua.sega.maimai2.model.userdata;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserOption")
@Table(name = "maimai2_user_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOption implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int optionKind;
    private int noteSpeed;
    private int slideSpeed;
    private int touchSpeed;
    private int tapDesign;
    private int holdDesign;
    private int slideDesign;
    private int starType;
    private int outlineDesign;
    private int noteSize;
    private int slideSize;
    private int touchSize;
    private int starRotate;
    private int dispCenter;
    private int dispChain;
    private int dispRate;
    private int dispBar;
    private int touchEffect;
    private int submonitorAnimation;
    private int submonitorAchive;
    private int submonitorAppeal;
    private int matching;
    private int trackSkip;
    private int brightness;
    private int mirrorMode;
    private int dispJudge;
    private int dispJudgePos;
    private int dispJudgeTouchPos;
    private int adjustTiming;
    private int ansVolume;
    private int tapHoldVolume;
    private int criticalSe;
    private int breakSe;
    private int breakVolume;
    private int exSe;
    private int exVolume;
    private int slideSe;
    private int slideVolume;
    private int touchHoldVolume;
    private int damageSeVolume;
    private int headPhoneVolume;
    private int sortTab;
    private int sortMusic;

    public UserOption(UserDetail user) {
        this.user = user;
    }
}
