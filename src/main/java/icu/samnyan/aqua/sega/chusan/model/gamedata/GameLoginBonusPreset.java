package icu.samnyan.aqua.sega.chusan.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity(name = "ChusanGameLoginBonusPreset")
@Table(name = "chusan_game_login_bonus_preset")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameLoginBonusPreset implements Serializable {
    // No one cares about chuni lol
    // Maimai and Ongeki all got their login bonus but nothing for chunithm

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int version;
    private String presetName;
    private boolean isEnabled;
}
