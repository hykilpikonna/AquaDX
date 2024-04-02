package icu.samnyan.aqua.net.games.ongeki

import ext.API
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.games.*
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
        "userName" to usernameCheck(SEGA_USERNAME_CAHRS)
    ) }

    override suspend fun userSummary(username: String) = us.cardByName(username) { card ->
//        val extra = userGeneralDataRepository.findByUser_Card_ExtId(u.ghostCard.extId)
//            .associate { it.propertyKey to it.propertyValue }

        // TODO: Rating composition

        genericUserSummary(card, mapOf())
    }
}