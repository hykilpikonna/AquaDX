package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import icu.samnyan.aqua.sega.maimai2.util.IntegerListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserExtend")
@Table(name = "maimai2_user_extend")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"selectMusicId", "selectDifficultyId", "categoryIndex", "musicIndex",
"extraFlag", "selectScoreType", "extendContentBit", "isPhotoAgree", "isGotoCodeRead",
"selectResultDetails", "sortCategorySetting", "sortMusicSetting", "selectedCardList", "encountMapNpcList"})
public class UserExtend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user;

    private int selectMusicId;
    private int selectDifficultyId;
    private int categoryIndex;
    private int musicIndex;
    private int extraFlag;
    private int selectScoreType;
    private long extendContentBit;
    @JsonProperty("isPhotoAgree")
    private boolean isPhotoAgree;
    @JsonProperty("isGotoCodeRead")
    private boolean isGotoCodeRead;
    private boolean selectResultDetails;
    private int sortCategorySetting; //enum SortTabID
    private int sortMusicSetting; //enum SortMusicID
    
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> selectedCardList;
    
    @OneToMany(mappedBy = "userExtend")
    private List<MapEncountNpc> encountMapNpcList = new ArrayList<>();

    public UserExtend(UserDetail user) {
        this.user = user;
    }
}
