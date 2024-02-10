package icu.samnyan.aqua.api.controller.sega.game.maimai2

import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPlaylogRepository
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPlaylog
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api/game/maimai2new")
class Maimai2New(
    private val userPlaylogRepository: UserPlaylogRepository
)
{
    data class TrendOut(val date: String, val rating: Int)

    @GetMapping("trend")
    fun trend(@RequestParam userId: Long): List<TrendOut> {
        // O(n log n) sort TODO: It might be possible to optimize this
        val d = userPlaylogRepository.findByUser_Card_ExtId(userId).sortedBy { it.playDate }.toList()

        // Assume it's sorted by date, map the values O(n)
        val map = d.associate { it.playDate to it.afterRating }

        // Is sorting here necessary?
        return map.map { TrendOut(it.key, it.value) }.sortedBy { it.date }
    }
}