package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import ext.minus
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/maimai2")
class Maimai2(
    val us: AquaUserServices,
    val userPlaylogRepository: UserPlaylogRepository,
    val userDataRepository: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController
{
    override fun trend(@RP username: Str): List<TrendOut> = us.byName(username) { u ->
        // O(n log n) sort
        val d = userPlaylogRepository.findByUser_Card_ExtId(u.ghostCard.extId).sortedBy { it.playDate }.toList()

        // Precompute the play counts for each date in O(n)
        val playCounts = d.groupingBy { it.playDate }.eachCount()

        // Find the max afterRating on each date
        val maxRating = d.groupingBy { it.playDate }.fold(0) { acc, e -> maxOf(acc, e.afterRating) }

        // Use the precomputed play counts
        d.distinctBy { it.playDate }
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

    override fun userSummary(@RP username: Str) = us.byName(username) { u ->
        // Summary values: total plays, player rating, server-wide ranking
        // number of each rank, max combo, number of full combo, number of all perfect
        val user = userDataRepository.findByCard(u.ghostCard) ?: (404 - "User not found")
        val plays = userPlaylogRepository.findByUser_Card_ExtId(u.ghostCard.extId)
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(u.ghostCard.extId)
            .associate { it.propertyKey to it.propertyValue }

        // O(6n) ranks algorithm: Loop through the entire list of plays,
        // count the number of each rank
        val ranks = shownRanks.associate { (_, v) -> v to 0 }.toMutableMap()
        plays.forEach {
            shownRanks.find { (s, _) -> it.achievement > s }?.let { (_, v) -> ranks[v] = ranks[v]!! + 1 }
        }

        GenericGameSummary(
            name = user.userName,
            iconId = user.iconId,
            serverRank = userDataRepository.getRanking(user.playerRating),
            accuracy = plays.sumOf { it.achievement }.toDouble() / plays.size,
            rating = user.playerRating,
            ratingHighest = user.highestRating,
            ranks = ranks.map { (k, v) -> RankCount(k, v) },
            maxCombo = plays.maxOf { it.maxCombo },
            fullCombo = plays.count { it.totalCombo == it.maxCombo },
            allPerfect = plays.count { it.achievement == 1010000 },
            totalScore = user.totalDeluxscore,
            plays = plays.size,
            totalPlayTime = plays.count() * 3L, // TODO: Give a better estimate
            joined = user.firstPlayDate,
            lastSeen = user.lastPlayDate,
            lastVersion = user.lastRomVersion,
            ratingComposition = mapOf(
                "best35" to (extra["recent_rating"] ?: ""),
                "best15" to (extra["recent_rating_new"] ?: "")
            ),
            recent = plays.sortedBy { it.playDate }.takeLast(15).map {
                GenericGamePlaylog(it.playDate, it.achievement, it.maxCombo, it.totalCombo)
            }
        )
    }
}