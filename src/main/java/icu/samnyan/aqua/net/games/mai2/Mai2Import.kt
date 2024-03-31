package icu.samnyan.aqua.net.games.mai2

import ext.API
import ext.returns
import ext.vars
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.Maimai2DataExport
import icu.samnyan.aqua.net.games.ImportClass
import icu.samnyan.aqua.net.games.ImportController
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserLinked
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.full.declaredMembers

@RestController
@API("api/v2/game/mai2")
class Mai2Import(
    val repos: Mai2Repos,
) : ImportController<Maimai2DataExport, Mai2UserDetail>(
    "SDEZ", Maimai2DataExport::class,
    exportFields = Maimai2DataExport::class.vars().associateBy {
        it.name.replace("List", "").lowercase()
    },
    exportRepos = Maimai2DataExport::class.vars()
        .filter { f -> f.name !in setOf("gameId", "userData") }
        .associateWith { Mai2Repos::class.declaredMembers
            .filter { f -> f returns Mai2UserLinked::class }
            .firstOrNull { f -> f.name == it.name || f.name == it.name.replace("List", "") }
            ?.call(repos) as Mai2UserLinked<*>? ?: error("No matching field found for ${it.name}")
        },
    artemisRenames = mapOf(
        "mai2_item_character" to ImportClass(Mai2UserCharacter::class),
        "mai2_item_charge" to ImportClass(Mai2UserCharge::class),
        "mai2_item_friend_season_ranking" to ImportClass(Mai2UserFriendSeasonRanking::class),
        "mai2_item_item" to ImportClass(Mai2UserItem::class, mapOf("isValid" to "valid")),
        "mai2_item_login_bonus" to ImportClass(Mai2UserLoginBonus::class),
        "mai2_item_map" to ImportClass(Mai2UserMap::class),
        "mai2_playlog" to ImportClass(Mai2UserPlaylog::class, mapOf("userId" to null)),
        "mai2_profile_activity" to ImportClass(Mai2UserAct::class, mapOf("activityId" to "id")),
        "mai2_profile_detail" to ImportClass(Mai2UserDetail::class,
            mapOf("user" to null, "version" to null, "isNetMember" to null),
            name = "userdata"),
        "mai2_profile_extend" to ImportClass(Mai2UserExtend::class, mapOf("version" to null)),
        "mai2_profile_option" to ImportClass(Mai2UserOption::class, mapOf("version" to null)),
        "mai2_score_best" to ImportClass(Mai2UserMusicDetail::class),
        "mai2_score_course" to ImportClass(Mai2UserCourse::class),
    )
) {
    override fun createEmpty() = Maimai2DataExport()
    override val userDataRepo = repos.userData
}