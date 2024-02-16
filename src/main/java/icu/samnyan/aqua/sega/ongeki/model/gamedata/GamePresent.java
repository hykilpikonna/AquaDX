package icu.samnyan.aqua.sega.ongeki.model.gamedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiGamePresent")
@Table(name = "ongeki_game_present")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePresent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private long presentId;

    private String presentName;

    private int rewardId;

    // count
    private int stock;

    // acquisitionCondition
    private String message;

    private String startDate = "2000-01-01 05:00:00.0";

    private String endDate = "2099-01-01 05:00:00.0";

    public GamePresent(int presentId, String presentName, int rewardId, int stock, String message) {
        this.presentId = presentId;
        this.presentName = presentName;
        this.rewardId = rewardId;
        this.stock = stock;
        this.message = message;
    }
}
