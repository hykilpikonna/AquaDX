package icu.samnyan.aqua.net.games.chu3

import ext.API
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external.Chu3DataExport
import icu.samnyan.aqua.net.games.ImportClass
import icu.samnyan.aqua.net.games.ImportController
import icu.samnyan.aqua.sega.chusan.model.userdata.*
import org.springframework.web.bind.annotation.RestController

@RestController
@API("api/v2/game/chu3")
class Chu3Import : ImportController<Chu3DataExport>(
    exportFields = Chu3DataExport::class.java.declaredFields.associateBy {
        var name = it.name
        if (name == "userMapList") name = "userMapAreaList"
        name.replace("List", "").lowercase()
    },
    renameTable = mapOf(
        "chuni_item_character" to ImportClass(UserCharacter::class),
        "chuni_item_duel" to ImportClass(UserDuel::class),
        "chuni_item_item" to ImportClass(UserItem::class, mapOf("isValid" to "valid")),
        "chuni_item_login_bonus" to ImportClass(UserLoginBonus::class, mapOf("isWatched" to "watched")),
        "chuni_item_map_area" to ImportClass(UserMapArea::class),
        "chuni_profile_activity" to ImportClass(UserActivity::class, mapOf("activityId" to "id")),
        "chuni_profile_charge" to ImportClass(UserCharge::class),
        "chuni_profile_data" to ImportClass(UserData::class, mapOf("user" to null, "version" to null, "isNetMember" to null)),
        "chuni_profile_option" to ImportClass(UserGameOption::class, mapOf("version" to null)),
        "chuni_score_best" to ImportClass(UserMusicDetail::class),
        "chuni_score_playlog" to ImportClass(UserPlaylog::class),
//        "chuni_item_favorite" to ImportClass(UserFavorite::class),
//        "chuni_profile_emoney" to ImportClass(UserEmoney::class),
//        "chuni_profile_overpower" to ImportClass(UserOverpower::class),
//        "chuni_profile_recent_rating" to ImportClass(UserRecentRating::class),
    )
) {
    override fun createEmpty() = Chu3DataExport("SDEZ", UserData(), UserGameOption(), ArrayList(), ArrayList(),
        ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
}