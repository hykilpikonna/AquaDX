package icu.samnyan.aqua.net.games

import ext.API
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserPlaylogRepository
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/ongeki")
class Ongeki(
    val us: AquaUserServices,
    val userPlaylogRepository: UserPlaylogRepository,
    val userDataRepository: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController {
    override fun trend(username: String) = us.byName(username) { u ->
        findTrend(userPlaylogRepository.findByUser_Card_ExtId(u.ghostCard.extId)
            .map { TrendLog(it.playDate, it.playerRating) })
    }

    private val shownRanks = ongekiScores.filter { it.first >= 950000 }

    override fun userSummary(username: String) = us.byName(username) { u ->
//        val extra = userGeneralDataRepository.findByUser_Card_ExtId(u.ghostCard.extId)
//            .associate { it.propertyKey to it.propertyValue }

        // TODO: Rating composition

        genericUserSummary(u, userDataRepository, userPlaylogRepository, shownRanks, mapOf())
    }

    override fun ranking() = genericRanking(userDataRepository, userPlaylogRepository)
}