package icu.samnyan.aqua.net.games

import ext.API

data class TrendOut(val date: String, val rating: Int, val plays: Int)

data class GenericGamePlaylog(
    val playDate: String,
    val achievement: Int,
    val maxCombo: Int,
    val totalCombo: Int,
    val afterRating: Int
)

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

    val recent: List<GenericGamePlaylog>
)

interface GameApiController {
    @API("trend")
    fun trend(username: String): List<TrendOut>
    @API("user-summary")
    fun userSummary(username: String): GenericGameSummary
}