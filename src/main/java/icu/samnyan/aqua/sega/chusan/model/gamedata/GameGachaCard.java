package icu.samnyan.aqua.sega.chusan.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "ChusanGameGachaCard")
@Table(name = "chusan_game_gacha_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameGachaCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int gachaId;
    private int cardId;
    private int rarity;
    private int weight;
    @JsonProperty("isPickup")
    private boolean isPickup;
}
