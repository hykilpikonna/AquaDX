package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.maimai.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.*;
import icu.samnyan.aqua.sega.maimai2.model.request.UpsertUserAll;
import icu.samnyan.aqua.sega.maimai2.model.request.data.UserAll;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserActivity;
import icu.samnyan.aqua.sega.maimai2.model.response.data.UserRating;
import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@AllArgsConstructor
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
    private final Mai2UserActRepo userActRepository;
    private final Mai2UserCharacterRepo userCharacterRepository;
    private final UserMapRepository userMapRepository;
    private final UserLoginBonusRepository userLoginBonusRepository;
    private final UserFavoriteRepository userFavoriteRepository;
    private final UserUdemaeRepository userUdemaeRepository;
    private final UserGeneralDataRepository userGeneralDataRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserFriendSeasonRankingRepository userFriendSeasonRankingRepository;
    private final UserPlaylogRepository userPlaylogRepository;

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        UpsertUserAll upsertUserAll = mapper.convert(request, UpsertUserAll.class);
        long userId = upsertUserAll.getUserId();
        UserAll userAll = upsertUserAll.getUpsertUserAll();

        // If user is guest, just return OK response.
        if ((userId & 281474976710657L) == 281474976710657L) {
            return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UpsertUserAllApi\"}";
        } 

        // UserData
        UserDetail userData;
        UserDetail newUserData;
        if (userAll.getUserData() == null) {
            return null;
        } else {
            newUserData = userAll.getUserData().get(0);
            Optional<UserDetail> userOptional = userDataRepository.findByCardExtId(userId);

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

            // Set isNetMember value to 1, which enables some in-game features.
            newUserData.setNetMember(1);
            userDataRepository.saveAndFlush(newUserData);

            // Check playlog backlog
            var backlog = UploadUserPlaylogHandler.getPlayBacklog();
            if (backlog.containsKey(userId))
                backlog.remove(userId).forEach(it -> {
                    it.getPlaylog().setUser(newUserData);
                    userPlaylogRepository.save(it.getPlaylog());
                });
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

            /* UserRate:
            Let's save recent user rating as same as ongeki implementation.
            Previously saved rating will not compatible with this and will be lost, sorry.
            */

            this.saveGeneralData(userRating.getRatingList(), newUserData, "recent_rating");
            this.saveGeneralData(userRating.getNewRatingList(), newUserData, "recent_rating_new");
            this.saveGeneralData(userRating.getNextRatingList(), newUserData, "recent_rating_next");
            this.saveGeneralData(userRating.getNextNewRatingList(), newUserData, "recent_rating_next_new");
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

        // UserCourseList
        if (userAll.getUserCourseList() != null) {
            List<UserCourse> userCourseList = userAll.getUserCourseList();
            List<UserCourse> newUserCourseList = new ArrayList<>();

            for (UserCourse newUserCourse : userCourseList) {
                int courseId = newUserCourse.getCourseId();

                Optional<UserCourse> userCourseOptional = userCourseRepository.findByUserAndCourseId(newUserData, courseId);
                UserCourse userCourse = userCourseOptional.orElseGet(() -> new UserCourse(newUserData));

                newUserCourse.setId(userCourse.getId());
                newUserCourse.setUser(newUserData);
                newUserCourseList.add(newUserCourse);
            }
            userCourseRepository.saveAll(newUserCourseList);
        }

        // UserFriendSeasonRankingList
        if (userAll.getUserFriendSeasonRankingList() != null) {
            List<UserFriendSeasonRanking> userFriendSeasonRankingList = userAll.getUserFriendSeasonRankingList();
            List<UserFriendSeasonRanking> newUserFriendSeasonRankingList = new ArrayList<>();

            for (UserFriendSeasonRanking newUserFriendSeasonRanking : userFriendSeasonRankingList) {
                int seasonId = newUserFriendSeasonRanking.getSeasonId();

                Optional<UserFriendSeasonRanking> userFriendSeasonRankingOptional = userFriendSeasonRankingRepository.findByUserAndSeasonId(newUserData, seasonId);
                UserFriendSeasonRanking userFriendSeasonRanking = userFriendSeasonRankingOptional.orElseGet(() -> new UserFriendSeasonRanking(newUserData));

                newUserFriendSeasonRanking.setId(userFriendSeasonRanking.getId());
                newUserFriendSeasonRanking.setUser(newUserData);
                newUserFriendSeasonRankingList.add(newUserFriendSeasonRanking);
            }
            userFriendSeasonRankingRepository.saveAll(newUserFriendSeasonRankingList);
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

    private void saveGeneralData(List<UserRate> itemList, UserDetail newUserData, String key) {
        StringBuilder sb = new StringBuilder();
        // Convert to a string
        for (UserRate item :
                itemList) {
            sb.append(item.getMusicId()).append(":").append(item.getLevel()).append(":").append(item.getRomVersion()).append(":").append(item.getAchievement());
            sb.append(",");
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        Optional<UserGeneralData> uOptional = userGeneralDataRepository.findByUserAndPropertyKey(newUserData, key);
        UserGeneralData userGeneralData = uOptional.orElseGet(() -> new UserGeneralData(newUserData, key));
        userGeneralData.setPropertyValue(sb.toString());
        userGeneralDataRepository.save(userGeneralData);
    }
}
