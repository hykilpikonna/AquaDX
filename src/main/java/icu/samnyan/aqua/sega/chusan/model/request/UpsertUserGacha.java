package icu.samnyan.aqua.sega.chusan.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import icu.samnyan.aqua.sega.chusan.model.gamedata.GameGachaCard;
import icu.samnyan.aqua.sega.chusan.model.userdata.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserGacha implements Serializable {

    @Nullable
    private UserData userData;

    @Nullable
    private UserGacha userGacha;

    @Nullable
    private List<Object> userCharacterList;

    @Nullable
    private List<Object> userCardList;

    @Nullable
    private List<GameGachaCard> gameGachaCardList;

    @Nullable
    private List<UserItem> userItemList;

    @Nullable
    @JsonProperty("isNewCharacterList")
    private String isNewCharacterList;

    @Nullable
    @JsonProperty("isNewCardList")
    private String isNewCardList;
    
}
