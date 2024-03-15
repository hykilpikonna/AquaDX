package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import ext.minus
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@API("api/v2/game/mai2")
class Maimai2(
    val us: AquaUserServices,
    val userPlaylogRepository: UserPlaylogRepository,
    val userDataRepository: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController("mai2")
{
    override suspend fun trend(@RP username: Str): List<TrendOut> = us.cardByName(username) { card ->
        findTrend(userPlaylogRepository.findByUser_Card_ExtId(card.extId)
            .map { TrendLog(it.playDate, it.afterRating) })
    }

    // Only show > S rank
    private val shownRanks = mai2Scores.filter { it.first >= 97 * 10000 }

    override suspend fun userSummary(@RP username: Str) = us.cardByName(username) { card ->
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "best35" to (extra["recent_rating"] ?: ""),
            "best15" to (extra["recent_rating_new"] ?: "")
        )

        genericUserSummary(card, userDataRepository, userPlaylogRepository, shownRanks, ratingComposition)
    }

    override suspend fun ranking() = genericRanking(userDataRepository, userPlaylogRepository)

    override suspend fun playlog(@RP id: Long) = userPlaylogRepository.findById(id).getOrNull() ?: (404 - "Playlog not found")

    override suspend fun recent(@RP username: Str) = us.cardByName(username) { card ->
        userPlaylogRepository.findByUser_Card_ExtId(card.extId)
    }
}