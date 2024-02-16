package icu.samnyan.aqua.sega.maimai.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "MaimaiUserPlaylog")
@Table(name = "maimai_user_playlog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlaylog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int orderId;

    private long sortNumber;

    private int placeId;

    private String placeName;

    private String country;

    private int regionId;

    private String playDate;

    private String userPlayDate;

    private int musicId;

    private int level;

    private int gameMode;

    private int rivalNum;

    private int track;

    private int eventId;

    @JsonProperty("isFreeToPlay")
    private boolean isFreeToPlay;

    private int playerRating;

    private long playedUserId1;

    private String playedUserName1;

    private int playedMusicLevel1;

    private long playedUserId2;

    private String playedUserName2;

    private int playedMusicLevel2;

    private long playedUserId3;

    private String playedUserName3;

    private int playedMusicLevel3;

    private int achievement;

    private int score;

    private int tapScore;

    private int holdScore;

    private int slideScore;

    private int breakScore;

    private int syncRate;

    private int vsWin;

    @JsonProperty("isAllPerfect")
    private boolean isAllPerfect;

    private int fullCombo;

    private int maxFever;

    private int maxCombo;

    private int tapPerfect;

    private int tapGreat;

    private int tapGood;

    private int tapBad;

    private int holdPerfect;

    private int holdGreat;

    private int holdGood;

    private int holdBad;

    private int slidePerfect;

    private int slideGreat;

    private int slideGood;

    private int slideBad;

    private int breakPerfect;

    private int breakGreat;

    private int breakGood;

    private int breakBad;

    @JsonProperty("isTrackSkip")
    private boolean isTrackSkip;

    @JsonProperty("isHighScore")
    private boolean isHighScore;

    @JsonProperty("isChallengeTrack")
    private boolean isChallengeTrack;

    private int challengeLife;

    private int challengeRemain;

    private int isAllPerfectPlus;

}
