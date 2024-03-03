package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import icu.samnyan.aqua.net.utils.IGenericGamePlaylog

data class TrendOut(val date: String, val rating: Int, val plays: Int)

data class RankCount(val name: String, val count: Int)

data class GenericGameSummary(
    val name: String,
    val iconId: Int,

    val serverRank: Long,
    val accuracy: Double,
    val rating: Int,
    val ratingHighest: Int,
    val ranks: List<RankCount>,
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

interface GameApiController {
    @API("trend")
    suspend fun trend(@RP username: String): List<TrendOut>
    @API("user-summary")
    suspend fun userSummary(@RP username: String): GenericGameSummary
    @API("ranking")
    suspend fun ranking(): List<GenericRankingPlayer>
    @API("playlog")
    suspend fun playlog(@RP id: Long): IGenericGamePlaylog
    @API("recent")
    suspend fun recent(@RP username: String): List<IGenericGamePlaylog>
}