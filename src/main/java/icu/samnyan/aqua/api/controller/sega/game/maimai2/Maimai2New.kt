package icu.samnyan.aqua.api.controller.sega.game.maimai2

import ext.RP
import ext.invoke
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPlaylogRepository
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("api/game/maimai2new")
class Maimai2New(
    private val userPlaylogRepository: UserPlaylogRepository,
    private val userDataRepository: UserDataRepository,
    private val userGeneralDataRepository: UserGeneralDataRepository
)
{
    data class TrendOut(val date: String, val rating: Int, val plays: Int)

    @GetMapping("trend")
    fun trend(@RP userId: Long): List<TrendOut> {
        // O(n log n) sort
        val d = userPlaylogRepository.findByUser_Card_ExtId(userId).sortedBy { it.playDate }.toList()

        // Precompute the play counts for each date in O(n)
        val playCounts = d.groupingBy { it.playDate }.eachCount()

        // Find the max afterRating on each date
        val maxRating = d.groupingBy { it.playDate }.fold(0) { acc, e -> maxOf(acc, e.afterRating) }

        // Use the precomputed play counts
        return d.distinctBy { it.playDate }
            .map { TrendOut(it.playDate, maxRating[it.playDate] ?: 0,
                playCounts[it.playDate] ?: 0) }
            .sortedBy { it.date }
    }

    private val shownRanks = listOf(
        100.5 to "SSS+",
        100.0 to "SSS",
        99.5  to "SS+",
        99.0  to "SS",
        98.0  to "S+",
        97.0  to "S").map { (k, v) -> (k * 10000).toInt() to v }

    @GetMapping("user-summary")
    fun userSummary(@RP userId: Long): Map<String, Any> {
        // Summary values: total plays, player rating, server-wide ranking
        // number of each rank, max combo, number of full combo, number of all perfect
        val user = userDataRepository.findByCard_ExtId(userId).getOrNull() ?: NOT_FOUND()
        val plays = userPlaylogRepository.findByUser_Card_ExtId(userId)
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(userId)
            .associate { it.propertyKey to it.propertyValue }

        // O(6n) ranks algorithm: Loop through the entire list of plays,
        // count the number of each rank
        val ranks = shownRanks.associate { (_, v) -> v to 0 }.toMutableMap()
        plays.forEach {
            shownRanks.find { (s, _) -> it.achievement > s }?.let { (_, v) -> ranks[v] = ranks[v]!! + 1 }
        }

        return mapOf(
            "name" to user.userName,
            "iconId" to user.iconId,

            "serverRank" to userDataRepository.getRanking(user.playerRating),
            "accuracy" to plays.sumOf { it.achievement } / plays.size,
            "rating" to user.playerRating,
            "ratingHighest" to user.highestRating,
            "ranks" to ranks.map { (k, v) -> mapOf("name" to k, "count" to v) },
            "maxCombo" to plays.maxOf { it.maxCombo },
            "fullCombo" to plays.count { it.totalCombo == it.maxCombo },
            "allPerfect" to plays.count { it.achievement == 1010000 },
            "totalDxScore" to user.totalDeluxscore,

            "plays" to plays.size,
            "totalPlayTime" to plays.count() * 3, // TODO: Make a more accurate estimate
            "joined" to user.firstPlayDate,
            "lastSeen" to user.lastPlayDate,
            "lastVersion" to user.lastRomVersion,

            "best35" to (extra["recent_rating"] ?: ""),
            "best15" to (extra["recent_rating_new"] ?: ""),

            "recent" to plays.sortedBy { it.playDate }.takeLast(10)
        )
    }
}