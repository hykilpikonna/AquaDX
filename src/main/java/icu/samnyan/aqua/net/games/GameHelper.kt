package icu.samnyan.aqua.net.games

import ext.isoDate
import ext.minus
import icu.samnyan.aqua.sega.general.model.Card
import java.time.LocalDate
import java.util.*

data class TrendLog(val date: String, val rating: Int)

/**
 * Find the trend of a user's rating
 */
fun findTrend(log: List<TrendLog>): List<TrendOut> {

    // Limit to 60 days by filtering out the dates that are too old
    val minDate = LocalDate.now().minusDays(60).isoDate()
    val now = LocalDate.now().isoDate()

    // O(n log n)
    val d = log.sortedBy { it.date }.toList()
    if (d.isEmpty()) return listOf()

    // Precompute the play counts for each date in O(n)
    val playCounts = d.groupingBy { it.date }.eachCount()

    // Find the max afterRating on each date
    val maxRating = d.groupingBy { it.date }.fold(0) { acc, e -> maxOf(acc, e.rating) }

    // Use the precomputed play counts
    val trend = d.distinctBy { it.date }
        .map { TrendOut(it.date, maxRating[it.date] ?: 0,
            playCounts[it.date] ?: 0) }
        .sortedBy { it.date }.toMutableList()

    // Fill in the missing dates (min date and current date)
    trend[0].let { if (it.date > minDate) trend.add(0, TrendOut(minDate, 0, 0)) }
    trend.last().let { if (it.date != now) trend.add(TrendOut(now, it.rating, 0)) }

    return trend
}

fun List<IGenericGamePlaylog>.acc() = if (isEmpty()) 0.0 else sumOf { it.achievement }.toDouble() / size / 10000.0

fun GameApiController.genericUserSummary(
    card: Card,
    ratingComposition: Map<String, String>,
): GenericGameSummary {
    // Summary values: total plays, player rating, server-wide ranking
    // number of each rank, max combo, number of full combo, number of all perfect
    val user = userDataRepo.findByCard(card) ?: (404 - "Game data not found")
    val plays = playlogRepo.findByUserCardExtId(card.extId)

    // Detailed ranks: Find the number of each rank in each level category
    // map<level, map<rank, count>>
    val rankMap = shownRanks.associate { (_, v) -> v to 0 }
    val detailedRanks = HashMap<Int, MutableMap<String, Int>>()
    plays.forEach { play ->
        val lvl = musicMapping[play.musicId]?.notes?.getOrNull(if (play.level == 10) 0 else play.level)?.lv ?: return@forEach
        shownRanks.find { (s, _) -> play.achievement > s }?.let { (_, v) ->
            val ranks = detailedRanks.getOrPut(lvl.toInt()) { rankMap.toMutableMap() }
            ranks[v] = ranks[v]!! + 1
        }
    }

    // Collapse detailed ranks to get non-detailed ranks map<rank, count>
    val ranks = shownRanks.associate { (_, v) -> v to 0 }.toMutableMap().also { ranks ->
        plays.forEach { play ->
            shownRanks.find { (s, _) -> play.achievement > s }?.let { (_, v) -> ranks[v] = ranks[v]!! + 1 }
        }
    }

    return GenericGameSummary(
        name = user.userName,
        iconId = user.iconId,
        aquaUser = card.aquaUser?.publicFields,
        serverRank = userDataRepo.getRanking(user.playerRating),
        accuracy = plays.acc(),
        rating = user.playerRating,
        ratingHighest = user.highestRating,
        ranks = ranks.map { (k, v) -> RankCount(k, v) },
        detailedRanks = detailedRanks,
        maxCombo = plays.maxOfOrNull { it.maxCombo } ?: 0,
        fullCombo = plays.count { it.isFullCombo },
        allPerfect = plays.count { it.isAllPerfect },
        totalScore = user.totalScore,
        plays = plays.size,
        totalPlayTime = plays.count() * 3L, // TODO: Give a better estimate
        joined = user.firstPlayDate.toString(),
        lastSeen = user.lastPlayDate.toString(),
        lastVersion = user.lastRomVersion,
        ratingComposition = ratingComposition,
        recent = plays.sortedBy { it.userPlayDate.toString() }.takeLast(15).reversed()
    )
}