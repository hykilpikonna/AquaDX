package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.chunithm.model.response.CodeResp;
import icu.samnyan.aqua.sega.chunithm.model.userdata.*;
import icu.samnyan.aqua.sega.chunithm.service.*;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The handler for save user data. Only send in the end of the session.
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class UpsertUserAllHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpsertUserAllHandler.class);

    private final StringMapper mapper;

    private final CardService cardService;

    private final UserDataService userDataService;
    private final UserCharacterService userCharacterService;
    private final UserGameOptionService userGameOptionService;
    private final UserGameOptionExService userGameOptionExService;
    private final UserMapService userMapService;
    private final UserItemService userItemService;
    private final UserMusicDetailService userMusicDetailService;
    private final UserActivityService userActivityService;
    private final UserPlaylogService userPlaylogService;
    private final UserChargeService userChargeService;
    private final UserDataExService userDataExService;
    private final UserCourseService userCourseService;
    private final UserDuelService userDuelService;

    @Autowired
    public UpsertUserAllHandler(StringMapper mapper, CardService cardService, UserDataService userDataService, UserCharacterService userCharacterService, UserGameOptionService userGameOptionService, UserGameOptionExService userGameOptionExService, UserMapService userMapService, UserItemService userItemService, UserMusicDetailService userMusicDetailService, UserActivityService userActivityService, UserPlaylogService userPlaylogService, UserChargeService userChargeService, UserDataExService userDataExService, UserCourseService userCourseService, UserDuelService userDuelService) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userDataService = userDataService;
        this.userCharacterService = userCharacterService;
        this.userGameOptionService = userGameOptionService;
        this.userGameOptionExService = userGameOptionExService;
        this.userMapService = userMapService;
        this.userItemService = userItemService;
        this.userMusicDetailService = userMusicDetailService;
        this.userActivityService = userActivityService;
        this.userPlaylogService = userPlaylogService;
        this.userChargeService = userChargeService;
        this.userDataExService = userDataExService;
        this.userCourseService = userCourseService;
        this.userDuelService = userDuelService;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = (String) request.get("userId");
        Map<String, Object> upsertUserAll = (Map<String, Object>) request.get("upsertUserAll");

        // Not all field will be sent. Check if they are exist first.

        UserData userData;
        UserData newUserData;
        // UserData
        if (!upsertUserAll.containsKey("userData")) {
            return null;
        } else {
            Map<String, Object> userDataMap = ((List<Map<String, Object>>) upsertUserAll.get("userData")).get(0);

            Optional<UserData> userOptional = userDataService.getUserByExtId(userId);


            if (userOptional.isPresent()) {
                userData = userOptional.get();
            } else {
                userData = new UserData();
                Card card = cardService.getCardByExtId(userId).orElseThrow();
                userData.setCard(card);
            }

            // Map the map to object
            newUserData = mapper.convert(userDataMap, UserData.class);

            newUserData.setId(userData.getId());
            newUserData.setCard(userData.getCard());


            // Decode Username
            String userName = new String(newUserData.getUserName()
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);


            newUserData.setUserName(userName);
            userDataService.saveAndFlushUserData(newUserData);
        }

        // userGameOption
        if (upsertUserAll.containsKey("userGameOption")) {
            Map<String, Object> userGameOptionMap = ((List<Map<String, Object>>) upsertUserAll.get("userGameOption")).get(0);

            Optional<UserGameOption> userGameOptionOptional = userGameOptionService.getByUser(newUserData);

            UserGameOption userGameOption = userGameOptionOptional.orElseGet(() -> new UserGameOption(newUserData));

            UserGameOption newUserGameOption = mapper.convert(userGameOptionMap, UserGameOption.class);
            newUserGameOption.setId(userGameOption.getId());
            newUserGameOption.setUser(userGameOption.getUser());

            userGameOptionService.save(newUserGameOption);


        }

        // userGameOptionEx
        if (upsertUserAll.containsKey("userGameOptionEx")) {
            Map<String, Object> userGameOptionExMap = ((List<Map<String, Object>>) upsertUserAll.get("userGameOptionEx")).get(0);

            Optional<UserGameOptionEx> userGameOptionExOptional = userGameOptionExService.getByUser(newUserData);
            UserGameOptionEx userGameOptionEx = userGameOptionExOptional.orElseGet(() -> new UserGameOptionEx(newUserData));

            UserGameOptionEx newUserGameOptionEx = mapper.convert(userGameOptionExMap, UserGameOptionEx.class);
            newUserGameOptionEx.setId(userGameOptionEx.getId());
            newUserGameOptionEx.setUser(userGameOptionEx.getUser());

            userGameOptionExService.save(newUserGameOptionEx);
        }

        // userMapList
        if (upsertUserAll.containsKey("userMapList")) {

            int mapPos = 0;

            List<Map<String, Object>> userMapList = (List<Map<String, Object>>) upsertUserAll.get("userMapList");
            Map<String, UserMap> newUserMapMap = new HashMap<>();

            userMapList.forEach(userMapListMap -> {
                String mapId = (String) userMapListMap.get("mapId");
                UserMap userMap;
                Optional<UserMap> userMapOptional = userMapService.getByUserAndMapId(newUserData, mapId);
                userMap = userMapOptional.orElseGet(() -> new UserMap(newUserData));


                UserMap newUserMap = mapper.convert(userMapListMap, UserMap.class);
                newUserMap.setId(userMap.getId());
                newUserMap.setUser(userMap.getUser());

                newUserMapMap.put(mapId, newUserMap);

            });
            userMapService.saveAll(newUserMapMap.values());
        }

        // userCharacterList
        if (upsertUserAll.containsKey("userCharacterList")) {

            List<Map<String, Object>> userCharacterList = (List<Map<String, Object>>) upsertUserAll.get("userCharacterList");
            Map<String, UserCharacter> newCharacterMap = new HashMap<>();

            userCharacterList.forEach(userCharacterMap -> {
                String characterId = (String) userCharacterMap.get("characterId");

                Optional<UserCharacter> userCharacterOptional = userCharacterService.getByUserAndCharacterId(newUserData, characterId);
                UserCharacter userCharacter = userCharacterOptional.orElseGet(() -> new UserCharacter(newUserData));

                UserCharacter newUserCharacter = mapper.convert(userCharacterMap, UserCharacter.class);
                newUserCharacter.setId(userCharacter.getId());
                newUserCharacter.setUser(userCharacter.getUser());

                newCharacterMap.put(characterId, newUserCharacter);
            });
            userCharacterService.saveAll(newCharacterMap.values());
        }

        // userItemList
        if (upsertUserAll.containsKey("userItemList")) {

            List<Map<String, Object>> userItemList = (List<Map<String, Object>>) upsertUserAll.get("userItemList");
            Map<String, UserItem> newUserItemMap = new HashMap<>();

            userItemList.forEach(userItemMap -> {
                String itemId = (String) userItemMap.get("itemId");


                Optional<UserItem> userItemOptional = userItemService.getByUserAndItemId(newUserData, itemId);
                UserItem userItem = userItemOptional.orElseGet(() -> new UserItem(newUserData));

                UserItem newUserItem = mapper.convert(userItemMap, UserItem.class);
                newUserItem.setId(userItem.getId());
                newUserItem.setUser(userItem.getUser());

                newUserItemMap.put(itemId, newUserItem);
            });
            userItemService.saveAll(newUserItemMap.values());
        }

        // userMusicDetailList
        if (upsertUserAll.containsKey("userMusicDetailList")) {

            List<Map<String, Object>> userMusicDetailList = (List<Map<String, Object>>) upsertUserAll.get("userMusicDetailList");
            Map<String, UserMusicDetail> newUserMusicDetailMap = new HashMap<>();

            userMusicDetailList.forEach(userMusicDetailMap -> {
                String musicId = (String) userMusicDetailMap.get("musicId");
                String level = (String) userMusicDetailMap.get("level");

                Optional<UserMusicDetail> userMusicDetailOptional = userMusicDetailService.getByUserAndMusicIdAndLevel(newUserData, musicId, level);
                UserMusicDetail userMusicDetail = userMusicDetailOptional.orElseGet(() -> new UserMusicDetail(newUserData));

                UserMusicDetail newUserMusicDetail = mapper.convert(userMusicDetailMap, UserMusicDetail.class);
                newUserMusicDetail.setId(userMusicDetail.getId());
                newUserMusicDetail.setUser(userMusicDetail.getUser());

                newUserMusicDetailMap.put(musicId + "," + level, newUserMusicDetail);

            });
            userMusicDetailService.saveAll(newUserMusicDetailMap.values());

        }

        // userActivityList
        if (upsertUserAll.containsKey("userActivityList")) {

            List<Map<String, Object>> userActivityList = (List<Map<String, Object>>) upsertUserAll.get("userActivityList");
            userActivityList.forEach(userActivityMap -> {
                // No need to rename to activityId. jackson auto handle that
                String activityId = (String) userActivityMap.get("id");
                String kind = (String) userActivityMap.get("kind");

                Optional<UserActivity> userActivityOptional = userActivityService.getByUserAndActivityIdAndKind(newUserData, activityId, kind);
                UserActivity userActivity = userActivityOptional.orElseGet(() -> new UserActivity(newUserData));

                UserActivity newUserActivity = mapper.convert(userActivityMap, UserActivity.class);
                newUserActivity.setId(userActivity.getId());
                newUserActivity.setUser(userActivity.getUser());

                userActivityService.save(newUserActivity);

            });
        }

        // userRecentRatingList

        // userChargeList

        if (upsertUserAll.containsKey("userChargeList")) {
            List<Map<String, Object>> userChargeList = (List<Map<String, Object>>) upsertUserAll.get("userChargeList");
            List<UserCharge> newUserChargeList = new ArrayList<>();
            userChargeList.forEach(userChargeMap -> {
                String chargeId = (String) userChargeMap.get("chargeId");

                Optional<UserCharge> userChargeOptional = userChargeService.getByUserAndChargeId(newUserData, chargeId);
                UserCharge userCharge = userChargeOptional.orElseGet(() -> new UserCharge(newUserData));

                UserCharge newUserCharge = mapper.convert(userChargeMap, UserCharge.class);
                newUserCharge.setId(userCharge.getId());
                newUserCharge.setUser(userCharge.getUser());
                newUserChargeList.add(newUserCharge);
            });

            userChargeService.saveAll(newUserChargeList);
        }

        // userPlaylogList
        if (upsertUserAll.containsKey("userPlaylogList")) {

            List<Map<String, Object>> userPlaylogList = (List<Map<String, Object>>) upsertUserAll.get("userPlaylogList");
            List<UserPlaylog> newUserPlaylogList = new ArrayList<>();
            userPlaylogList.forEach(userPlayLogMap -> {

                UserPlaylog newUserPlaylog = mapper.convert(userPlayLogMap, UserPlaylog.class);
                newUserPlaylog.setUser(newUserData);

                newUserPlaylogList.add(newUserPlaylog);
            });

            if (newUserPlaylogList.size() > 0) userPlaylogService.saveAll(newUserPlaylogList);
        }


        // userCourseList
        if (upsertUserAll.containsKey("userCourseList")) {
            List<Map<String, Object>> userCourseList = (List<Map<String, Object>>) upsertUserAll.get("userCourseList");

            userCourseList.forEach(userCourseMap -> {
                String courseId = (String) userCourseMap.get("courseId");

                Optional<UserCourse> userCourseOptional = userCourseService.getByUserAndCourseId(newUserData, courseId);
                UserCourse userCourse = userCourseOptional.orElseGet(() -> new UserCourse(newUserData));

                UserCourse newUserCourse = mapper.convert(userCourseMap, UserCourse.class);
                newUserCourse.setId(userCourse.getId());
                newUserCourse.setUser(userCourse.getUser());

                userCourseService.save(newUserCourse);
            });
        }


        // userDataEx
        if (upsertUserAll.containsKey("userDataEx")) {
            Map<String, Object> userDataExMap = ((List<Map<String, Object>>) upsertUserAll.get("userDataEx")).get(0);

            Optional<UserDataEx> userDataExOptional = userDataExService.getByUser(newUserData);
            UserDataEx userDataEx = userDataExOptional.orElseGet(() -> new UserDataEx(newUserData));

            UserDataEx newUserDataEx = mapper.convert(userDataExMap, UserDataEx.class);
            newUserDataEx.setId(userDataEx.getId());
            newUserDataEx.setUser(userDataEx.getUser());

            userDataExService.save(newUserDataEx);
        }

        // userDuelList
        if (upsertUserAll.containsKey("userDuelList")) {

            List<Map<String, Object>> userDuelList = (List<Map<String, Object>>) upsertUserAll.get("userDuelList");
            Map<String, UserDuel> newUserDuelMap = new HashMap<>();
            userDuelList.forEach(userDuelMap -> {
                String duelId = (String) userDuelMap.get("duelId");

                Optional<UserDuel> userDuelOptional = userDuelService.getByUserAndDuelId(newUserData, duelId);
                UserDuel userDuel = userDuelOptional.orElseGet(() -> new UserDuel(newUserData));

                UserDuel newUserDuel = mapper.convert(userDuelMap, UserDuel.class);
                newUserDuel.setId(userDuel.getId());
                newUserDuel.setUser(userDuel.getUser());

                newUserDuelMap.put(duelId, newUserDuel);
            });

            userDuelService.saveAll(newUserDuelMap.values());
        }

        String json = mapper.write(new CodeResp(1));
        logger.info("Response: " + json);
        return json;
    }
}
