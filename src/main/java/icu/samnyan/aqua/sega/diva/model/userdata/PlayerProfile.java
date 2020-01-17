package icu.samnyan.aqua.sega.diva.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.samnyan.aqua.sega.diva.model.common.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static icu.samnyan.aqua.sega.diva.util.DivaStringUtils.getDummyString;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "DivaPlayerProfile")
@Table(name = "diva_player_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private int pdId;

    private String playerName = "xxx";

    private int vocaloidPoints = 300;

    private int level = 1;

    private int levelExp = 0;

    private String levelTitle = "xxx";

    private int plateId = -1;

    private int plateEffectId = -1;

    @Enumerated(EnumType.STRING)
    private PassStat passwordStatus = PassStat.MISS;

    @JsonIgnore
    private String password = "**********";

    /**
     * Game play customize
     */
    private boolean preferPerPvModule = true;

    private boolean preferCommonModule = false;

    private boolean usePerPvSkin = false;

    private boolean usePerPvButtonSe = false;

    private boolean usePerPvSliderSe = false;

    private boolean usePerPvChainSliderSe = false;

    private boolean usePerPvTouchSliderSe = false;

    private String commonModule = "-999,-999,-999";

    private String commonCustomizeItems = "-999,-999,-999,-999,-999,-999,-999,-999,-999,-999,-999,-999";

    private LocalDateTime commonModuleSetTime = LocalDateTime.now();

    private String moduleSelectItemFlag = "-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1";

    // -1 is disable
    private int commonSkin = -1;

    /**
     * Sound Setting
     */
    private int headphoneVolume = 100;

    private boolean buttonSeOn = true;

    private int buttonSeVolume = 100;

    private int sliderSeVolume = 100;

    private int buttonSe = -1;

    private int chainSlideSe = -1;

    private int slideSe = -1;

    private int sliderTouchSe = -1;

    /**
     * View Setting
     */
    @Enumerated(EnumType.STRING)
    private SortMode sortMode = SortMode.RELEASE_DATE;

    @JsonIgnore
    private int nextPvId = -1;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Difficulty nextDifficulty = Difficulty.NORMAL;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Edition nextEdition = Edition.ORIGINAL;

    private boolean showInterimRanking = true;

    private boolean showClearStatus = true;

    private boolean showClearBorder = true;

    private boolean showRgoSetting = true;

    @JsonIgnore
    private boolean contestNowPlayingEnable = false;

    @JsonIgnore
    private int contestNowPlayingId = -1;

    // Contest now playing progress
    @JsonIgnore
    private int contestNowPlayingValue = -1;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private ContestBorder contestNowPlayingResultRank = ContestBorder.NONE;

    // This store the current progress of contest
    @JsonIgnore
    private String contestNowPlayingSpecifier = "";


    /**
     * MyList, comma separate string
     */
    private String myList0 = getDummyString("-1", 40);

    private String myList1 = getDummyString("-1", 40);

    private String myList2 = getDummyString("-1", 40);

    public PlayerProfile(int pdId, String playerName) {
        this.pdId = pdId;
        this.playerName = playerName;
    }
}
