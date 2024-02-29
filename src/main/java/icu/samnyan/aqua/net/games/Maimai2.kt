package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/mai2")
class Maimai2(
    val us: AquaUserServices,
    val userPlaylogRepository: UserPlaylogRepository,
    val userDataRepository: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController
{
    override fun trend(@RP username: Str): List<TrendOut> = us.byName(username) { u ->
        findTrend(userPlaylogRepository.findByUser_Card_ExtId(u.ghostCard.extId)
            .map { TrendLog(it.playDate, it.afterRating) })
    }

    // Only show > S rank
    private val shownRanks = mai2Scores.filter { it.first >= 97 * 10000 }

    override fun userSummary(@RP username: Str) = us.byName(username) { u ->
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(u.ghostCard.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "best35" to (extra["recent_rating"] ?: ""),
            "best15" to (extra["recent_rating_new"] ?: "")
        )

        genericUserSummary(u, userDataRepository, userPlaylogRepository, shownRanks, ratingComposition)
    }

    override fun ranking() = genericRanking(userDataRepository, userPlaylogRepository)
}