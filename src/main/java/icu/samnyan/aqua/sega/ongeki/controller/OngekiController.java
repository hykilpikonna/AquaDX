package icu.samnyan.aqua.sega.ongeki.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.ongeki.handler.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("OngekiServlet")
public class OngekiController {

    private final GetGameEventHandler getGameEventHandler;
    private final GetGameIdlistHandler getGameIdlistHandler;
    private final GetGameMessageHandler getGameMessageHandler;
    private final GetGamePointHandler getGamePointHandler;
    private final GetGamePresentHandler getGamePresentHandler;
    private final GetGameTechMusicHandler getGameTechMusicHandler;
    private final GetGameRankingHandler getGameRankingHandler;
    private final GetGameRewardHandler getGameRewardHandler;
    private final GetGameSettingHandler getGameSettingHandler;
    private final GetUserActivityHandler getUserActivityHandler;
    private final GetUserBossHandler getUserBossHandler;
    private final GetUserBpBaseHandler getUserBpBaseHandler;
    private final GetUserCardHandler getUserCardHandler;
    private final GetUserChapterHandler getUserChapterHandler;
    private final GetUserCharacterHandler getUserCharacterHandler;
    private final GetUserDataHandler getUserDataHandler;
    private final GetUserDeckByKeyHandler getUserDeckByKeyHandler;
    private final GetUserEventPointHandler getUserEventPointHandler;
    private final GetUserEventRankingHandler getUserEventRankingHandler;
    private final GetUserEventMusicHandler getUserEventMusicHandler;
    private final GetUserItemHandler getUserItemHandler;
    private final GetUserLoginBonusHandler getUserLoginBonusHandler;
    private final GetUserMissionPointHandler getUserMissionPointHandler;
    private final GetUserMusicHandler getUserMusicHandler;
    private final GetUserMusicItemHandler getUserMusicItemHandler;
    private final GetUserOptionHandler getUserOptionHandler;
    private final GetUserPreviewHandler getUserPreviewHandler;
    private final GetUserRatinglogListHandler getUserRatinglogListHandler;
    private final GetUserRecentRatingHandler getUserRecentRatingHandler;
    private final GetUserRegionHandler getUserRegionHandler;
    private final GetUserScenarioHandler getUserScenarioHandler;
    private final GetUserStoryHandler getUserStoryHandler;
    private final GetUserTechCountHandler getUserTechCountHandler;
    private final GetUserTechEventHandler getUserTechEventHandler;
    private final GetUserTechEventRankingHandler getUserTechEventRankingHandler;
    private final GetUserTradeItemHandler getUserTradeItemHandler;
    private final GetUserTrainingRoomByKeyHandler getUserTrainingRoomByKeyHandler;
    private final GetUserKopHandler getUserKopHandler;
    private final UpsertUserAllHandler upsertUserAllHandler;

    @Autowired
    public OngekiController(GetGameEventHandler getGameEventHandler, GetGameIdlistHandler getGameIdlistHandler, GetGameMessageHandler getGameMessageHandler, GetGamePointHandler getGamePointHandler, GetGamePresentHandler getGamePresentHandler, GetGameRankingHandler getGameRankingHandler, GetGameRewardHandler getGameRewardHandler, GetGameSettingHandler getGameSettingHandler, GetUserActivityHandler getUserActivityHandler, GetUserBossHandler getUserBossHandler, GetUserBpBaseHandler getUserBpBaseHandler, GetUserCardHandler getUserCardHandler, GetUserChapterHandler getUserChapterHandler, GetUserCharacterHandler getUserCharacterHandler, GetUserDataHandler getUserDataHandler, GetUserDeckByKeyHandler getUserDeckByKeyHandler, GetUserEventPointHandler getUserEventPointHandler, GetUserEventRankingHandler getUserEventRankingHandler, GetUserItemHandler getUserItemHandler, GetUserLoginBonusHandler getUserLoginBonusHandler, GetUserMissionPointHandler getUserMissionPointHandler, GetUserMusicHandler getUserMusicHandler, GetUserMusicItemHandler getUserMusicItemHandler, GetUserOptionHandler getUserOptionHandler, GetUserPreviewHandler getUserPreviewHandler, GetUserRatinglogListHandler getUserRatinglogListHandler, GetUserRecentRatingHandler getUserRecentRatingHandler, GetUserRegionHandler getUserRegionHandler, GetUserScenarioHandler getUserScenarioHandler, GetUserStoryHandler getUserStoryHandler, GetUserTechCountHandler getUserTechCountHandler, GetUserTrainingRoomByKeyHandler getUserTrainingRoomByKeyHandler, UpsertUserAllHandler upsertUserAllHandler, GetGameTechMusicHandler getGameTechMusicHandler, GetUserTechEventHandler getUserTechEventHandler, GetUserTechEventRankingHandler getUserTechEventRankingHandler, GetUserEventMusicHandler getUserEventMusicHandler, GetUserTradeItemHandler getUserTradeItemHandler, GetUserKopHandler getUserKopHandler) {
        this.getGameEventHandler = getGameEventHandler;
        this.getGameIdlistHandler = getGameIdlistHandler;
        this.getGameMessageHandler = getGameMessageHandler;
        this.getGamePointHandler = getGamePointHandler;
        this.getGamePresentHandler = getGamePresentHandler;
        this.getGameRankingHandler = getGameRankingHandler;
        this.getGameRewardHandler = getGameRewardHandler;
        this.getGameSettingHandler = getGameSettingHandler;
        this.getUserActivityHandler = getUserActivityHandler;
        this.getUserBossHandler = getUserBossHandler;
        this.getUserBpBaseHandler = getUserBpBaseHandler;
        this.getUserCardHandler = getUserCardHandler;
        this.getUserChapterHandler = getUserChapterHandler;
        this.getUserCharacterHandler = getUserCharacterHandler;
        this.getUserDataHandler = getUserDataHandler;
        this.getUserDeckByKeyHandler = getUserDeckByKeyHandler;
        this.getUserEventPointHandler = getUserEventPointHandler;
        this.getUserEventRankingHandler = getUserEventRankingHandler;
        this.getUserItemHandler = getUserItemHandler;
        this.getUserLoginBonusHandler = getUserLoginBonusHandler;
        this.getUserMissionPointHandler = getUserMissionPointHandler;
        this.getUserMusicHandler = getUserMusicHandler;
        this.getUserMusicItemHandler = getUserMusicItemHandler;
        this.getUserOptionHandler = getUserOptionHandler;
        this.getUserPreviewHandler = getUserPreviewHandler;
        this.getUserRatinglogListHandler = getUserRatinglogListHandler;
        this.getUserRecentRatingHandler = getUserRecentRatingHandler;
        this.getUserRegionHandler = getUserRegionHandler;
        this.getUserScenarioHandler = getUserScenarioHandler;
        this.getUserStoryHandler = getUserStoryHandler;
        this.getUserTechCountHandler = getUserTechCountHandler;
        this.getUserTrainingRoomByKeyHandler = getUserTrainingRoomByKeyHandler;
        this.upsertUserAllHandler = upsertUserAllHandler;
        this.getGameTechMusicHandler = getGameTechMusicHandler;
        this.getUserTechEventHandler = getUserTechEventHandler;
        this.getUserTechEventRankingHandler = getUserTechEventRankingHandler;
        this.getUserEventMusicHandler = getUserEventMusicHandler;
        this.getUserTradeItemHandler = getUserTradeItemHandler;
        this.getUserKopHandler = getUserKopHandler;
    }

    @PostMapping("ExtendLockTimeApi")
    public String extendLockTime(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"extendLockTime\"}";
    }

    @PostMapping("GameLoginApi")
    public String gameLogin(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"gameLogin\"}";
    }

    @PostMapping("GameLogoutApi")
    public String gameLogout(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\": \"gameLogout\"}";
    }

    @PostMapping("GetGameEventApi")
    public String getGameEvent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameEventHandler.handle(request);
    }

    @PostMapping("GetGameIdlistApi")
    public String getGameIdList(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameIdlistHandler.handle(request);
    }

    @PostMapping("GetGameMessageApi")
    public String getGameMessage(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameMessageHandler.handle(request);
    }

    @PostMapping("GetGamePointApi")
    public String getGamePoint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGamePointHandler.handle(request);
    }

    @PostMapping("GetGamePresentApi")
    public String getGamePresent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGamePresentHandler.handle(request);
    }

    @PostMapping("GetGameTechMusicApi")
    public String getGameTechMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameTechMusicHandler.handle(request);
    }

    @PostMapping("GetUserTechEventApi")
    public String getUserTechEvent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTechEventHandler.handle(request);
    }

    @PostMapping("GetUserTechEventRankingApi")
    public String getUserTechEventRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTechEventRankingHandler.handle(request);
    }

    @PostMapping("GetGameRankingApi")
    public String getGameRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameRankingHandler.handle(request);
    }

    @PostMapping("GetGameRewardApi")
    public String getGameReward(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameRewardHandler.handle(request);
    }

    @PostMapping("GetGameSettingApi")
    public String getGameSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameSettingHandler.handle(request);
    }

    @PostMapping("GetUserActivityApi")
    public String getUserActivity(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserActivityHandler.handle(request);
    }

    @PostMapping("GetUserBossApi")
    public String getUserBoss(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserBossHandler.handle(request);
    }

    @PostMapping("GetUserBpBaseApi")
    public String getUserBpBase(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserBpBaseHandler.handle(request);
    }

    @PostMapping("GetUserCardApi")
    public String getUserCard(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCardHandler.handle(request);
    }
    @PostMapping("GetUserChapterApi")
    public String getUserChapter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserChapterHandler.handle(request);
    }

    @PostMapping("GetUserCharacterApi")
    public String getUserCharacter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCharacterHandler.handle(request);
    }

    @PostMapping("GetUserDataApi")
    public String getUserData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDataHandler.handle(request);
    }

    @PostMapping("GetUserDeckByKeyApi")
    public String getUserDeckByKey(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDeckByKeyHandler.handle(request);
    }

    @PostMapping("GetUserEventPointApi")
    public String getUserEventPoint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserEventPointHandler.handle(request);
    }

    @PostMapping("GetUserEventMusicApi")
    public String getUserEventMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserEventMusicHandler.handle(request);
    }

    @PostMapping("GetUserTradeItemApi")
    public String getUserTradeItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTradeItemHandler.handle(request);
    }

    @PostMapping("GetUserEventRankingApi")
    public String getUserEventRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserEventRankingHandler.handle(request);
    }

    @PostMapping("GetUserItemApi")
    public String getUserItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserItemHandler.handle(request);
    }

    @PostMapping("GetUserLoginBonusApi")
    public String getUserLoginBonus(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserLoginBonusHandler.handle(request);
    }

    @PostMapping("GetUserMissionPointApi")
    public String getUserMissionPoint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMissionPointHandler.handle(request);
    }

    @PostMapping("GetUserMusicApi")
    public String getUserMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMusicHandler.handle(request);
    }

    @PostMapping("GetUserMusicItemApi")
    public String getUserMusicItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMusicItemHandler.handle(request);
    }

    @PostMapping("GetUserOptionApi")
    public String getUserOption(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserOptionHandler.handle(request);
    }

    @PostMapping("GetUserPreviewApi")
    public String getUserPreview(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPreviewHandler.handle(request);
    }

    @PostMapping("GetUserRatinglogApi")
    public String getUserRatinglog(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRatinglogListHandler.handle(request);
    }

    @PostMapping("GetUserRecentRatingApi")
    public String getUserRecentRating(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRecentRatingHandler.handle(request);
    }

    @PostMapping("GetUserRegionApi")
    public String getUserRegion(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRegionHandler.handle(request);
    }

    @PostMapping("GetUserScenarioApi")
    public String getUserScenario(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserScenarioHandler.handle(request);
    }

    @PostMapping("GetUserStoryApi")
    public String getUserStory(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserStoryHandler.handle(request);
    }

    @PostMapping("GetUserTechCountApi")
    public String getUserTechCount(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTechCountHandler.handle(request);
    }

    @PostMapping("GetUserTrainingRoomByKeyApi")
    public String getUserTrainingRoomByKey(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTrainingRoomByKeyHandler.handle(request);
    }

    @PostMapping("GetUserKopApi")
    public String getUserKopApi(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserKopHandler.handle(request);
    }

    @PostMapping("UpsertClientBookkeepingApi")
    public String upsertClientBookkeeping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1},\"apiName\":\"upsertClientBookkeeping\"";
    }

    @PostMapping("UpsertClientDevelopApi")
    public String upsertClientDevelop(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1},\"apiName\":\"upsertClientDevelop\"";
    }

    @PostMapping("UpsertClientErrorApi")
    public String upsertClientError(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertClientError\"}";
    }

    @PostMapping("UpsertClientSettingApi")
    public String upsertClientSetting(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertClientSetting\"}";
    }

    @PostMapping("UpsertClientTestmodeApi")
    public String upsertClientTestmode(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertClientTestmode\"}";
    }

    @PostMapping("UpsertUserGplogApi")
    public String upsertUserGplog(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertUserGplog\"}";
    }

    @PostMapping("UpsertUserAllApi")
    public String upsertUserAll(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserAllHandler.handle(request);
    }

}
