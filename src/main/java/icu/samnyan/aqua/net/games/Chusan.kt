package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import ext.minus
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@API("api/v2/game/chu3")
class Chusan(
    val us: AquaUserServices,
    val userPlaylogRepository: UserPlaylogRepository,
    val userDataRepository: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController("chu3")
{
    override suspend fun trend(@RP username: Str): List<TrendOut> = us.cardByName(username) { card ->
        findTrend(userPlaylogRepository.findByUser_Card_ExtId(card.extId)
            .map { TrendLog(it.playDate.toString(), it.playerRating) })
    }

    // Only show > AAA rank
    private val shownRanks = chu3Scores.filter { it.first >= 95 * 10000 }

    override suspend fun userSummary(@RP username: Str) = us.cardByName(username) { card ->
        // Summary values: total plays, player rating, server-wide ranking
        // number of each rank, max combo, number of full combo, number of all perfect
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "recent" to (extra["recent_rating_list"] ?: ""),
        )

        genericUserSummary(card, userDataRepository, userPlaylogRepository, shownRanks, ratingComposition)
    }

    override suspend fun ranking() = genericRanking(userDataRepository, userPlaylogRepository)

    override suspend fun playlog(@RP id: Long) = userPlaylogRepository.findById(id).getOrNull() ?: (404 - "Playlog not found")

    override suspend fun recent(@RP username: Str) = us.cardByName(username) { card ->
        userPlaylogRepository.findByUser_Card_ExtId(card.extId)
    }
}