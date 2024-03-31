package icu.samnyan.aqua.api.model.resp.sega.maimai2.external

import icu.samnyan.aqua.net.games.IExportClass
import icu.samnyan.aqua.sega.maimai2.model.userdata.*

data class Maimai2DataExport(
    override var userData: Mai2UserDetail,
    var userExtend: Mai2UserExtend,
    var userOption: Mai2UserOption,
    var userUdemae: Mai2UserUdemae,
    var mapEncountNpcList: List<Mai2MapEncountNpc>,
    var userActList: List<Mai2UserAct>,
    var userCharacterList: List<Mai2UserCharacter>,
    var userChargeList: List<Mai2UserCharge>,
    var userCourseList: List<Mai2UserCourse>,
    var userFavoriteList: List<Mai2UserFavorite>,
    var userFriendSeasonRankingList: List<Mai2UserFriendSeasonRanking>,
    var userGeneralDataList: List<Mai2UserGeneralData>,
    var userItemList: List<Mai2UserItem>,
    var userLoginBonusList: List<Mai2UserLoginBonus>,
    var userMapList: List<Mai2UserMap>,
    var userMusicDetailList: List<Mai2UserMusicDetail>,
    var userPlaylogList: List<Mai2UserPlaylog>,
    override var gameId: String = "SDEZ",
): IExportClass<Mai2UserDetail> {
    constructor() : this(Mai2UserDetail(), Mai2UserExtend(), Mai2UserOption(), Mai2UserUdemae(),
        mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(),
        mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(),
        mutableListOf())
}
