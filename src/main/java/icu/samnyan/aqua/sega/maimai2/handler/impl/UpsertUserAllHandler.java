package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.*;
import icu.samnyan.aqua.sega.maimai.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.request.UpsertUserAll;
import icu.samnyan.aqua.sega.maimai2.model.request.data.UserAll;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserActivity;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRating;
import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2UpsertUserAllHandler")
public class UpsertUserAllHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpsertUserAllHandler.class);

    private final BasicMapper mapper;

    private final CardService cardService;

    private final UserDataRepository userDataRepository;
    private final UserExtendRepository userExtendRepository;
    private final UserOptionRepository userOptionRepository;
    private final UserItemRepository userItemRepository;
    private final UserMusicDetailRepository userMusicDetailRepository;
    private final UserActRepository userActRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserMapRepository userMapRepository;
    private final UserLoginBonusRepository userLoginBonusRepository;
    private final UserFavoriteRepository userFavoriteRepository;
    private final UserRateRepository userRateRepository;
    private final UserUdemaeRepository userUdemaeRepository;

    public UpsertUserAllHandler(BasicMapper mapper, CardService cardService, UserDataRepository userDataRepository, UserExtendRepository userExtendRepository, UserOptionRepository userOptionRepository, UserItemRepository userItemRepository, UserMusicDetailRepository userMusicDetailRepository, UserActRepository userActRepository, UserCharacterRepository userCharacterRepository, UserMapRepository userMapRepository, UserLoginBonusRepository userLoginBonusRepository, UserFavoriteRepository userFavoriteRepository, UserRateRepository userRateRepository, UserUdemaeRepository userUdemaeRepository) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userDataRepository = userDataRepository;
        this.userExtendRepository = userExtendRepository;
        this.userOptionRepository = userOptionRepository;
        this.userItemRepository = userItemRepository;
        this.userMusicDetailRepository = userMusicDetailRepository;
        this.userActRepository = userActRepository;
        this.userCharacterRepository = userCharacterRepository;
        this.userMapRepository = userMapRepository;
        this.userLoginBonusRepository = userLoginBonusRepository;
        this.userFavoriteRepository = userFavoriteRepository;
        this.userRateRepository = userRateRepository;
        this.userUdemaeRepository = userUdemaeRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        UpsertUserAll upsertUserAll = mapper.convert(request, UpsertUserAll.class);
        long userId = upsertUserAll.getUserId();
        UserAll userAll = upsertUserAll.getUpsertUserAll();

        // UserData
        UserDetail userData;
        UserDetail newUserData;
        if (userAll.getUserData() == null) {
            return null;
        } else {
            newUserData = userAll.getUserData().get(0);
            Optional<UserDetail> userOptional = userDataRepository.findByCard_ExtId(userId);

            if (userOptional.isPresent()) {
                userData = userOptional.get();
            } else {
                userData = new UserDetail();
                Card card = cardService.getCardByExtId(userId).orElseThrow();
                userData.setCard(card);
            }

            newUserData.setId(userData.getId());
            newUserData.setCard(userData.getCard());
            // Decode Username
            String userName = new String(newUserData.getUserName());

            newUserData.setUserName(userName);
            userDataRepository.saveAndFlush(newUserData);
        }

        // UserExtend
        if (userAll.getUserExtend() != null) {
            UserExtend newUserExtend = userAll.getUserExtend().get(0);

            Optional<UserExtend> userExtendOptional = userExtendRepository.findByUser(newUserData);
            UserExtend userExtend = userExtendOptional.orElseGet(() -> new UserExtend(newUserData));

            newUserExtend.setId(userExtend.getId());
            newUserExtend.setUser(userExtend.getUser());

            userExtendRepository.save(newUserExtend);
        }

        // UserOption
        if (userAll.getUserOption() != null) {
            UserOption newUserOption = userAll.getUserOption().get(0);

            Optional<UserOption> userOptionOptional = userOptionRepository.findByUser(newUserData);
            UserOption userOption = userOptionOptional.orElseGet(() -> new UserOption(newUserData));

            newUserOption.setId(userOption.getId());
            newUserOption.setUser(userOption.getUser());

            userOptionRepository.save(newUserOption);
        }

        // UserCharacterList
        if (userAll.getUserCharacterList() != null) {
            List<UserCharacter> userCharacterList = userAll.getUserCharacterList();
            List<UserCharacter> newUserCharacterList = new ArrayList<>();
            for (UserCharacter newUserCharacter : userCharacterList) {
                int id = newUserCharacter.getCharacterId();

                Optional<UserCharacter> characterOptional = userCharacterRepository.findByUserAndCharacterId(newUserData, id);
                UserCharacter userCharacter = characterOptional.orElseGet(() -> new UserCharacter(newUserData));

                newUserCharacter.setId(userCharacter.getId());
                newUserCharacter.setUser(newUserData);
                newUserCharacterList.add(newUserCharacter);
            }
            userCharacterRepository.saveAll(newUserCharacterList);
        }

        // UserGhost : worthless

        // UserMapList
        if (userAll.getUserMapList() != null) {
            List<UserMap> userMapList = userAll.getUserMapList();
            List<UserMap> newUserMapList = new ArrayList<>();
            for (UserMap newUserMap : userMapList) {
                int mapId = newUserMap.getMapId();

                Optional<UserMap> mapOptional = userMapRepository.findByUserAndMapId(newUserData, mapId);
                UserMap userMap = mapOptional.orElseGet(() -> new UserMap(newUserData));

                newUserMap.setId(userMap.getId());
                newUserMap.setUser(newUserData);
                newUserMapList.add(newUserMap);
            }
            userMapRepository.saveAll(newUserMapList);
        }

        // UserLoginBonusList
        if (userAll.getUserLoginBonusList() != null) {
            List<UserLoginBonus> userLoginBonusList = userAll.getUserLoginBonusList();
            List<UserLoginBonus> newUserLoginBonusList = new ArrayList<>();
            for (UserLoginBonus newUserLoginBonus : userLoginBonusList) {
                int bonusId = newUserLoginBonus.getBonusId();

                Optional<UserLoginBonus> loginBonusOptional = userLoginBonusRepository.findByUserAndBonusId(newUserData, bonusId);
                UserLoginBonus userLoginBonus = loginBonusOptional.orElseGet(() -> new UserLoginBonus(newUserData));

                newUserLoginBonus.setId(userLoginBonus.getId());
                newUserLoginBonus.setUser(newUserData);
                newUserLoginBonusList.add(newUserLoginBonus);
            }
            userLoginBonusRepository.saveAll(newUserLoginBonusList);
        }

        // UserRatingList
        if (userAll.getUserRatingList() != null) {
            UserRating userRating = userAll.getUserRatingList().get(0);

            //Udemae
            UserUdemae newUserUdemae = userRating.getUdemae();

            Optional<UserUdemae> udemaeOptional = userUdemaeRepository.findByUser(newUserData);
            UserUdemae userUdemae = udemaeOptional.orElseGet(() -> new UserUdemae(newUserData));

            newUserUdemae.setId(userUdemae.getId());
            newUserUdemae.setUser(newUserData);

            userUdemaeRepository.saveAndFlush(newUserUdemae);

            List<UserRate> userRateList = userRating.getRatingList();
            List<UserRate> newUserRateList = new ArrayList<>();

            // UserRate
            for (UserRate newUserRate : userRateList) {
                int musicId = newUserRate.getMusicId();
                int musicLevel = newUserRate.getLevel();

                Optional<UserRate> rateOptional = userRateRepository.findByUserAndMusicIdAndLevel(newUserData, musicId, musicLevel);
                UserRate userRate = rateOptional.orElseGet(() -> new UserRate(newUserData));

                newUserRate.setId(userRate.getId());
                newUserRate.setUser(newUserData);
                newUserRateList.add(newUserRate);

            }
            userRateRepository.saveAll(newUserRateList);
        }

        // UserItemList
        if (userAll.getUserItemList() != null) {
            List<UserItem> userItemList = userAll.getUserItemList();
            List<UserItem> newUserItemList = new ArrayList<>();

            for (UserItem newUserItem : userItemList) {
                int itemId = newUserItem.getItemId();
                int itemKind = newUserItem.getItemKind();

                Optional<UserItem> itemOptional = userItemRepository.findByUserAndItemKindAndItemId(newUserData, itemKind, itemId);
                UserItem userItem = itemOptional.orElseGet(() -> new UserItem(newUserData));

                newUserItem.setId(userItem.getId());
                newUserItem.setUser(newUserData);
                newUserItemList.add(newUserItem);

            }
            userItemRepository.saveAll(newUserItemList);
        }

        // UserMusicDetailList
        if (userAll.getUserMusicDetailList() != null) {
            List<UserMusicDetail> userMusicDetailList = userAll.getUserMusicDetailList();
            List<UserMusicDetail> newUserMusicDetailList = new ArrayList<>();

            for (UserMusicDetail newUserMusicDetail : userMusicDetailList) {
                int musicId = newUserMusicDetail.getMusicId();
                int level = newUserMusicDetail.getLevel();

                Optional<UserMusicDetail> musicDetailOptional = userMusicDetailRepository.findByUserAndMusicIdAndLevel(newUserData, musicId, level);
                UserMusicDetail userMusicDetail = musicDetailOptional.orElseGet(() -> new UserMusicDetail(newUserData));

                newUserMusicDetail.setId(userMusicDetail.getId());
                newUserMusicDetail.setUser(newUserData);
                newUserMusicDetailList.add(newUserMusicDetail);
            }
            userMusicDetailRepository.saveAll(newUserMusicDetailList);
        }

        // UserFavoriteList
        if (userAll.getUserFavoriteList() != null) {
            List<UserFavorite> userFavoriteList = userAll.getUserFavoriteList();
            List<UserFavorite> newUserFavoriteList = new ArrayList<>();
            for (UserFavorite newUserFavorite : userFavoriteList) {
                int itemKind = newUserFavorite.getItemKind();

                Optional<UserFavorite> favoriteOptional = userFavoriteRepository.findByUserAndItemKind(newUserData, itemKind);
                UserFavorite userFavorite = favoriteOptional.orElseGet(() -> new UserFavorite());

                newUserFavorite.setId(userFavorite.getId());
                newUserFavorite.setUser(newUserData);
                newUserFavoriteList.add(newUserFavorite);
            }
            userFavoriteRepository.saveAll(newUserFavoriteList);
        }

        // UserActivityList
        if (userAll.getUserActivityList() != null) {
            UserActivity userActivity = userAll.getUserActivityList().get(0);
            List<UserAct> newUserActList = new ArrayList<>();

            List<List<UserAct>> activityList = new ArrayList<>();
            activityList.add(userActivity.getMusicList());
            activityList.add(userActivity.getPlayList());

            for (List<UserAct> actList : activityList) {
                for (UserAct newUserAct : actList) {
                    int kind = newUserAct.getKind();
                    int id = newUserAct.getActivityId();
    
                    if (kind != 0 && id != 0) {
                        Optional<UserAct> activityOptional = userActRepository.findByUserAndKindAndActivityId(newUserData, kind, id);
                        UserAct userAct = activityOptional.orElseGet(() -> new UserAct(newUserData));
    
                        newUserAct.setId(userAct.getId());
                        newUserAct.setUser(newUserData);
                        newUserActList.add(newUserAct);
                    }
                }
            }
            newUserActList.sort((a, b) -> Long.compare(b.getSortNumber(), a.getSortNumber()));
            userActRepository.saveAll(newUserActList);
        }

        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertUserAllApi\"}";
    }
}
