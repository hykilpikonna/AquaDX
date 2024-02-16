package icu.samnyan.aqua.sega.chusan.model.userdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanUserGameOption")
@Table(name = "chusan_user_game_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
    "bgInfo",
    "fieldColor",
    "guideSound",
    "soundEffect",
    "guideLine",
    "speed",
    "optionSet",
    "matching",
    "judgePos",
    "rating",
    "judgeCritical",
    "judgeJustice",
    "judgeAttack",
    "headphone",
    "playerLevel",
    "successTap",
    "successExTap",
    "successSlideHold",
    "successAir",
    "successFlick",
    "successSkill",
    "successTapTimbre",
    "privacy",
    "mirrorFumen",
    "selectMusicFilterLv",
    "sortMusicFilterLv",
    "sortMusicGenre",
    "categoryDetail",
    "judgeTimingOffset",
    "playTimingOffset",
    "fieldWallPosition",
    "resultVoiceShort",
    "notesThickness",
    "judgeAppendSe",
    "trackSkip",
    "hardJudge",
    "speed_120",
    "fieldWallPosition_120",
    "playTimingOffset_120",
    "judgeTimingOffset_120",
    "ext1",
    "ext2",
    "ext3",
    "ext4",
    "ext5",
    "ext6",
    "ext7",
    "ext8",
    "ext9",
    "ext10"
})
public class UserGameOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData user;

    private int bgInfo;

    private int fieldColor;

    private int guideSound;

    private int soundEffect;

    private int guideLine;

    private int speed;

    private int optionSet;

    private int matching;

    private int judgePos;

    private int rating;

    private int judgeCritical;

    private int judgeJustice;

    private int judgeAttack;

    private int headphone;

    private int playerLevel;

    private int successTap;

    private int successExTap;

    private int successSlideHold;

    private int successAir;

    private int successFlick;

    private int successSkill;

    private int successTapTimbre;

    private int privacy;

    private int mirrorFumen;

    private int selectMusicFilterLv;

    private int sortMusicFilterLv;

    private int sortMusicGenre;

    private int categoryDetail;

    private int judgeTimingOffset;

    private int playTimingOffset;

    private int fieldWallPosition;

    private int resultVoiceShort;

    private int notesThickness;

    private int judgeAppendSe;

    private int trackSkip;

    private int hardJudge;

    private int speed_120;

    private int fieldWallPosition_120;

    private int playTimingOffset_120;

    private int judgeTimingOffset_120;

    private int ext1;

    private int ext2;

    private int ext3;

    private int ext4;

    private int ext5;

    private int ext6;

    private int ext7;

    private int ext8;

    private int ext9;

    private int ext10;

    public UserGameOption(UserData userData) {
        user = userData;
    }
}
