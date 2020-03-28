package icu.samnyan.aqua.sega.ongeki.model.gamedata;

import icu.samnyan.aqua.sega.ongeki.model.common.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "OngekiGameReward")
@Table(name = "ongeki_game_reward")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private long rewardId;

    @Enumerated(EnumType.ORDINAL)
    private ItemType itemKind;

    private int itemId;

    public GameReward(int rewardId, ItemType itemKind, int itemId) {
        this.rewardId = rewardId;
        this.itemKind = itemKind;
        this.itemId = itemId;
    }
}
