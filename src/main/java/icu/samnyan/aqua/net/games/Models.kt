package icu.samnyan.aqua.net.games

import ext.*
import icu.samnyan.aqua.net.utils.*
import kotlinx.serialization.Serializable
import kotlin.jvm.optionals.getOrNull

data class TrendOut(val date: String, val rating: Int, val plays: Int)

data class RankCount(val name: String, val count: Int)

data class GenericGameSummary(
    val name: String,
    val iconId: Int,

    val aquaUser: Map<String, Any?>?,

    val serverRank: Long,
    val accuracy: Double,
    val rating: Int,
    val ratingHighest: Int,
    val ranks: List<RankCount>,
    val detailedRanks: Map<Int, Map<String, Int>>,
    val maxCombo: Int,
    val fullCombo: Int,
    val allPerfect: Int,
    val totalScore: Long,

    val plays: Int,
    val totalPlayTime: Long,
    val joined: String,
    val lastSeen: String,
    val lastVersion: String,

    val ratingComposition: Map<String, Any>,

    val recent: List<IGenericGamePlaylog>
)

data class GenericRankingPlayer(
    val rank: Int,
    val name: String,
    val username: String?,
    val accuracy: Double,
    val rating: Int,
    val allPerfect: Int,
    val fullCombo: Int,
    val lastSeen: String
)

@Serializable
data class GenericMusicMeta(
    val name: String?,
    val ver: String,
    val notes: List<GenericNoteMeta>
)

@Serializable
data class GenericNoteMeta(
    val lv: Double,
    val lvId: Int
)

abstract class GameApiController(name: String) {
    val musicMapping: Map<Int, GenericMusicMeta> = GameApiController::class.java
        .getResourceAsStream("/meta/$name/music.json")
        .use { it?.reader()?.readText() }
        ?.let { JSON.decodeFromString<Map<String, GenericMusicMeta>>(it) }
        ?.mapKeys { it.key.toInt() }
        ?: emptyMap()

    abstract val userDataRepo: GenericUserDataRepo<*, *>
    abstract val playlogRepo: GenericPlaylogRepo
    abstract val shownRanks: List<Pair<Int, String>>

    @API("trend")
    abstract suspend fun trend(@RP username: String): List<TrendOut>
    @API("user-summary")
    abstract suspend fun userSummary(@RP username: String): GenericGameSummary
    @API("recent")
    abstract suspend fun recent(@RP username: String): List<IGenericGamePlaylog>


    private val rankingCache = mutableMapOf<String, Pair<Long, List<GenericRankingPlayer>>>()
    @API("ranking")
    fun ranking(): List<GenericRankingPlayer> {
        // Read from cache if we just computed it less than 2 minutes ago
        val cacheKey = playlogRepo::class.java.name
        rankingCache[cacheKey]?.let { (t, r) ->
            if (millis() - t < 120_000) return r
        }

        // TODO: pagination
        val players = userDataRepo.findAll().sortedByDescending { it.playerRating }
        return players.filter { it.card != null }.mapIndexed { i, user ->
            val plays = playlogRepo.findByUserCardExtId(user.card!!.extId)

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

    @API("playlog")
    fun playlog(@RP id: Long): IGenericGamePlaylog = playlogRepo.findById(id).getOrNull() ?: (404 - "Playlog not found")
}