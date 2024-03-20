package icu.samnyan.aqua.net.games.mai2

import ext.API
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.Maimai2DataExport
import icu.samnyan.aqua.net.games.ImportClass
import icu.samnyan.aqua.net.games.ImportController
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/mai2")
class Mai2Import : ImportController<Maimai2DataExport>(
    exportFields = Maimai2DataExport::class.java.declaredFields.associateBy {
        it.name.replace("List", "").lowercase()
    },
    renameTable = mapOf(
        "mai2_item_character" to ImportClass(UserCharacter::class),
        "mai2_item_charge" to ImportClass(UserCharge::class),
        "mai2_item_friend_season_ranking" to ImportClass(UserFriendSeasonRanking::class),
        "mai2_item_item" to ImportClass(UserItem::class, mapOf("isValid" to "valid")),
        "mai2_item_login_bonus" to ImportClass(UserLoginBonus::class),
        "mai2_item_map" to ImportClass(UserMap::class),
        "mai2_playlog" to ImportClass(UserPlaylog::class, mapOf("userId" to null)),
        "mai2_profile_activity" to ImportClass(UserAct::class, mapOf("activityId" to "id")),
        "mai2_profile_detail" to ImportClass(UserDetail::class,
            mapOf("user" to null, "version" to null, "isNetMember" to null),
            name = "userdata"),
        "mai2_profile_extend" to ImportClass(UserExtend::class, mapOf("version" to null)),
        "mai2_profile_option" to ImportClass(UserOption::class, mapOf("version" to null)),
        "mai2_score_best" to ImportClass(UserMusicDetail::class),
        "mai2_score_course" to ImportClass(UserCourse::class),
        // "mai2_profile_ghost" to ImportClass(UserGhost::class),
        // "mai2_profile_rating" to ImportClass(UserRating::class),
        // "mai2_profile_region" to ImportClass(UserRegion::class),
    )
) {
    override fun createEmpty() = Maimai2DataExport("SDEZ", UserDetail(), UserExtend(), UserOption(),
        ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),
        ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),
        ArrayList(), UserUdemae())
}