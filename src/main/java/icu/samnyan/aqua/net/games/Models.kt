package icu.samnyan.aqua.net.games

import ext.API
import ext.JSON
import ext.RP
import icu.samnyan.aqua.net.utils.IGenericGamePlaylog
import kotlinx.serialization.Serializable

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

    @API("trend")
    abstract suspend fun trend(@RP username: String): List<TrendOut>
    @API("user-summary")
    abstract suspend fun userSummary(@RP username: String): GenericGameSummary
    @API("ranking")
    abstract suspend fun ranking(): List<GenericRankingPlayer>
    @API("playlog")
    abstract suspend fun playlog(@RP id: Long): IGenericGamePlaylog
    @API("recent")
    abstract suspend fun recent(@RP username: String): List<IGenericGamePlaylog>
}