package icu.samnyan.aqua.net.games.wacca

import ext.API
import ext.minus
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.games.GameApiController
import icu.samnyan.aqua.net.games.GenericGameSummary
import icu.samnyan.aqua.net.games.TrendOut
import icu.samnyan.aqua.net.games.USERNAME_CHARS
import icu.samnyan.aqua.net.utils.AquaNetProps
import icu.samnyan.aqua.sega.wacca.model.db.WaccaRepos
import icu.samnyan.aqua.sega.wacca.model.db.WaccaUser
import icu.samnyan.aqua.sega.wacca.model.db.WcUserPlayLogRepo
import icu.samnyan.aqua.sega.wacca.model.db.WcUserRepo
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/wacca")
class Wacca(
    override val us: AquaUserServices,
    override val playlogRepo: WcUserPlayLogRepo,
    override val userDataRepo: WcUserRepo,
    val repos: WaccaRepos,
    val netProps: AquaNetProps,
): GameApiController<WaccaUser>("wacca", WaccaUser::class) {
    override val settableFields: Map<String, (WaccaUser, String) -> Unit> by lazy { mapOf(
        "userName" to { u, v -> u.userName = v
            if (!v.all { it in USERNAME_CHARS }) { 400 - "Invalid character in username" }
        },
    ) }

    override suspend fun trend(username: String): List<TrendOut> {
        TODO("Not yet implemented")
    }

    override suspend fun userSummary(username: String): GenericGameSummary {
        TODO("Not yet implemented")
    }

    override val shownRanks: List<Pair<Int, String>> = emptyList()
}