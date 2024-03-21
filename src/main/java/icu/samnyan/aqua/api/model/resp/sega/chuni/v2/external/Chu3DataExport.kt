package icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external

import icu.samnyan.aqua.sega.chusan.model.userdata.*

data class Chu3DataExport(
    var gameId: String = "SDHD",
    var userData: UserData,
    var userGameOption: UserGameOption,
    var userActivityList: List<UserActivity>,
    var userCharacterList: List<UserCharacter>,
    var userChargeList: List<UserCharge>,
    var userCourseList: List<UserCourse>,
    var userDuelList: List<UserDuel>,
    var userItemList: List<UserItem>,
    var userMapList: List<UserMapArea>,
    var userMusicDetailList: List<UserMusicDetail>,
    var userPlaylogList: List<UserPlaylog>,
    var userLoginBonusList: List<UserLoginBonus>,
) {
    constructor() : this("SDHD", UserData(), UserGameOption(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
}

