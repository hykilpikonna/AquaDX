package icu.samnyan.aqua.net.games.wacca

import ext.*
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.games.*
import icu.samnyan.aqua.net.utils.waccaScores
import icu.samnyan.aqua.sega.wacca.model.db.WaccaRepos
import icu.samnyan.aqua.sega.wacca.model.db.WaccaUser
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/wacca")
class Wacca(
    override val us: AquaUserServices,
    val rp: WaccaRepos,
): GameApiController<WaccaUser>("wacca", WaccaUser::class) {
    override val settableFields: Map<String, (WaccaUser, String) -> Unit> by lazy { mapOf(
        "userName" to usernameCheck(WACCA_USERNAME_CHARS),
    ) }
    override val playlogRepo = rp.playLog
    override val userDataRepo = rp.user

    override suspend fun trend(@RP username: String) = us.cardByName(username) { card ->
        findTrend(playlogRepo.findByUserCardExtId(card.extId)
            .map { TrendLog(it.userPlayDate.utc().isoDate(), it.afterRating) })
    }

    override suspend fun userSummary(@RP username: String) = us.cardByName(username) { card ->
        genericUserSummary(card) {
            val best50 = rp.bestScore.findTop50(it)

            mapOf()
        }
    }

    override val shownRanks: List<Pair<Int, String>> = waccaScores.filter { it.first > 85 * 10000 }
}