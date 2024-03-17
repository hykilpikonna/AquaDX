package icu.samnyan.aqua.sega.maimai2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.handler.impl.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@AllArgsConstructor
@RestController
@RequestMapping({ "/g/mai2/Maimai2Servlet", "/g/mai2" })
public class Maimai2ServletController {

    private final GetGameSettingHandler getGameSettingHandler;
    private final GetGameEventHandler getGameEventHandler;
    private final GetGameRankingHandler getGameRankingHandler;
    private final GetGameTournamentInfoHandler getGameTournamentInfoHandler;
    private final GetGameChargeHandler getGameChargeHandler;
    private final GetTransferFriendHandler getTransferFriendHandler;
    private final GetUserActivityHandler getUserActivityHandler;
    private final UserLoginHandler userLoginHandler;
    private final UserLogoutHandler userLogoutHandler;
    private final GetUserDataHandler getUserDataHandler;
    private final UpsertUserAllHandler upsertUserAllHandler;
    private final GetUserPreviewHandler getUserPreviewHandler;
    private final GetUserCharacterHandler getUserCharacterHandler;
    private final GetUserOptionHandler getUserOptionHandler;
    private final GetUserItemHandler getUserItemHandler;
    private final GetUserExtendHandler getUserExtendHandler;
    private final GetUserGhostHandler getUserGhostHandler;
    private final GetUserLoginBonusHandler getUserLoginBonusHandler;
    private final GetUserMapHandler getUserMapHandler;
    private final GetUserFavoriteHandler getUserFavoriteHandler;
    private final GetUserCardHandler getUserCardHandler;
    private final GetUserMusicHandler getUserMusicHandler;
    private final GetUserRatingHandler getUserRatingHandler;
    private final GetUserRegionHandler getUserRegionHandler;
    private final GetUserChargeHandler getUserChargeHandler;
    private final GetUserCourseHandler getUserCourseHandler;
    private final UploadUserPhotoHandler uploadUserPhotoHandler;
    private final UploadUserPlaylogHandler uploadUserPlaylogHandler;
    private final GetGameNgMusicIdHandler getGameNgMusicIdHandler;
    private final GetUserFriendSeasonRankingHandler getUserFriendSeasonRankingHandler;
    private final GetUserPortraitHandler getUserPortraitHandler;
    private final UploadUserPortraitHandler uploadUserPortraitHandler;
    private final CMGetUserPreviewHandler cmGetUserPreviewHandler;
    private final CMGetSellingCardHandler cmGetSellingCardHandler;
    private final GetUserCardPrintErrorHandler getUserCardPrintErrorHandler;
    private final CMGetUserCharacterHandler cmGetUserCharacterHandler;
    private final UpsertUserPrintHandler upsertUserPrintHandler;
    private final GetUserRecommendRateMusicHandler getUserRecommendRateMusicHandler;
    private final GetUserRecommendSelectMusicHandler getUserRecommendSelectMusicHandler;
    private final GetUserFavoriteItemHandler getUserFavoriteItemHandler;
    private final GetUserRivalDataHandler getUserRivalDataHandler;
    private final GetUserRivalMusicHandler getUserRivalMusicHandler;

    // Mandatory for boot
    @PostMapping("GetGameEventApi")
    public String getGameEvent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameEventHandler.handle(request);
    }

    @PostMapping("GetGameRankingApi")
    public String getGameRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameRankingHandler.handle(request);
    }

    @PostMapping("GetGameSettingApi")
    public String getGameSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameSettingHandler.handle(request);
    }

    @PostMapping("GetGameTournamentInfoApi")
    public String getGameTournamentInfoHandler(@ModelAttribute Map<String, Object> request)
            throws JsonProcessingException {
        return getGameTournamentInfoHandler.handle(request);
    }

    // Gameplay
    @PostMapping("GetTransferFriendApi")
    public String getTransferFriendHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getTransferFriendHandler.handle(request);
    }

    @PostMapping("GetUserActivityApi")
    public String getUserActivityHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserActivityHandler.handle(request);
    }

    @PostMapping("GetUserCardApi")
    public String getUserCardHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCardHandler.handle(request);
    }

    @PostMapping("GetUserCharacterApi")
    public String getUserCharacterHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCharacterHandler.handle(request);
    }

    @PostMapping("GetUserDataApi")
    public String getUserDataHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDataHandler.handle(request);
    }

    @PostMapping("GetUserExtendApi")
    public String getUserExtendHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserExtendHandler.handle(request);
    }

    @PostMapping("GetUserFavoriteApi")
    public String getUserFavoriteHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserFavoriteHandler.handle(request);
    }

    // No support, return empty
    @PostMapping("GetUserGhostApi")
    public String getUserGhostHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserGhostHandler.handle(request);
    }

    @PostMapping("GetUserItemApi")
    public String getUserItemHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserItemHandler.handle(request);
    }

    @PostMapping("GetUserLoginBonusApi")
    public String getUserLoginBonusHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserLoginBonusHandler.handle(request);
    }

    @PostMapping("GetUserMapApi")
    public String getUserMapHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMapHandler.handle(request);
    }

    @PostMapping("GetUserMusicApi")
    public String getUserMusicHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMusicHandler.handle(request);
    }

    @PostMapping("GetUserOptionApi")
    public String getUserOptionHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserOptionHandler.handle(request);
    }

    @PostMapping("GetUserPortraitApi")
    public String getUserPortraitHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPortraitHandler.handle(request);
    }

    @PostMapping("GetUserPreviewApi")
    public String getUserPreviewHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPreviewHandler.handle(request);
    }

    @PostMapping("GetUserRatingApi")
    public String getUserRatingHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRatingHandler.handle(request);
    }

    // I don't know what it is. return empty
    @PostMapping("GetUserRegionApi")
    public String getUserRegionHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRegionHandler.handle(request);
    }

    // Seems only used for tournament, No Support
    @PostMapping("GetUserScoreRankingApi")
    public String getUserScoreRankingHandler(@ModelAttribute Map<String, Object> request)
            throws JsonProcessingException {
        return "{}";
    }

    @PostMapping("UploadUserPhotoApi")
    public Object uploadUserPhotoHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return uploadUserPhotoHandler.handle(request);
    }

    @PostMapping("UploadUserPlaylogApi")
    public Object uploadUserPlaylogHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return uploadUserPlaylogHandler.handle(request);
    }

    @CrossOrigin // enable cors because aqua-viewer also use it.
    @PostMapping("UploadUserPortraitApi")
    public Object uploadUserPortraitHandler(@ModelAttribute Map<String, Object> request)
            throws JsonProcessingException {
        return uploadUserPortraitHandler.handle(request);
    }

    @PostMapping("UserLoginApi")
    public Object userLoginHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return userLoginHandler.handle(request);
    }

    @PostMapping("UserLogoutApi")
    public String userLogoutHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return userLogoutHandler.handle(request);
    }

    @PostMapping("UpsertClientBookkeepingApi")
    public String upsertClientBookkeeping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertClientBookkeepingApi\"}";
    }

    @PostMapping("UpsertClientSettingApi")
    public String upsertClientSetting(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertClientSettingApi\"}";
    }

    @PostMapping("UpsertClientTestmodeApi")
    public String upsertClientTestmode(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertClientTestmodeApi\"}";
    }

    @PostMapping("UpsertClientUploadApi")
    public String upsertClientUpload(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertClientUploadApi\"}";
    }

    // Score saving
    @PostMapping("UpsertUserAllApi")
    public String upsertUserAllHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserAllHandler.handle(request);
    }

    // Splash plus APIs
    @PostMapping("GetGameChargeApi")
    public String getGameChargeHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameChargeHandler.handle(request);
    }

    @PostMapping("GetUserChargeApi")
    public String getUserChargeHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserChargeHandler.handle(request);
    }

    @PostMapping("UpsertUserChargelogApi")
    public String upsertUserChargelog(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertUserChargelogApi\"}";
    }

    @PostMapping("GetUserCourseApi")
    public String getUserCourseHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCourseHandler.handle(request);
    }

    // Universe APIs
    @PostMapping("GetGameNgMusicIdApi")
    public String getGameNgMusicIdHandler(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameNgMusicIdHandler.handle(request);
    }

    @PostMapping("GetUserFriendSeasonRankingApi")
    public String getUserFriendSeasonRankingHandler(@ModelAttribute Map<String, Object> request)
            throws JsonProcessingException {
        return getUserFriendSeasonRankingHandler.handle(request);
    }

    @PostMapping("Ping")
    String ping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    // Festival APIs
    @PostMapping("CreateTokenApi")
    String createTokenHandler(@ModelAttribute Map<String, Object> request) {
        return "{\"Bearer\":\"AQUATOKEN\"}";
    }

    @PostMapping("RemoveTokenApi")
    String removeTokenHandler(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("GetUserRecommendRateMusicApi")
    public String getUserRecommendRateMusicHandler(@ModelAttribute Map<String, Object> request)
            throws JsonProcessingException {
        return getUserRecommendRateMusicHandler.handle(request);
    }

    @PostMapping("GetUserRecommendSelectMusicApi")
    public String getUserRecommendSelectMusicHandler(@ModelAttribute Map<String, Object> request)
            throws JsonProcessingException {
        return getUserRecommendSelectMusicHandler.handle(request);
    }

    // CardMaker APIs
    @PostMapping("CMGetSellingCardApi")
    String cmGetSellingCard(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetSellingCardHandler.handle(request);
    }

    @PostMapping("CMGetUserCardApi")
    String cmGetUserCard(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCardHandler.handle(request);
    }

    @PostMapping("CMGetUserCardPrintErrorApi")
    String cmGetUserCardPrintError(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCardPrintErrorHandler.handle(request);
    }

    @PostMapping("CMGetUserCharacterApi")
    String cmGetUserCharacter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetUserCharacterHandler.handle(request);
    }

    @PostMapping("CMGetUserDataApi")
    String cmGetUserData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDataHandler.handle(request);
    }

    @PostMapping("CMGetUserItemApi")
    String cmGetUserItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserItemHandler.handle(request);
    }

    @PostMapping("CMGetUserPreviewApi")
    String cmGetUserPreview(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return cmGetUserPreviewHandler.handle(request);
    }

    @PostMapping("CMLoginApi")
    String cmLoginApi(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("CMLogoutApi")
    String cmLogoutApi(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("CMUpsertBuyCardApi")
    String cmUpsertBuyCard(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("CMUpsertUserPrintApi")
    String cmUpsertUserPrint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserPrintHandler.handle(request);
    }

    @PostMapping("CMUpsertUserPrintlogApi")
    String cmUpsertUserPrintlog(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\", \"orderId\":\"0\", \"serialId\":\"FAKECARDIMAG12345678\"}";
    }

    @PostMapping("GetUserFavoriteItemApi")
    String getUserFavoriteItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return this.getUserFavoriteItemHandler.handle(request);
    }

    @PostMapping("GetUserRivalDataApi")
    String getUserRivalData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return this.getUserRivalDataHandler.handle(request);
    }

    @PostMapping("GetUserRivalMusicApi")
    String getUserRivalMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return this.getUserRivalMusicHandler.handle(request);
    }
}
