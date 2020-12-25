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
@Entity(name = "MaimaiUserOption")
@Table(name = "maimai_user_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int soudEffect;

    private int mirrorMode;

    private int guideSpeed;

    private int bgInfo;

    private int brightness;

    private int isStarRot;

    private int breakSe;

    private int slideSe;

    private int hardJudge;

    private int isTagJump;

    private int breakSeVol;

    private int slideSeVol;

    private int isUpperDisp;

    private int trackSkip;

    private int optionMode;

    private int simpleOptionParam;

    private int adjustTiming;

    private int dispTiming;

    private int timingPos;

    private int ansVol;

    private int noteVol;

    private int dmgVol;

    private int appealFlame;

    private int isFeverDisp;

    private int dispJudge;

    private int judgePos;

    private int ratingGuard;

    private int selectChara;

    private int sortType;

    private int filterGenre;

    private int filterLevel;

    private int filterRank;

    private int filterVersion;

    private int filterRec;

    private int filterFullCombo;

    private int filterAllPerfect;

    private int filterDifficulty;

    private int filterFullSync;

    private int filterReMaster;

    private int filterMaxFever;

    private int finalSelectId;

    private int finalSelectCategory;

    public UserOption(UserData user) {
        this.user = user;
    }
}
