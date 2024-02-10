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
    data class TrendOut(val date: String, val rating: Int, val plays: Int)

    @GetMapping("trend")
    fun trend(@RequestParam userId: Long): List<TrendOut> {
        // O(n log n) sort
        val d = userPlaylogRepository.findByUser_Card_ExtId(userId).sortedBy { it.playDate }.toList()

        // Precompute the play counts for each date in O(n)
        val playCounts = d.groupingBy { it.playDate }.eachCount()

        // Use the precomputed play counts
        return d.distinctBy { it.playDate }
            .map { TrendOut(it.playDate, it.afterRating, playCounts[it.playDate] ?: 0) }
            .sortedBy { it.date }
    }
}