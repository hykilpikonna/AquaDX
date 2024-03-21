package icu.samnyan.aqua.sega.maimai2.model

import com.fasterxml.jackson.annotation.JsonIgnore
import icu.samnyan.aqua.net.games.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.Data
import java.time.LocalDateTime

@Data @Entity
@Table(name = "maimai2_game_event")
class Mai2GameEvent : BaseEntity() {
    private val type = 0
    private val startDate: String? = null
    private val endDate: String? = null

    @JsonIgnore
    private val enable = false
}

@Data @Entity
@Table(name = "maimai2_game_charge")
class Mai2GameCharge : BaseEntity() {
    @Column(unique = true)
    var chargeId = 0L
    val orderId = 0L
    val price = 0
    val startDate: String? = null
    val endDate: String? = null
}

@Data @Entity
@Table(name = "maimai2_game_selling_card")
class Mai2GameSellingCard : BaseEntity() {
    private val cardId = 0L
    private val startDate: LocalDateTime? = null
    private val endDate: LocalDateTime? = null
    private val noticeStartDate: LocalDateTime? = null
    private val noticeEndDate: LocalDateTime? = null
}
