package icu.samnyan.aqua.net.games.ongeki

import ext.API
import ext.minus
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.games.GameApiController
import icu.samnyan.aqua.net.games.TrendLog
import icu.samnyan.aqua.net.games.USERNAME_CHARS
import icu.samnyan.aqua.net.games.findTrend
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserDataRepository
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserGeneralDataRepository
import icu.samnyan.aqua.sega.ongeki.dao.userdata.UserPlaylogRepository
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/ongeki")
class Ongeki(
    override val us: AquaUserServices,
    override val playlogRepo: UserPlaylogRepository,
    override val userDataRepo: UserDataRepository,
    val userGeneralDataRepository: UserGeneralDataRepository
): GameApiController<UserData>("ongeki", UserData::class) {
    override suspend fun trend(username: String) = us.cardByName(username) { card ->
        findTrend(playlogRepo.findByUser_Card_ExtId(card.extId)
            .map { TrendLog(it.playDate, it.playerRating) })
    }

    override val shownRanks = ongekiScores.filter { it.first >= 950000 }
    override val settableFields: Map<String, (UserData, String) -> Unit> by lazy { mapOf(
        "userName" to { u, v -> u.setUserName(v)
            if (!v.all { it in USERNAME_CHARS }) { 400 - "Invalid character in username" }
        },
    ) }

    override suspend fun userSummary(username: String) = us.cardByName(username) { card ->
//        val extra = userGeneralDataRepository.findByUser_Card_ExtId(u.ghostCard.extId)
//            .associate { it.propertyKey to it.propertyValue }

        // TODO: Rating composition

        genericUserSummary(card, mapOf())
    }
}