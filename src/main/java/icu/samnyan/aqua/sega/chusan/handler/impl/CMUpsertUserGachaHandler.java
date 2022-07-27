package icu.samnyan.aqua.sega.chusan.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.chusan.dao.userdata.UserCardPrintStateRepository;
import icu.samnyan.aqua.sega.chusan.dao.userdata.UserGachaRepository;
import icu.samnyan.aqua.sega.chusan.handler.BaseHandler;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameGachaCard;
import icu.samnyan.aqua.sega.chusan.model.request.UpsertUserGacha;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserCardPrintState;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserGacha;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserItem;
import icu.samnyan.aqua.sega.chusan.service.UserDataService;
import icu.samnyan.aqua.sega.chusan.service.UserItemService;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("ChusanCMUpsertUserGachaHandler")
public class CMUpsertUserGachaHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(CMUpsertUserGachaHandler.class);
    private final UserCardPrintStateRepository userCardPrintStateRepository;
    private final UserGachaRepository userGachaRepository;
    private final UserDataService userDataService;
    private final UserItemService userItemService;
    private final BasicMapper mapper;

    @Autowired
    public CMUpsertUserGachaHandler(UserItemService userItemService, UserDataService userDataService,
    UserCardPrintStateRepository userCardPrintStateRepository, BasicMapper mapper,
    UserGachaRepository userGachaRepository) {
        this.userCardPrintStateRepository = userCardPrintStateRepository;
        this.userGachaRepository = userGachaRepository;
        this.userDataService = userDataService;
        this.userItemService = userItemService;
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = String.valueOf(request.get("userId"));
        int gachaId = ((Number) request.get("gachaId")).intValue();
        int placeId = ((Number) request.get("placeId")).intValue();

        UpsertUserGacha upsertUserGacha = mapper.convert(request.get("cmUpsertUserGacha"), UpsertUserGacha.class);
        List<UserCardPrintState> userCardPrintStateList = new ArrayList<>();
        UserData userData;

        Optional<UserData> userOptional = userDataService.getUserByExtId(userId);
        if (userOptional.isPresent()) {
            userData = userOptional.get();
        } else {
            logger.error("User not found. userId: {}", userId);
            return null;
        }
        
        if (upsertUserGacha.getGameGachaCardList() != null) {
            for (GameGachaCard gameGachaCard : upsertUserGacha.getGameGachaCardList()) {
                UserCardPrintState userCardPrintState = new UserCardPrintState();
                userCardPrintState.setHasCompleted(false);
                userCardPrintState.setLimitDate(LocalDateTime.of(2029, 01, 01, 0, 0));
                userCardPrintState.setPlaceId(placeId);
                userCardPrintState.setCardId(gameGachaCard.getCardId());
                userCardPrintState.setGachaId(gachaId);
                userCardPrintState.setUser(userData);
                userCardPrintStateRepository.save(userCardPrintState);
            }
        }

        List<UserItem> userItemListToSave = new ArrayList<>();
        List<UserItem> userItemList = upsertUserGacha.getUserItemList();

        userItemList.forEach(newUserItem -> {
            int itemId = newUserItem.getItemId();
            int itemKind = newUserItem.getItemKind();

            Optional<UserItem> userItemOptional = userItemService.getByUserAndItemIdAndKind(userData, itemId, itemKind);
            UserItem userItem = userItemOptional.orElseGet(() -> new UserItem(userData));

            newUserItem.setId(userItem.getId());
            newUserItem.setUser(userItem.getUser());

            userItemListToSave.add(newUserItem);
        });
        userItemService.saveAll(userItemListToSave);

        if (upsertUserGacha.getUserGacha() != null) {
            UserGacha newUserGacha = upsertUserGacha.getUserGacha();
            newUserGacha.setUser(userData);
            userGachaRepository.save(newUserGacha);
        }

        userCardPrintStateList = userCardPrintStateRepository.findByUserAndGachaIdAndHasCompleted(userData, gachaId, false);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("returnCode", 1);
        resultMap.put("apiName", "CMUpsertUserGachaApi");
        resultMap.put("userCardPrintStateList", userCardPrintStateList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
