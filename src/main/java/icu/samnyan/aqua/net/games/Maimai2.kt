package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.maimai2.model.*
import icu.samnyan.aqua.sega.maimai2.model.UserDataRepository
import icu.samnyan.aqua.sega.maimai2.model.UserGeneralDataRepository
import icu.samnyan.aqua.sega.maimai2.model.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/mai2")
class Maimai2(
    override val us: AquaUserServices,
    override val playlogRepo: UserPlaylogRepository,
    override val userDataRepo: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController("mai2")
{
    override suspend fun trend(@RP username: Str): List<TrendOut> = us.cardByName(username) { card ->
        findTrend(playlogRepo.findByUserCardExtId(card.extId)
            .map { TrendLog(it.playDate, it.afterRating) })
    }

    // Only show > S rank
    override val shownRanks = mai2Scores.filter { it.first >= 97 * 10000 }

    override suspend fun userSummary(@RP username: Str) = us.cardByName(username) { card ->
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "best35" to (extra["recent_rating"] ?: ""),
            "best15" to (extra["recent_rating_new"] ?: "")
        )

        genericUserSummary(card, ratingComposition)
    }
}