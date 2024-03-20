package icu.samnyan.aqua.sega.maimai2.model.gamedata

import icu.samnyan.aqua.net.games.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2GameCharge")
@Table(name = "maimai2_game_charge")
@Data
@AllArgsConstructor
@NoArgsConstructor
class GameCharge : Serializable, BaseEntity() {
    val orderId = 0

    @Column(unique = true)
    var chargeId = 0

    val price = 0

    val startDate: String? = null

    val endDate: String? = null

    companion object {
        const val serialVersionUID = 1L
    }
}
