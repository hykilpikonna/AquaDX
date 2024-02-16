package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import icu.samnyan.aqua.sega.util.jackson.AccessCodeSerializer;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.maimai2.util.IntegerListConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserData")
@Table(name = "maimai2_user_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonSerialize(using = AccessCodeSerializer.class)
    @JsonProperty(value = "accessCode", access = JsonProperty.Access.READ_ONLY)
    @OneToOne
    @JoinColumn(name = "aime_card_id")
    private Card card;

    private String userName;
    @JsonInclude
    @Transient
    private String friendCode = "";
    private int isNetMember;
    @JsonInclude
    @Transient
    private int nameplateId = 0;
    private int iconId;
    @JsonInclude
    @Transient
    private int trophyId = 0;
    private int plateId;
    private int titleId;
    private int partnerId;
    private int frameId;
    private int selectMapId;
    private int totalAwake;
    private int gradeRating;
    private int musicRating;
    private int playerRating;
    private int highestRating;
    private int gradeRank;
    private int classRank;
    private int courseRank;

    @Convert(converter = IntegerListConverter.class)
    private List<Integer> charaSlot; // Entries: 5

    @Convert(converter = IntegerListConverter.class)
    private List<Integer> charaLockSlot; // Entries: 5

    private long contentBit;
    private int playCount;
    private String eventWatchedDate;
    private String lastGameId;
    private String lastRomVersion;
    private String lastDataVersion;
    private String lastLoginDate;
    private String lastPlayDate;
    private int lastPlayCredit;
    private int lastPlayMode;
    private int lastPlaceId;
    private String lastPlaceName;
    private int lastAllNetId;
    private int lastRegionId;
    private String lastRegionName;
    private String lastClientId;
    private String lastCountryCode;
    private int lastSelectEMoney;
    private int lastSelectTicket;
    private int lastSelectCourse;
    private int lastCountCourse;
    private String firstGameId;
    private String firstRomVersion;
    private String firstDataVersion;
    private String firstPlayDate;
    private String compatibleCmVersion;
    private String dailyBonusDate;
    private String dailyCourseBonusDate;
    private String lastPairLoginDate;
    private String lastTrialPlayDate;
    private int playVsCount;
    private int playSyncCount;
    private int winCount;
    private int helpCount;
    private int comboCount;
    private long totalDeluxscore;
    private long totalBasicDeluxscore;
    private long totalAdvancedDeluxscore;
    private long totalExpertDeluxscore;
    private long totalMasterDeluxscore;
    private long totalReMasterDeluxscore;
    @JsonInclude
    @Transient
    private long totalHiscore = 0;
    @JsonInclude
    @Transient
    private long totalBasicHighscore = 0;
    @JsonInclude
    @Transient
    private long totalAdvancedHighscore = 0;
    @JsonInclude
    @Transient
    private long totalExpertHighscore = 0;
    @JsonInclude
    @Transient
    private long totalMasterHighscore = 0;
    @JsonInclude
    @Transient
    private long totalReMasterHighscore = 0;
    private int totalSync;
    private int totalBasicSync;
    private int totalAdvancedSync;
    private int totalExpertSync;
    private int totalMasterSync;
    private int totalReMasterSync;
    private long totalAchievement;
    private long totalBasicAchievement;
    private long totalAdvancedAchievement;
    private long totalExpertAchievement;
    private long totalMasterAchievement;
    private long totalReMasterAchievement;
    private long playerOldRating;
    private long playerNewRating;
    private int banState;
    private long dateTime;
    @JsonInclude
    @Transient
    private int cmLastEmoneyBrand = 2;
    @JsonInclude
    @Transient
    private int cmLastEmoneyCredit = 69;
    private int mapStock;
}
