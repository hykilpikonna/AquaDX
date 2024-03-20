package icu.samnyan.aqua.net.games.chu3

import ext.API
import ext.RP
import ext.Str
import ext.minus
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.games.*
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.chusan.model.*
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/chu3")
class Chusan(
    override val us: AquaUserServices,
    override val playlogRepo: Chu3UserPlaylogRepo,
    override val userDataRepo: Chu3UserDataRepo,
    val userGeneralDataRepository: Chu3UserGeneralDataRepo,
): GameApiController<UserData>("chu3", UserData::class) {
    override suspend fun trend(@RP username: Str): List<TrendOut> = us.cardByName(username) { card ->
        findTrend(playlogRepo.findByUserCardExtId(card.extId)
            .map { TrendLog(it.playDate.toString(), it.playerRating) })
    }


    // Only show > AAA rank
    override val shownRanks = chu3Scores.filter { it.first >= 95 * 10000 }
    override val settableFields: Map<String, (UserData, String) -> Unit> by lazy { mapOf(
        "userName" to { u, v -> u.setUserName(v)
            if (!v.all { it in USERNAME_CHARS }) { 400 - "Invalid character in username" }
        },
    ) }

    override suspend fun userSummary(@RP username: Str) = us.cardByName(username) { card ->
        // Summary values: total plays, player rating, server-wide ranking
        // number of each rank, max combo, number of full combo, number of all perfect
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "recent" to (extra["recent_rating_list"] ?: ""),
        )

        genericUserSummary(card, ratingComposition)
    }
}