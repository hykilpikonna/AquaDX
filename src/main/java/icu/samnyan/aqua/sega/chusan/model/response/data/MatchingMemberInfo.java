package icu.samnyan.aqua.sega.chusan.model.response.data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingMemberInfo {

    private int userId;
    private int regionId;
    private int placeId;
    private String userName;
    private int playerRating;
    private int battleRankId;
    private int battleCorrection;
    private int battleRatingAvg;
    private int optRatingId;
    private int ratingEffectColorId;
    private int trophyId;
    private int nameplateId;
    private int emblemMedal;
    private int emblemBase;
    private int characterId;
    private int characterRank;
    private int skillId;
    private int skillLv;
    private int skillIdForChara;
    @JsonProperty("isJoinTeam")
    private boolean isJoinTeam;
    private String teamName;
    private int teamRank;
    private AvatarEquip avatarEquip;
    private int messageId;
    private String clientId;
    private String romVersion;
    private String dataVersion;
    private int errCnt;
    private int hostErrCnt;
    private int joinTime;
    private List<GenreGraph> genreGraphList;
    
}
