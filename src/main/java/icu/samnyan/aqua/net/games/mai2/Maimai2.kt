package icu.samnyan.aqua.net.games.mai2

import ext.*
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.games.*
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.maimai2.model.*
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@API("api/v2/game/mai2")
class Maimai2(
    override val us: AquaUserServices,
    override val playlogRepo: Mai2UserPlaylogRepo,
    override val userDataRepo: Mai2UserDataRepo,
    val repos: Mai2Repos,
): GameApiController<Mai2UserDetail>("mai2", Mai2UserDetail::class) {
    override suspend fun trend(@RP username: Str): List<TrendOut> = us.cardByName(username) { card ->
        findTrend(playlogRepo.findByUserCardExtId(card.extId)
            .map { TrendLog(it.playDate, it.afterRating) })
    }

    // Only show > S rank
    override val shownRanks = mai2Scores.filter { it.first >= 97 * 10000 }
    override val settableFields: Map<String, (Mai2UserDetail, String) -> Unit> by lazy { mapOf(
        "userName" to usernameCheck(SEGA_USERNAME_CAHRS),
    ) }

    override suspend fun userSummary(@RP username: Str) = us.cardByName(username) { card ->
        val extra = repos.userGeneralData.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "best35" to (extra["recent_rating"] ?: ""),
            "best15" to (extra["recent_rating_new"] ?: "")
        )

        genericUserSummary(card, ratingComposition)
    }

    @API("user-rating")
    suspend fun userRating(@RP username: Str) = us.cardByName(username) { card ->
        val extra = repos.userGeneralData.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }
        val b35Str = extra["recent_rating"] ?: (400 - "No rating found")
        val b15Str = extra["recent_rating_new"] ?: (400 - "No rating found")

        val b35 = b35Str.split(',').map { it.split(':') }
        val b15 = b15Str.split(',').map { it.split(':') }

        val musicIdList = listOf(
            b35.map { it[0].toInt() },
            b15.map { it[0].toInt() },
        ).flatten()

        val userMusicList = repos.userMusicDetail.findByUser_Card_ExtIdAndMusicIdIn(card.extId, musicIdList)

        // Dont leak extId
        mapOf(
            "best35" to b35,
            "best15" to b15,
            "musicList" to userMusicList,
        )
    }

    @API("user-name-plate")
    suspend fun userNamePlate(@RP username: Str) = us.cardByName(username) { card ->
        val userData = repos.userData.findByCardExtId(card.extId).orElse(null) ?: (404 - "User not found")
        mapOf(
            "iconId" to userData.iconId,
            "plateId" to userData.plateId,
            "titleId" to userData.titleId,
            "classRank" to userData.classRank,
            "playerRating" to userData.playerRating,
            "userName" to userData.userName,
            "courseRank" to userData.courseRank,
        )
    }

    @API("user-favorite")
    suspend fun userFavorite(@RP username: Str) = us.cardByName(username) { card ->
        repos.userFavorite.findByUser_Card_ExtId(card.extId)
    }

    @API("user-music-from-list")
    suspend fun userMusicFromList(@RP username: Str, @RB musicList: List<Int>) = us.cardByName(username) { card ->
        repos.userMusicDetail.findByUser_Card_ExtIdAndMusicIdIn(card.extId, musicList)
    }

    @PostMapping("change-name")
    suspend fun changeName(@RP token: String, @RP newName: String) = us.jwt.auth(token) { u ->
        val newNameFull = toFullWidth(newName)
        us.cardByName(u.username) { card ->
            val user = userDataRepo.findByCard(card) ?: (404 - "User not found")
            settableFields["userName"]?.invoke(user, newNameFull)
            userDataRepo.save(user)
        }
        mapOf("newName" to newNameFull)
    }
}
