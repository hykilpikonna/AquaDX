package icu.samnyan.aqua.sega.diva.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.sega.diva.model.common.ChallengeKind;
import icu.samnyan.aqua.sega.diva.model.common.ClearResult;
import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayLog")
@Table(name = "diva_play_log")
@Data
public class PlayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pd_id")
    @JsonIgnore
    private PlayerProfile pdId;

    private int pvId;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private Edition edition;

    private int scriptVer;

    private int score;

    @Enumerated(EnumType.STRING)
    private ChallengeKind challengeKind;

    private int challengeResult;

    @Enumerated(EnumType.STRING)
    private ClearResult clearResult;

    private int vp;

    private int coolCount;

    private int coolPercent;

    private int fineCount;

    private int finePercent;

    private int safeCount;

    private int safePercent;

    private int sadCount;

    private int sadPercent;

    private int wrongCount;

    private int wrongPercent;

    private int maxCombo;

    private int chanceTime;

    private int holdScore;

    private int attainPoint;

    private int skinId;

    private int buttonSe;

    private int buttonSeVol;

    private int sliderSe;

    private int ChainSlideSe;

    private int SliderTouchSe;

    private String modules;

    private int stageCompletion;

    private int slideScore;

    private int isVocalChange;

    private String customizeItems;

//    private String customizeItemFlags;

    private String rhythmGameOptions;

    private int screenShotCount = -1;

    private LocalDateTime dateTime;

    public PlayLog() {
    }

    public PlayLog(PlayerProfile pdId, int pvId, Difficulty difficulty, Edition edition, int scriptVer, int score, ChallengeKind challengeKind, int challengeResult, ClearResult clearResult, int vp, int coolCount, int coolPercent, int fineCount, int finePercent, int safeCount, int safePercent, int sadCount, int sadPercent, int wrongCount, int wrongPercent, int maxCombo, int chanceTime, int holdScore, int attainPoint, int skinId, int buttonSe, int buttonSeVol, int sliderSe, int chainSlideSe, int sliderTouchSe, String modules, int stageCompletion, int slideScore, int isVocalChange, String customizeItems, String rhythmGameOptions, int screenShotCount, LocalDateTime dateTime) {
        this.pdId = pdId;
        this.pvId = pvId;
        this.difficulty = difficulty;
        this.edition = edition;
        this.scriptVer = scriptVer;
        this.score = score;
        this.challengeKind = challengeKind;
        this.challengeResult = challengeResult;
        this.clearResult = clearResult;
        this.vp = vp;
        this.coolCount = coolCount;
        this.coolPercent = coolPercent;
        this.fineCount = fineCount;
        this.finePercent = finePercent;
        this.safeCount = safeCount;
        this.safePercent = safePercent;
        this.sadCount = sadCount;
        this.sadPercent = sadPercent;
        this.wrongCount = wrongCount;
        this.wrongPercent = wrongPercent;
        this.maxCombo = maxCombo;
        this.chanceTime = chanceTime;
        this.holdScore = holdScore;
        this.attainPoint = attainPoint;
        this.skinId = skinId;
        this.buttonSe = buttonSe;
        this.buttonSeVol = buttonSeVol;
        this.sliderSe = sliderSe;
        ChainSlideSe = chainSlideSe;
        SliderTouchSe = sliderTouchSe;
        this.modules = modules;
        this.stageCompletion = stageCompletion;
        this.slideScore = slideScore;
        this.isVocalChange = isVocalChange;
        this.customizeItems = customizeItems;
        this.rhythmGameOptions = rhythmGameOptions;
        this.screenShotCount = screenShotCount;
        this.dateTime = dateTime;
    }
}
