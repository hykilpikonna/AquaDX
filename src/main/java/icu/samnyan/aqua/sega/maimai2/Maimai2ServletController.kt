package icu.samnyan.aqua.sega.maimai2

import ext.API
import ext.popAll
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.handler.*
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
    val getGameSetting: GetGameSettingHandler,
    val getGameEvent: GetGameEventHandler,
    val getGameRanking: GetGameRankingHandler,
    val getGameTournamentInfo: GetGameTournamentInfoHandler,
    val getGameCharge: GetGameChargeHandler,
    val getTransferFriend: GetTransferFriendHandler,
    val getUserActivity: GetUserActivityHandler,
    val userLogin: UserLoginHandler,
    val getUserData: GetUserDataHandler,
    val upsertUserAll: UpsertUserAllHandler,
    val getUserPreview: GetUserPreviewHandler,
    val getUserCharacter: GetUserCharacterHandler,
    val getUserOption: GetUserOptionHandler,
    val getUserItem: GetUserItemHandler,
    val getUserExtend: GetUserExtendHandler,
    val getUserGhost: GetUserGhostHandler,
    val getUserLoginBonus: GetUserLoginBonusHandler,
    val getUserMap: GetUserMapHandler,
    val getUserFavorite: GetUserFavoriteHandler,
    val getUserCard: GetUserCardHandler,
    val getUserMusic: GetUserMusicHandler,
    val getUserRating: GetUserRatingHandler,
    val getUserRegion: GetUserRegionHandler,
    val getUserCharge: GetUserChargeHandler,
    val getUserCourse: GetUserCourseHandler,
    val uploadUserPhoto: UploadUserPhotoHandler,
    val uploadUserPlaylog: UploadUserPlaylogHandler,
    val getGameNgMusicId: GetGameNgMusicIdHandler,
    val getUserFriendSeasonRanking: GetUserFriendSeasonRankingHandler,
    val getUserPortrait: GetUserPortraitHandler,
    val uploadUserPortrait: UploadUserPortraitHandler,
    val cmGetUserPreview: CMGetUserPreviewHandler,
    val cmGetSellingCard: CMGetSellingCardHandler,
    val getUserCardPrintError: GetUserCardPrintErrorHandler,
    val cmGetUserCharacter: CMGetUserCharacterHandler,
    val upsertUserPrint: UpsertUserPrintHandler,
    val getUserRecommendRateMusic: GetUserRecommendRateMusicHandler,
    val getUserRecommendSelectMusic: GetUserRecommendSelectMusicHandler,
    val getUserFavoriteItem: GetUserFavoriteItemHandler,
    val getUserRivalData: GetUserRivalDataHandler,
    val getUserRivalMusic: GetUserRivalMusicHandler,
) {
    val logger = LoggerFactory.getLogger(Maimai2ServletController::class.java)

    val createToken = BaseHandler { """{"Bearer":"AQUATOKEN"}""" }
    val cmUpsertUserPrintLog = BaseHandler { """{"returnCode":1,"orderId":"0","serialId":"FAKECARDIMAG12345678"}""" }

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

    val members = this::class.declaredMemberProperties
    val handlers: Map<String, BaseHandler> = endpointList.associateWith { api ->
        val name = api.replace("Api", "").lowercase()
        (members.find { it.name.lowercase() == name } ?: members.find { it.name.lowercase() == name.replace("cm", "") })
            ?.let { it.call(this) as BaseHandler }
            ?: throw IllegalArgumentException("Mai2: No handler found for $api")
    }

    @API("/{api}")
    fun handle(@PathVariable api: String, @RequestBody request: Map<String, Any>): Any {
        logger.info("Mai2 $api : $request")

        if (api in noopEndpoint) {
            return """{"returnCode":1,"apiName":"com.sega.maimai2servlet.api.$api"}"""
        }

        return handlers[api]?.handle(request) ?: {
            logger.warn("Mai2 $api not found")
            """{"returnCode":1,"apiName":"com.sega.maimai2servlet.api.$api"}"""
        }
    }
}
