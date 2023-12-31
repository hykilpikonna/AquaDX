package icu.samnyan.aqua.sega.chusan.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chusan.handler.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping({"/ChusanServlet/{version}/ChuniServlet", "/ChusanServlet/{version}"})
public class ChusanServletController {

    private final GameLoginHandler gameLoginHandler;
    private final GameLogoutHandler gameLogoutHandler;
    private final GetGameChargeHandler getGameChargeHandler;
    private final GetGameEventHandler getGameEventHandler;
    private final GetGameIdlistHandler getGameIdlistHandler;
    private final GetGameRankingHandler getGameRankingHandler;
    private final GetGameSettingHandler getGameSettingHandler;
    private final GetTeamCourseRuleHandler getTeamCourseRuleHandler;
    private final GetTeamCourseSettingHandler getTeamCourseSettingHandler;
    private final GetUserActivityHandler getUserActivityHandler;
    private final GetUserCharacterHandler getUserCharacterHandler;
    private final GetUserChargeHandler getUserChargeHandler;
    private final GetUserCourseHandler getUserCourseHandler;
    private final GetUserDataHandler getUserDataHandler;
    private final GetUserDuelHandler getUserDuelHandler;
    private final GetUserFavoriteItemHandler getUserFavoriteItemHandler;
    private final GetUserItemHandler getUserItemHandler;
    private final GetUserLoginBonusHandler getUserLoginBonusHandler;
    private final GetUserMapAreaHandler getUserMapAreaHandler;
    private final GetUserMusicHandler getUserMusicHandler;
    private final GetUserOptionHandler getUserOptionHandler;
    private final GetUserPreviewHandler getUserPreviewHandler;
    private final GetUserRecentRatingHandler getUserRecentRatingHandler;
    private final GetUserRegionHandler getUserRegionHandler;
    private final GetUserRivalDataHandler getUserRivalDataHandler;
    private final GetUserRivalMusicHandler getUserRivalMusicHandler;
    private final GetUserSymbolChatSettingHandler getUserSymbolChatSettingHandler;
    private final GetUserNetBattleDataHandler getUserNetBattleDataHandler;
    private final GetUserTeamHandler getUserTeamHandler;
    private final UpsertUserAllHandler upsertUserAllHandler;
    private final UpsertUserChargelogHandler upsertUserChargelogHandler;
    private final GetGameGachaHandler getGameGachaHandler;
    private final GetGameGachaCardByIdHandler getGameGachaCardByIdHandler;
    private final GetUserCardPrintErrorHandler getUserCardPrintErrorHandler;
    private final CMGetUserPreviewHandler cmGetUserPreviewHandler;
    private final CMGetUserDataHandler cmGetUserDataHandler;
    private final CMGetUserCharacterHandler cmGetUserCharacterHandler;
    private final GetUserGachaHandler getUserGachaHandler;
    private final GetUserPrintedCardHandler getUserPrintedCardHandler;
    private final CMGetUserItemHandler cmGetUserItemHandler;
    private final RollGachaHandler rollGachaHandler;
    private final CMUpsertUserGachaHandler cmUpsertUserGachaHandler;
    private final CMUpsertUserPrintSubtractHandler cmUpsertUserPrintSubtractHandler;
    private final CMUpsertUserPrintCancelHandler cmUpsertUserPrintCancelHandler;
    private final BeginMatchingHandler beginMatchingHandler;
    private final EndMatchingHandler endMatchingHandler;
    private final GetMatchingStateHandler getMatchingStateHandler;
    private final RemoveMatchingMemberHandler removeMatchingMemberHandler;

    @Autowired
    public ChusanServletController(GameLoginHandler gameLoginHandler, GameLogoutHandler gameLogoutHandler,
    GetGameChargeHandler getGameChargeHandler, GetGameEventHandler getGameEventHandler,
    GetGameIdlistHandler getGameIdlistHandler, GetGameRankingHandler getGameRankingHandler,
    GetGameSettingHandler getGameSettingHandler, GetTeamCourseRuleHandler getTeamCourseRuleHandler,
    GetTeamCourseSettingHandler getTeamCourseSettingHandler, GetUserActivityHandler getUserActivityHandler,
    GetUserCharacterHandler getUserCharacterHandler, GetUserChargeHandler getUserChargeHandler,
    GetUserCourseHandler getUserCourseHandler, GetUserDataHandler getUserDataHandler,
    GetUserDuelHandler getUserDuelHandler, GetUserFavoriteItemHandler getUserFavoriteItemHandler,
    GetUserItemHandler getUserItemHandler, GetUserLoginBonusHandler getUserLoginBonusHandler,
    GetUserMapAreaHandler getUserMapAreaHandler, GetUserMusicHandler getUserMusicHandler,
    GetUserOptionHandler getUserOptionHandler, GetUserPreviewHandler getUserPreviewHandler,
    GetUserRecentRatingHandler getUserRecentRatingHandler, GetUserRegionHandler getUserRegionHandler,
    GetUserRivalDataHandler getUserRivalDataHandler, GetUserRivalMusicHandler getUserRivalMusicHandler,
    GetUserTeamHandler getUserTeamHandler, UpsertUserAllHandler upsertUserAllHandler,
    UpsertUserChargelogHandler upsertUserChargelogHandler, GetUserSymbolChatSettingHandler getUserSymbolChatSettingHandler,
    GetUserNetBattleDataHandler getUserNetBattleDataHandler, GetGameGachaHandler getGameGachaHandler,
    GetGameGachaCardByIdHandler getGameGachaCardByIdHandler, GetUserCardPrintErrorHandler getUserCardPrintErrorHandler,
    CMGetUserPreviewHandler cmGetUserPreviewHandler, CMGetUserDataHandler cmGetUserDataHandler,
    CMGetUserCharacterHandler cmGetUserCharacterHandler, GetUserGachaHandler getUserGachaHandler,
    GetUserPrintedCardHandler getUserPrintedCardHandler, CMGetUserItemHandler cmGetUserItemHandler,
    RollGachaHandler rollGachaHandler, CMUpsertUserGachaHandler cmUpsertUserGachaHandler,
    CMUpsertUserPrintSubtractHandler cmUpsertUserPrintSubtractHandler, CMUpsertUserPrintCancelHandler cmUpsertUserPrintCancelHandler,
    BeginMatchingHandler beginMatchingHandler, EndMatchingHandler endMatchingHandler,
    GetMatchingStateHandler getMatchingStateHandler, RemoveMatchingMemberHandler removeMatchingMemberHandler) {
        this.gameLoginHandler = gameLoginHandler;
        this.gameLogoutHandler = gameLogoutHandler;
        this.getGameChargeHandler = getGameChargeHandler;
        this.getGameEventHandler = getGameEventHandler;
        this.getGameIdlistHandler = getGameIdlistHandler;
        this.getGameRankingHandler = getGameRankingHandler;
        this.getGameSettingHandler = getGameSettingHandler;
        this.getTeamCourseRuleHandler = getTeamCourseRuleHandler;
        this.getTeamCourseSettingHandler = getTeamCourseSettingHandler;
        this.getUserActivityHandler = getUserActivityHandler;
        this.getUserCharacterHandler = getUserCharacterHandler;
        this.getUserChargeHandler = getUserChargeHandler;
        this.getUserCourseHandler = getUserCourseHandler;
        this.getUserDataHandler = getUserDataHandler;
        this.getUserDuelHandler = getUserDuelHandler;
        this.getUserFavoriteItemHandler = getUserFavoriteItemHandler;
        this.getUserItemHandler = getUserItemHandler;
        this.getUserLoginBonusHandler = getUserLoginBonusHandler;
        this.getUserMapAreaHandler = getUserMapAreaHandler;
        this.getUserMusicHandler = getUserMusicHandler;
        this.getUserOptionHandler = getUserOptionHandler;
        this.getUserPreviewHandler = getUserPreviewHandler;
        this.getUserRecentRatingHandler = getUserRecentRatingHandler;
        this.getUserRegionHandler = getUserRegionHandler;
        this.getUserRivalDataHandler = getUserRivalDataHandler;
        this.getUserRivalMusicHandler = getUserRivalMusicHandler;
        this.getUserTeamHandler = getUserTeamHandler;
        this.upsertUserAllHandler = upsertUserAllHandler;
        this.upsertUserChargelogHandler = upsertUserChargelogHandler;
        this.getUserSymbolChatSettingHandler = getUserSymbolChatSettingHandler;
        this.getUserNetBattleDataHandler = getUserNetBattleDataHandler;
        this.getGameGachaHandler = getGameGachaHandler;
        this.getGameGachaCardByIdHandler = getGameGachaCardByIdHandler;
        this.getUserCardPrintErrorHandler = getUserCardPrintErrorHandler;
        this.cmGetUserPreviewHandler = cmGetUserPreviewHandler;
        this.cmGetUserDataHandler = cmGetUserDataHandler;
        this.cmGetUserCharacterHandler = cmGetUserCharacterHandler;
        this.getUserGachaHandler = getUserGachaHandler;
        this.getUserPrintedCardHandler = getUserPrintedCardHandler;
        this.cmGetUserItemHandler = cmGetUserItemHandler;
        this.rollGachaHandler = rollGachaHandler;
        this.cmUpsertUserGachaHandler = cmUpsertUserGachaHandler;
        this.cmUpsertUserPrintSubtractHandler = cmUpsertUserPrintSubtractHandler;
        this.cmUpsertUserPrintCancelHandler = cmUpsertUserPrintCancelHandler;
        this.beginMatchingHandler = beginMatchingHandler;
        this.endMatchingHandler = endMatchingHandler;
        this.getMatchingStateHandler = getMatchingStateHandler;
        this.removeMatchingMemberHandler = removeMatchingMemberHandler;
    }

    @PostMapping("GameLoginApi")
    String gameLogin(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return gameLoginHandler.handle(request);
    }

    @PostMapping("GameLogoutApi")
    String gameLogout(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return gameLogoutHandler.handle(request);
    }

    @PostMapping("GetGameChargeApi")
    String getGameCharge(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameChargeHandler.handle(request);
    }

    @PostMapping("GetGameEventApi")
    String getGameEvent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameEventHandler.handle(request);
    }

    @PostMapping("GetGameIdlistApi")
    String getGameIdList(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameIdlistHandler.handle(request);
    }

    @PostMapping("GetGameRankingApi")
    String getGameRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameRankingHandler.handle(request);
    }

    @PostMapping("GetGameSettingApi")
    String getGameSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameSettingHandler.handle(request);
    }

    @PostMapping("GetTeamCourseRuleApi")
    String getTeamCourseRule(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getTeamCourseRuleHandler.handle(request);
    }

    @PostMapping("GetTeamCourseSettingApi")
    String getTeamCourseSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getTeamCourseSettingHandler.handle(request);
    }

    @PostMapping("GetUserActivityApi")
    String getUserActivity(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserActivityHandler.handle(request);
    }

    @PostMapping("GetUserCharacterApi")
    String getUserCharacter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCharacterHandler.handle(request);
    }

    @PostMapping("GetUserChargeApi")
    String getUserCharge(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserChargeHandler.handle(request);
    }

    @PostMapping("GetUserCourseApi")
    String getUserCourse(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCourseHandler.handle(request);
    }

    @PostMapping("GetUserDataApi")
    String getUserData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDataHandler.handle(request);
    }

    @PostMapping("GetUserDuelApi")
    String getUserDuel(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDuelHandler.handle(request);
    }

    @PostMapping("GetUserFavoriteItemApi")
    String getUserFavoriteItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserFavoriteItemHandler.handle(request);
    }

    @PostMapping("GetUserItemApi")
    String getUserItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserItemHandler.handle(request);
    }

    @PostMapping("GetUserLoginBonusApi")
    String getUserLoginBonus(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserLoginBonusHandler.handle(request);
    }

    @PostMapping("GetUserMapAreaApi")
    String getUserMap(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMapAreaHandler.handle(request);
    }

    @PostMapping("GetUserMusicApi")
    String getUserMusic(@ModelAttribute Map<String, Object> request, @PathVariable String version) throws JsonProcessingException {
        request.put("version", version);
        return getUserMusicHandler.handle(request);
    }

    @PostMapping("GetUserOptionApi")
    String getUserOption(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserOptionHandler.handle(request);
    }

    // Call when login. Return null if no profile exist
    @PostMapping("GetUserPreviewApi")
    String getUserPreview(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPreviewHandler.handle(request);
    }

    @PostMapping("GetUserRecentRatingApi")
    String getUserRecentRating(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRecentRatingHandler.handle(request);
    }

    @PostMapping("GetUserRegionApi")
    String getUserRegion(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRegionHandler.handle(request);
    }

    @PostMapping("GetUserRivalDataApi")
    String getUserRivalData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRivalDataHandler.handle(request);
    }

    @PostMapping("GetUserRivalMusicApi")
    String getUserRivalMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRivalMusicHandler.handle(request);
    }

    @PostMapping("GetUserTeamApi")
    String getUserTeam(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTeamHandler.handle(request);
    }

    @PostMapping("GetUserSymbolChatSettingApi")
    String getUserSymbolChatSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserSymbolChatSettingHandler.handle(request);
    }

    @PostMapping("GetUserNetBattleDataApi")
    String getUserNetBattleData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserNetBattleDataHandler.handle(request);
    }

    @PostMapping("UpsertClientBookkeepingApi")
    String upsertClientBookkeeping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertClientDevelopApi")
    String upsertClientDevelop(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertClientErrorApi")
    String upsertClientError(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertClientSettingApi")
    String upsertClientSetting(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertClientTestmodeApi")
    String upsertClientTestmode(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertUserAllApi")
    String upsertUserAll(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserAllHandler.handle(request);
    }

    @PostMapping("UpsertUserChargelogApi")
    String upsertUserChargelog(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserChargelogHandler.handle(request);
    }

    @PostMapping("CreateTokenApi")
    String createToken(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("RemoveTokenApi")
    String removeToken(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertClientUploadApi")
    String upsertClientUpload(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    // Matching endpoints

    @PostMapping("MatchingServer/Ping")
    String ping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("MatchingServer/BeginMatchingApi")
    String beginMatching(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return beginMatchingHandler.handle(request);
    }

    @PostMapping("MatchingServer/EndMatchingApi")
    String endMatching(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return endMatchingHandler.handle(request);
    }

    @PostMapping("MatchingServer/RemoveMatchingMemberApi")
    String removeMatchingMember(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return removeMatchingMemberHandler.handle(request);
    }

    @PostMapping("MatchingServer/GetMatchingStateApi")
    String getMatchingState(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getMatchingStateHandler.handle(request);
    }

    // Cardmaker endpoints

    @PostMapping("GetGameGachaApi")
    String getGameGacha(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameGachaHandler.handle(request);
    }

    @PostMapping("GetGameGachaCardByIdApi")
    String getGameGachaCardById(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameGachaCardByIdHandler.handle(request);
    }

    @PostMapping("GetUserCardPrintErrorApi")
    String getUserCardPrintError(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCardPrintErrorHandler.handle(request);
    }

    @PostMapping("CMGetUserCharacterApi")
    String cmGetUserCharacter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetUserCharacterHandler.handle(request);
    }

    @PostMapping("CMGetUserDataApi")
    String cmGetUserData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetUserDataHandler.handle(request);
    }

    @PostMapping("GetUserGachaApi")
    String GetUserGacha(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserGachaHandler.handle(request);
    }

    @PostMapping("CMGetUserItemApi")
    String cmGetUserItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetUserItemHandler.handle(request);
    }

    @PostMapping("CMGetUserPreviewApi")
    String cmGetUserPreview(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetUserPreviewHandler.handle(request);
    }

    @PostMapping("GetUserPrintedCardApi")
    String getUserPrintedCard(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPrintedCardHandler.handle(request);
    }

    @PostMapping("PrinterLoginApi")
    String printerLogin(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("PrinterLogoutApi")
    String printerLogout(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("RollGachaApi")
    String rollGacha(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return rollGachaHandler.handle(request);
    }

    @PostMapping("CMUpsertUserGachaApi")
    String cmUpsertUserGacha(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmUpsertUserGachaHandler.handle(request);
    }

    @PostMapping("CMUpsertUserPrintApi")
    String cmUpsertUserPrint(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\", \"orderId\":\"0\", \"serialId\":\"FAKECARDIMAG12345678\", \"apiName\":\"CMUpsertUserPrintApi\"}";
    }

    @PostMapping("CMUpsertUserPrintCancelApi")
    String cmUpsertUserPrintCancel(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmUpsertUserPrintCancelHandler.handle(request);
    }

    @PostMapping("CMUpsertUserPrintlogApi")
    String cmUpsertUserPrintlog(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\", \"orderId\":\"0\", \"serialId\":\"FAKECARDIMAG12345678\", \"apiName\":\"CMUpsertUserPrintlogApi\"}";
    }

    @PostMapping("CMUpsertUserPrintSubtractApi")
    String cmUpsertUserPrintSubtract(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmUpsertUserPrintSubtractHandler.handle(request);
    }

}
