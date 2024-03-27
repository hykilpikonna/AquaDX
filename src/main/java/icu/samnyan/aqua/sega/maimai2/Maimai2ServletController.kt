package icu.samnyan.aqua.sega.maimai2

import ext.*
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.handler.*
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import kotlin.reflect.full.declaredMemberProperties

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Suppress("unused")
@RestController
@RequestMapping(path = ["/g/mai2/Maimai2Servlet/", "/g/mai2/"])
class Maimai2ServletController(
    val userLogin: UserLoginHandler,
    val upsertUserAll: UpsertUserAllHandler,
    val getUserItem: GetUserItemHandler,
    val getUserRating: GetUserRatingHandler,
    val uploadUserPhoto: UploadUserPhotoHandler,
    val uploadUserPlaylog: UploadUserPlaylogHandler,
    val getUserPortrait: GetUserPortraitHandler,
    val uploadUserPortrait: UploadUserPortraitHandler,
    val upsertUserPrint: UpsertUserPrintHandler,
    val getUserFavoriteItem: GetUserFavoriteItemHandler,
    val getUserRivalMusic: GetUserRivalMusicHandler,
    val repos: Mai2Repos
) {
    companion object {
        private val logger = LoggerFactory.getLogger(Maimai2ServletController::class.java)
        private val empty = listOf<Any>()
    }

    val getUserExtend = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "userExtend" to (repos.userExtend.findSingleByUser_Card_ExtId(userId)() ?: (404 - "User not found"))
    ) }

    val getUserData = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "userData" to (repos.userData.findByCardExtId(userId)() ?: (404 - "User not found")),
        "banState" to 0
    ) }

    val getUserLoginBonus = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "nextIndex" to 0,
        "userLoginBonusList" to repos.userLoginBonus.findByUser_Card_ExtId(userId)
    ) }

    val getUserMap = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "nextIndex" to 0,
        "userMapList" to repos.userMap.findByUser_Card_ExtId(userId)
    ) }

    val getUserMusic = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "nextIndex" to 0,
        "userMusicList" to listOf(mapOf("userMusicDetailList" to repos.userMusicDetail.findByUser_Card_ExtId(userId)))
    ) }

    val getUserCard = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "nextIndex" to 0,
        "userCardList" to repos.userCard.findByUser_Card_ExtId(userId)
    ) }

    val getUserCharacter = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "userCharacterList" to repos.userCharacter.findByUser_Card_ExtId(userId)
    ) }

    val getUserCharge = UserReqHandler { _, userId -> repos.userCharge.findByUser_Card_ExtId(userId).let { mapOf(
        "userId" to userId,
        "length" to it.size,
        "userChargeList" to it
    ) } }

    val getUserFriendSeasonRanking = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "nextIndex" to 0,
        "userFriendSeasonRankingList" to repos.userFriendSeasonRanking.findByUser_Card_ExtId(userId)
    ) }

    val getUserCourse = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "nextIndex" to 0,
        "userCourseList" to repos.userCourse.findByUser_Card_ExtId(userId)
    ) }

    val getUserFavorite = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "userFavoriteData" to repos.userFavorite.findByUser_Card_ExtId(userId)
    ) }

    val getUserActivity = UserReqHandler { _, userId ->
        repos.userAct.findByUser_Card_ExtId(userId).let { act -> mapOf(
            "userActivity" to mapOf(
                "playList" to act.filter { it.kind == 1 },
                "musicList" to act.filter { it.kind == 2 }
            )
        ) }
    }

    val getGameCharge = BaseHandler { repos.gameCharge.findAll().let {
        mapOf("length" to it.size, "gameChargeList" to it)
    } }

    val getGameEvent = BaseHandler {
        val type = parsing { (it["type"] as Number).toInt() }
        mapOf(
            "type" to type,
            "gameEventList" to repos.gameEvent.findByEnable(true) // Maimai only request for type 1
        )
    }

    val getUserRivalData = UserReqHandler { req, userId ->
        val rivalId = parsing { (req["rivalId"] as Number).toLong() }

        mapOf(
            "userId" to userId,
            "userRivalData" to mapOf(
                "rivalId" to rivalId,
                "rivalName" to (repos.userData.findByCardExtId(rivalId)()?.userName ?: "")
            )
        )
    }

    val getUserOption = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "userOption" to (repos.userOption.findSingleByUser_Card_ExtId(userId)() ?: (404 - "User not found"))
    ) }

    val cmGetSellingCard = BaseHandler { repos.gameSellingCard.findAll().let {
        mapOf("length" to it.size, "sellingCardList" to it)
    } }

    val cmGetUserCharacter = UserReqHandler { _, userId -> repos.userCharacter.findByUser_Card_ExtId(userId).let {
        mapOf(
            "returnCode" to 1,
            "length" to it.size,
            "userCharacterList" to it
        )
    } }

    val cmGetUserPreview = UserReqHandler { _, userId -> repos.userData.findByCardExtId(userId)()?.let {
        mapOf(
            "userId" to userId,
            "userName" to it.userName,
            "rating" to it.playerRating,
            "lastDataVersion" to "1.20.00",
            "isLogin" to false,
            "isExistSellingCard" to false
        )
    } ?: (404 - "User not found") }

    val getUserPreview = UserReqHandler { _, userId ->
        val d = repos.userData.findByCardExtId(userId)() ?: (404 - "User not found")
        val option = repos.userOption.findSingleByUser_Card_ExtId(userId)()

        mapOf(
            "userId" to userId,
            "userName" to d.userName,
            "isLogin" to false,
            "lastGameId" to d.lastGameId,
            "lastDataVersion" to d.lastDataVersion,
            "lastRomVersion" to d.lastRomVersion,
            "lastLoginDate" to d.lastPlayDate,
            "lastPlayDate" to d.lastPlayDate,
            "playerRating" to d.playerRating,
            "nameplateId" to d.plateId,
            "iconId" to d.iconId,
            "trophyId" to 0,
            "partnerId" to d.partnerId,
            "frameId" to d.frameId,
            "totalAwake" to d.totalAwake,
            "isNetMember" to d.isNetMember,
            "dailyBonusDate" to d.dailyBonusDate,
            "headPhoneVolume" to (option?.headPhoneVolume ?: 0),
            "dispRate" to (option?.dispRate ?: 0),
            "isInherit" to false,
            "banState" to d.banState
        )
    }

    // Empty List Handlers
    val getUserRecommendRateMusic = UserReqHandler { _, userId -> mapOf(
        "userId" to userId,
        "userRecommendRateMusicIdList" to empty
    ) }
    val getUserRecommendSelectMusic = UserReqHandler { _, uid -> mapOf(
        "userId" to uid,
        "userRecommendSelectionMusicIdList" to empty
    ) }
    val getUserCardPrintError = BaseHandler { mapOf("length" to 0, "userPrintDetailList" to empty) }
    val getUserRegion = UserReqHandler { _, uid -> mapOf("userId" to uid, "length" to 0, "userRegionList" to empty) }
    val getUserGhost = UserReqHandler { _, uid -> mapOf("userId" to uid, "userGhostList" to empty) }
    val getTransferFriend = UserReqHandler { _, uid -> mapOf("userId" to uid, "transferFriendList" to empty) }
    val getGameNgMusicId = BaseHandler { mapOf("length" to 0, "musicIdList" to empty) }
    val getGameRanking = BaseHandler { mapOf("type" to it["type"].toString(), "gameRankingList" to empty) }
    val getGameTournamentInfo = BaseHandler { mapOf("length" to 0, "gameTournamentInfoList" to empty) }

    val endpointList = setOf("GetGameEventApi", "GetGameRankingApi", "GetGameSettingApi", "GetGameTournamentInfoApi",
        "GetTransferFriendApi", "GetUserActivityApi", "GetUserCardApi", "GetUserCharacterApi", "GetUserDataApi",
        "GetUserExtendApi", "GetUserFavoriteApi", "GetUserGhostApi", "GetUserItemApi", "GetUserLoginBonusApi",
        "GetUserMapApi", "GetUserMusicApi", "GetUserOptionApi", "GetUserPortraitApi", "GetUserPreviewApi",
        "GetUserRatingApi", "GetUserRegionApi", "UploadUserPhotoApi", "UploadUserPlaylogApi", "UploadUserPortraitApi",
        "UserLoginApi", "UserLogoutApi", "UpsertUserAllApi", "GetGameChargeApi", "GetUserChargeApi",
        "GetUserCourseApi", "GetGameNgMusicIdApi", "GetUserFriendSeasonRankingApi", "CreateTokenApi",
        "GetUserRecommendRateMusicApi", "GetUserRecommendSelectMusicApi", "CMGetSellingCardApi",
        "CMGetUserCardApi", "CMGetUserCardPrintErrorApi", "CMGetUserCharacterApi", "CMGetUserDataApi", "CMGetUserItemApi",
        "CMGetUserPreviewApi", "CMUpsertUserPrintApi",
        "CMUpsertUserPrintlogApi", "GetUserFavoriteItemApi", "GetUserRivalDataApi", "GetUserRivalMusicApi",
        "GetUserScoreRankingApi", "UpsertClientBookkeepingApi", "UpsertClientSettingApi",
        "UpsertClientTestmodeApi", "UpsertClientUploadApi", "Ping", "RemoveTokenApi", "CMLoginApi", "CMLogoutApi",
        "CMUpsertBuyCardApi").toMutableList()

    val noopEndpoint = endpointList.popAll("GetUserScoreRankingApi", "UpsertClientBookkeepingApi",
        "UpsertClientSettingApi", "UpsertClientTestmodeApi", "UpsertClientUploadApi", "Ping", "RemoveTokenApi",
        "CMLoginApi", "CMLogoutApi", "CMUpsertBuyCardApi", "UserLogoutApi", "GetGameMapAreaConditionApi")

    val staticEndpoint = mapOf(
        "CreateTokenApi" to """{"Bearer":"meow"}""",
        "CMUpsertUserPrintlogApi" to """{"returnCode":1,"orderId":"0","serialId":"FAKECARDIMAG12345678"}""",
        "GetGameSettingApi" to mapOf(
            "isAouAccession" to true,
            "gameSetting" to mapOf(
                "isMaintenance" to false,
                "requestInterval" to 10,
                "rebootStartTime" to "2099-01-01 23:59:00.0",
                "rebootEndTime" to "2099-01-01 23:59:00.0",
                "movieUploadLimit" to 10000,
                "movieStatus" to 0,
                "movieServerUri" to "",
                "deliverServerUri" to "",
                "oldServerUri" to "",
                "usbDlServerUri" to "",
                "rebootInterval" to 0
            )
        ).toJson()
    ).also { endpointList.popAll(it.keys.toList()) }

    val members = this::class.declaredMemberProperties
    val handlers: Map<String, BaseHandler> = endpointList.associateWith { api ->
        val name = api.replace("Api", "").lowercase()
        (members.find { it.name.lowercase() == name } ?: members.find { it.name.lowercase() == name.replace("cm", "") })
            ?.let { it.call(this) as BaseHandler }
            ?: throw IllegalArgumentException("Mai2: No handler found for $api")
    }

    @API("/{api}")
    fun handle(@PathVariable api: String, @RequestBody request: Map<String, Any>): Any {
        logger.info("Mai2 < $api : ${request.toJson()}") // TODO: Optimize logging

        if (api in noopEndpoint) {
            logger.info("Mai2 > $api no-op")
            return """{"returnCode":1,"apiName":"com.sega.maimai2servlet.api.$api"}"""
        }

        if (api in staticEndpoint) {
            logger.info("Mai2 > $api static")
            return staticEndpoint[api]!!
        }

        return handlers[api]?.handle(request)?.let { if (it is String) it else it.toJson() }?.also {
            if (api !in setOf("GetUserItemApi", "GetGameEventApi"))
                logger.info("Mai2 > $api : $it")
        } ?: {
            logger.warn("Mai2 > $api not found")
            """{"returnCode":1,"apiName":"com.sega.maimai2servlet.api.$api"}"""
        }
    }
}
