package icu.samnyan.aqua.net.utils

import ext.isoDate
import ext.millis
import ext.minus
import icu.samnyan.aqua.net.games.GenericGameSummary
import icu.samnyan.aqua.net.games.GenericRankingPlayer
import icu.samnyan.aqua.net.games.RankCount
import icu.samnyan.aqua.net.games.TrendOut
import icu.samnyan.aqua.sega.general.model.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.time.LocalDate

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
    trend[0].let { if (it.date != minDate) trend.add(0, TrendOut(minDate, 0, 0)) }
    trend.last().let { if (it.date != now) trend.add(TrendOut(now, it.rating, 0)) }

    return trend
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
    var card: Card?
}

@NoRepositoryBean
interface GenericUserDataRepo<T : IGenericUserData, ID> : JpaRepository<T, ID> {
    fun findByCard(card: Card): T?
    fun getRanking(rating: Int): Long
}

interface IGenericGamePlaylog {
    val musicId: Int
    val level: Int
    val userPlayDate: Any
    val achievement: Int
    val maxCombo: Int
    val isFullCombo: Boolean
    val beforeRating: Int
    val afterRating: Int
    val isAllPerfect: Boolean
}

interface GenericPlaylogRepo {
    fun findByUserCardExtId(extId: Long): List<IGenericGamePlaylog>
}

fun List<IGenericGamePlaylog>.acc() = if (isEmpty()) 0.0 else sumOf { it.achievement }.toDouble() / size / 10000.0

fun genericUserSummary(
    card: Card,
    userDataRepo: GenericUserDataRepo<*, *>,
    userPlaylogRepo: GenericPlaylogRepo,
    shownRanks: List<Pair<Int, String>>,
    ratingComposition: Map<String, String>,
): GenericGameSummary {
    // Summary values: total plays, player rating, server-wide ranking
    // number of each rank, max combo, number of full combo, number of all perfect
    val user = userDataRepo.findByCard(card) ?: (404 - "Game data not found")
    val plays = userPlaylogRepo.findByUserCardExtId(card.extId)

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
        accuracy = plays.acc(),
        rating = user.playerRating,
        ratingHighest = user.highestRating,
        ranks = ranks.map { (k, v) -> RankCount(k, v) },
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

val rankingCache = mutableMapOf<String, Pair<Long, List<GenericRankingPlayer>>>()

fun genericRanking(
    userDataRepo: GenericUserDataRepo<*, *>,
    userPlaylogRepo: GenericPlaylogRepo,
): List<GenericRankingPlayer> {
    // Read from cache if we just computed it less than 2 minutes ago
    val cacheKey = userPlaylogRepo::class.java.name
    rankingCache[cacheKey]?.let { (t, r) ->
        if (millis() - t < 120_000) return r
    }

    // TODO: pagination
    val players = userDataRepo.findAll().sortedByDescending { it.playerRating }
    return players.filter { it.card != null }.mapIndexed { i, user ->
        val plays = userPlaylogRepo.findByUserCardExtId(user.card!!.extId)

        GenericRankingPlayer(
            rank = i + 1,
            name = user.userName,
            accuracy = plays.acc(),
            rating = user.playerRating,
            allPerfect = plays.count { it.isAllPerfect },
            fullCombo = plays.count { it.isFullCombo },
            lastSeen = user.lastPlayDate.toString(),
            username = user.card!!.aquaUser?.username ?: "user${user.card!!.id}"
        )
    }.also { rankingCache[cacheKey] = millis() to it }  // Update the cache
}
