package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.TrendLog
import icu.samnyan.aqua.net.utils.chu3Scores
import icu.samnyan.aqua.net.utils.findTrend
import icu.samnyan.aqua.net.utils.genericUserSummary
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController
import java.time.format.DateTimeFormatter

@RestController
@API("api/v2/game/chu3")
class Chusan(
    val us: AquaUserServices,
    val userPlaylogRepository: UserPlaylogRepository,
    val userDataRepository: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController
{
    override fun trend(@RP username: Str): List<TrendOut> = us.byName(username) { u ->
        val fmt = DateTimeFormatter.ofPattern("YYYY-MM-DD")
        findTrend(userPlaylogRepository.findByUser_Card_ExtId(u.ghostCard.extId)
            .map { TrendLog(fmt.format(it.playDate), it.playerRating) })
    }

    // Only show > AAA rank
    private val shownRanks = chu3Scores.filter { it.first >= 95 * 10000 }

    override fun userSummary(@RP username: Str) = us.byName(username) { u ->
        // Summary values: total plays, player rating, server-wide ranking
        // number of each rank, max combo, number of full combo, number of all perfect
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(u.ghostCard.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "recent" to (extra["recent_rating_list"] ?: ""),
        )

        genericUserSummary(u, userDataRepository, userPlaylogRepository, shownRanks, ratingComposition)
    }
}