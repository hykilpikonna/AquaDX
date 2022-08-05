package icu.samnyan.aqua.sega.cardmaker.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.cardmaker.handler.impl.*;
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
@RequestMapping("CardMakerServlet")
public class CardMakerController {
    private final GetGameSettingHandler getGameSettingHandler;
    private final GetClientBookkeepingHandler getClientBookkeepingHandler;
    private final GetGameConnectHandler getGameConnectHandler;

    @Autowired
    public CardMakerController(GetGameSettingHandler getGameSettingHandler, GetClientBookkeepingHandler getClientBookkeepingHandler, GetGameConnectHandler getGameConnectHandler) {
        this.getGameSettingHandler = getGameSettingHandler;
        this.getClientBookkeepingHandler = getClientBookkeepingHandler;
        this.getGameConnectHandler = getGameConnectHandler;
    }

    @PostMapping("GetGameSettingApi")
    public String getGameSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameSettingHandler.handle(request);
    }

    @PostMapping("GetGameConnectApi")
    public String getGameConnect(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameConnectHandler.handle(request);
    }

    @PostMapping("GetClientBookkeepingApi")
    public String getClientBookkeeping(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getClientBookkeepingHandler.handle(request);
    }

    @PostMapping("UpsertClientBookkeepingApi")
    public String upsertClientBookkeeping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"UpsertClientBookkeepingApi\"}";
    }

    @PostMapping("UpsertClientSettingApi")
    public String upsertClientSetting(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"UpsertClientSettingApi\"}";
    }

}
