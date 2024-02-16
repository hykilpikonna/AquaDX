package icu.samnyan.aqua.sega.chusan.model.gamedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "ChusanGameLoginBonus")
@Table(name = "chusan_game_login_bonus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameLoginBonus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    private int version;
    private int presetId;
    private int loginBonusId;
    private String loginBonusName;
    private int presentId;
    private String presentName;
    private int itemNum;
    private int needLoginDayCount;
    private int loginBonusCategoryType;
}
