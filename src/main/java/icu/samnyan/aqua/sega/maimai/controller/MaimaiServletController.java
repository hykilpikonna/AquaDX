package icu.samnyan.aqua.sega.maimai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai.handler.impl.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("MaimaiServlet")
public class MaimaiServletController {

    private final GetGameEventHandler getGameEventHandler;
    private final GetGameRankingHandler getGameRankingHandler;
    private final GetGameSettingHandler getGameSettingHandler;
    private final GetTransferFriendHandler getTransferFriendHandler;
    private final GetUserActivityHandler getUserActivityHandler;
    private final GetUserBossHandler getUserBossHandler;
    private final GetUserCharacterHandler getUserCharacterHandler;
    private final GetUserCourseHandler getUserCourseHandler;
    private final GetUserDataHandler getUserDataHandler;
    private final GetUserGradeHandler getUserGradeHandler;
    private final GetUserItemHandler getUserItemHandler;
    private final GetUserMusicHandler getUserMusicHandler;
    private final GetUserOptionHandler getUserOptionHandler;
    private final GetUserPresentEventHandler getUserPresentEventHandler;
    private final GetUserPresentHandler getUserPresentHandler;
    private final GetUserPreviewHandler getUserPreviewHandler;
    private final GetUserRecentRatingHandler getUserRecentRatingHandler;
    private final GetUserSurvivalHandler getUserSurvivalHandler;
    private final GetUserWebOptionHandler getUserWebOptionHandler;
    private final UpsertTransferHandler upsertTransferHandler;
    private final UpsertUserAllHandler upsertUserAllHandler;
    private final UserLoginHandler userLoginHandler;
    private final UserLogoutHandler userLogoutHandler;

    public MaimaiServletController(GetGameEventHandler getGameEventHandler, GetGameRankingHandler getGameRankingHandler, GetGameSettingHandler getGameSettingHandler, GetTransferFriendHandler getTransferFriendHandler, GetUserActivityHandler getUserActivityHandler, GetUserBossHandler getUserBossHandler, GetUserCharacterHandler getUserCharacterHandler, GetUserCourseHandler getUserCourseHandler, GetUserDataHandler getUserDataHandler, GetUserGradeHandler getUserGradeHandler, GetUserItemHandler getUserItemHandler, GetUserMusicHandler getUserMusicHandler, GetUserOptionHandler getUserOptionHandler, GetUserPresentEventHandler getUserPresentEventHandler, GetUserPresentHandler getUserPresentHandler, GetUserPreviewHandler getUserPreviewHandler, GetUserRecentRatingHandler getUserRecentRatingHandler, GetUserSurvivalHandler getUserSurvivalHandler, GetUserWebOptionHandler getUserWebOptionHandler, UpsertTransferHandler upsertTransferHandler, UpsertUserAllHandler upsertUserAllHandler, UserLoginHandler userLoginHandler, UserLogoutHandler userLogoutHandler) {
        this.getGameEventHandler = getGameEventHandler;
        this.getGameRankingHandler = getGameRankingHandler;
        this.getGameSettingHandler = getGameSettingHandler;
        this.getTransferFriendHandler = getTransferFriendHandler;
        this.getUserActivityHandler = getUserActivityHandler;
        this.getUserBossHandler = getUserBossHandler;
        this.getUserCharacterHandler = getUserCharacterHandler;
        this.getUserCourseHandler = getUserCourseHandler;
        this.getUserDataHandler = getUserDataHandler;
        this.getUserGradeHandler = getUserGradeHandler;
        this.getUserItemHandler = getUserItemHandler;
        this.getUserMusicHandler = getUserMusicHandler;
        this.getUserOptionHandler = getUserOptionHandler;
        this.getUserPresentEventHandler = getUserPresentEventHandler;
        this.getUserPresentHandler = getUserPresentHandler;
        this.getUserPreviewHandler = getUserPreviewHandler;
        this.getUserRecentRatingHandler = getUserRecentRatingHandler;
        this.getUserSurvivalHandler = getUserSurvivalHandler;
        this.getUserWebOptionHandler = getUserWebOptionHandler;
        this.upsertTransferHandler = upsertTransferHandler;
        this.upsertUserAllHandler = upsertUserAllHandler;
        this.userLoginHandler = userLoginHandler;
        this.userLogoutHandler = userLogoutHandler;
    }

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

    @PostMapping("GetUserActivityApi")
    public String getUserActivity(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserActivityHandler.handle(request);
    }

    @PostMapping("GetUserBossApi")
    public String getUserBoss(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserBossHandler.handle(request);
    }

    @PostMapping("GetUserCharacterApi")
    public String getUserCharacter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCharacterHandler.handle(request);
    }

    @PostMapping("GetUserCourseApi")
    public String getUserCourse(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCourseHandler.handle(request);
    }

    @PostMapping("GetUserDataApi")
    public String getUserData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDataHandler.handle(request);
    }

    @PostMapping("GetTransferFriendApi")
    public String getTransferFriend(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getTransferFriendHandler.handle(request);
    }

    @PostMapping("GetUserItemApi")
    public String getUserItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserItemHandler.handle(request);
    }

    @PostMapping("GetUserMusicApi")
    public String getUserMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMusicHandler.handle(request);
    }

    @PostMapping("GetUserOptionApi")
    public String getUserOption(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserOptionHandler.handle(request);
    }

    @PostMapping("GetUserPresent")
    public String getUserPresent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPresentHandler.handle(request);
    }

    @PostMapping("GetUserPresentEventApi")
    public String getUserPresentEvent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPresentEventHandler.handle(request);
    }

    @PostMapping("GetUserPreviewApi")
    public String getUserPreview(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPreviewHandler.handle((request));
    }

    @PostMapping("GetUserGradeApi")
    public String getUserGrade(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserGradeHandler.handle(request);
    }

    @PostMapping("GetUserRecentRatingApi")
    public String getUserRecentRating(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRecentRatingHandler.handle(request);
    }

    @PostMapping("GetUserSurvivalApi")
    public String getUserSurvival(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserSurvivalHandler.handle(request);
    }

    @PostMapping("GetUserWebOptionApi")
    public String getUserWebOption(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserWebOptionHandler.handle(request);
    }

    @PostMapping("UpsertTransferApi")
    public String upsertTransfer(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertTransferHandler.handle(request);
    }

    @PostMapping("UpsertUserAllApi")
    public String upsertUserAll(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserAllHandler.handle(request);
    }

    @PostMapping("UserLoginApi")
    public String userLogin(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return userLoginHandler.handle(request);
    }

    @PostMapping("UserLogoutApi")
    public String userLogout(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return userLogoutHandler.handle(request);
    }

    @PostMapping("UpsertClientBookkeepingApi")
    public String upsertClientBookkeeping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":\"1\"}";
    }

    @PostMapping("UpsertClientSettingApi")
    public String upsertClientSetting(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimaiservlet.api.UpsertClientSettingApi\"}";
    }

    @PostMapping("UpsertClientTestmodeApi")
    public String upsertClientTestmode(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimaiservlet.api.UpsertClientTestmodeApi\"}";
    }


}
