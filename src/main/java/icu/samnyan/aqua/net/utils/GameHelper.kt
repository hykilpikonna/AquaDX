package icu.samnyan.aqua.net.utils

import ext.minus
import icu.samnyan.aqua.net.db.AquaNetUser
import icu.samnyan.aqua.net.games.GenericGameSummary
import icu.samnyan.aqua.net.games.RankCount
import icu.samnyan.aqua.net.games.TrendOut
import icu.samnyan.aqua.sega.general.model.Card

data class TrendLog(val date: String, val rating: Int)

/**
 * Find the trend of a user's rating
 */
fun findTrend(log: List<TrendLog>): List<TrendOut> {
    // O(n log n)
    val d = log.sortedBy { it.date }.toList()

    // Precompute the play counts for each date in O(n)
    val playCounts = d.groupingBy { it.date }.eachCount()

    // Find the max afterRating on each date
    val maxRating = d.groupingBy { it.date }.fold(0) { acc, e -> maxOf(acc, e.rating) }

    // Use the precomputed play counts
    return d.distinctBy { it.date }
        .map { TrendOut(it.date, maxRating[it.date] ?: 0,
            playCounts[it.date] ?: 0) }
        .sortedBy { it.date }
}

// Here are some interfaces to generalize across multiple games
interface IGenericUserData {
    val userName: String
    val iconId: Int
    val playerRating: Int
    val highestRating: Int
    val firstPlayDate: Any
    val lastPlayDate: Any
    val lastRomVersion: String
    val totalScore: Long
}

interface GenericUserDataRepo {
    fun findByCard(card: Card): IGenericUserData?
    fun getRanking(rating: Int): Long
}

interface IGenericGamePlaylog {
    val musicId: Int
    val level: Int
    val date: Any
    val achievement: Int
    val maxCombo: Int
    val totalCombo: Int
    val beforeRating: Int
    val afterRating: Int
}

interface GenericPlaylogRepo {
    fun findByUserCardExtId(extId: Long): List<IGenericGamePlaylog>
}

fun genericUserSummary(
    u: AquaNetUser,
    userDataRepo: GenericUserDataRepo,
    userPlaylogRepo: GenericPlaylogRepo,
    shownRanks: List<Pair<Int, String>>,
    ratingComposition: Map<String, String>,
): GenericGameSummary {
    // Summary values: total plays, player rating, server-wide ranking
    // number of each rank, max combo, number of full combo, number of all perfect
    val user = userDataRepo.findByCard(u.ghostCard) ?: (404 - "User not found")
    val plays = userPlaylogRepo.findByUserCardExtId(u.ghostCard.extId)

    // O(6n) ranks algorithm: Loop through the entire list of plays,
    // count the number of each rank
    val ranks = shownRanks.associate { (_, v) -> v to 0 }.toMutableMap()
    plays.forEach {
        shownRanks.find { (s, _) -> it.achievement > s }?.let { (_, v) -> ranks[v] = ranks[v]!! + 1 }
    }

    return GenericGameSummary(
        name = user.userName,
        iconId = user.iconId,
        serverRank = userDataRepo.getRanking(user.playerRating),
        accuracy = plays.sumOf { it.achievement }.toDouble() / plays.size,
        rating = user.playerRating,
        ratingHighest = user.highestRating,
        ranks = ranks.map { (k, v) -> RankCount(k, v) },
        maxCombo = plays.maxOf { it.maxCombo },
        fullCombo = plays.count { it.totalCombo == it.maxCombo },
        allPerfect = plays.count { it.achievement == 1010000 },
        totalScore = user.totalScore,
        plays = plays.size,
        totalPlayTime = plays.count() * 3L, // TODO: Give a better estimate
        joined = user.firstPlayDate.toString(),
        lastSeen = user.lastPlayDate.toString(),
        lastVersion = user.lastRomVersion,
        ratingComposition = ratingComposition,
        recent = plays.sortedBy { it.date.toString() }.takeLast(15)
    )
}


