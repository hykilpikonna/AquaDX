package icu.samnyan.aqua.api.controller.sega.game.ongeki;

import com.fasterxml.jackson.core.type.TypeReference;
import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.api.model.resp.sega.ongeki.ProfileResp;
import icu.samnyan.aqua.api.model.resp.sega.ongeki.external.ExternalUserData;
import icu.samnyan.aqua.api.model.resp.sega.ongeki.external.OngekiDataExport;
import icu.samnyan.aqua.api.model.resp.sega.ongeki.external.OngekiDataImport;
import icu.samnyan.aqua.api.util.ApiMapper;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.ongeki.dao.gamedata.GameCardRepository;
import icu.samnyan.aqua.sega.ongeki.dao.userdata.*;
import icu.samnyan.aqua.sega.ongeki.model.gamedata.GameCard;
import icu.samnyan.aqua.sega.ongeki.model.userdata.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/ongeki")
public class ApiOngekiPlayerDataController {

    private final ApiMapper mapper;

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

    private final CardService cardService;

    private final UserActivityRepository userActivityRepository;
    private final UserCardRepository userCardRepository;
    private final UserChapterRepository userChapterRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserDataRepository userDataRepository;
    private final UserDeckRepository userDeckRepository;
    private final UserEventPointRepository userEventPointRepository;
    private final UserItemRepository userItemRepository;
    private final UserLoginBonusRepository userLoginBonusRepository;
    private final UserMissionPointRepository userMissionPointRepository;
    private final UserMusicDetailRepository userMusicDetailRepository;
    private final UserMusicItemRepository userMusicItemRepository;
    private final UserOptionRepository userOptionRepository;
    private final UserPlaylogRepository userPlaylogRepository;
    private final UserStoryRepository userStoryRepository;
    private final UserTrainingRoomRepository userTrainingRoomRepository;
    private final UserGeneralDataRepository userGeneralDataRepository;
    private final UserTradeItemRepository userTradeItemRepository;
    private final UserEventMusicRepository userEventMusicRepository;
    private final UserTechEventRepository userTechEventRepository;
    private final UserKopRepository userKopRepository;

    private final UserMemoryChapterRepository userMemoryChapterRepository;

    private final UserScenarioRepository userScenarioRepository;

    private final UserBossRepository userBossRepository;

    private final UserTechCountRepository userTechCountRepository;

    private final GameCardRepository gameCardRepository;

    public ApiOngekiPlayerDataController(ApiMapper mapper, CardService cardService, UserActivityRepository userActivityRepository, UserCardRepository userCardRepository, UserChapterRepository userChapterRepository, UserCharacterRepository userCharacterRepository, UserDataRepository userDataRepository, UserDeckRepository userDeckRepository, UserEventPointRepository userEventPointRepository, UserItemRepository userItemRepository, UserLoginBonusRepository userLoginBonusRepository, UserMissionPointRepository userMissionPointRepository, UserMusicDetailRepository userMusicDetailRepository, UserMusicItemRepository userMusicItemRepository, UserOptionRepository userOptionRepository, UserPlaylogRepository userPlaylogRepository, UserStoryRepository userStoryRepository, UserTrainingRoomRepository userTrainingRoomRepository, UserGeneralDataRepository userGeneralDataRepository, GameCardRepository gameCardRepository, UserTradeItemRepository userTradeItemRepository, UserEventMusicRepository userEventMusicRepository, UserTechEventRepository userTechEventRepository, UserKopRepository userKopRepository, UserMemoryChapterRepository userMemoryChapterRepository, UserScenarioRepository userScenarioRepository, UserBossRepository userBossRepository, UserTechCountRepository userTechCountRepository) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userActivityRepository = userActivityRepository;
        this.userCardRepository = userCardRepository;
        this.userChapterRepository = userChapterRepository;
        this.userCharacterRepository = userCharacterRepository;
        this.userDataRepository = userDataRepository;
        this.userDeckRepository = userDeckRepository;
        this.userEventPointRepository = userEventPointRepository;
        this.userItemRepository = userItemRepository;
        this.userLoginBonusRepository = userLoginBonusRepository;
        this.userMissionPointRepository = userMissionPointRepository;
        this.userMusicDetailRepository = userMusicDetailRepository;
        this.userMusicItemRepository = userMusicItemRepository;
        this.userOptionRepository = userOptionRepository;
        this.userPlaylogRepository = userPlaylogRepository;
        this.userStoryRepository = userStoryRepository;
        this.userTrainingRoomRepository = userTrainingRoomRepository;
        this.userGeneralDataRepository = userGeneralDataRepository;
        this.gameCardRepository = gameCardRepository;
        this.userTradeItemRepository = userTradeItemRepository;
        this.userEventMusicRepository = userEventMusicRepository;
        this.userTechEventRepository = userTechEventRepository;
        this.userKopRepository = userKopRepository;
        this.userMemoryChapterRepository = userMemoryChapterRepository;
        this.userScenarioRepository = userScenarioRepository;
        this.userBossRepository = userBossRepository;
        this.userTechCountRepository = userTechCountRepository;
    }

    @GetMapping("profile")
    public ProfileResp getProfile(@RequestParam long aimeId) {
        return mapper.convert(userDataRepository.findByCard_ExtId(aimeId).orElseThrow(), new TypeReference<>() {
        });
    }

    @PostMapping("profile/userName")
    public UserData updateName(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setUserName((String) request.get("userName"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/plate")
    public UserData updatePlate(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setNameplateId((Integer) request.get("nameplateId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/trophy")
    public UserData updateTrophy(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setTrophyId((Integer) request.get("trophyId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/card")
    public UserData updateCard(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setCardId((Integer) request.get("cardId"));
        return userDataRepository.save(profile);
    }

    @GetMapping("card")
    public ReducedPageResponse<UserCard> getCard(@RequestParam long aimeId,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCard> cards = userCardRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page, size, Sort.Direction.DESC, "id"));
        return new ReducedPageResponse<>(cards.getContent(), cards.getPageable().getPageNumber(), cards.getTotalPages(), cards.getTotalElements());
    }

    /**
     * Force insert a card. This will use to create a new card or update a currently existed card star level.
     *
     * @param request Map of aimeId and cardId
     * @return result UserCard or error message
     */
    @PostMapping("card")
    public ResponseEntity<Object> insertCard(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        Integer cardId = (Integer) request.get("cardId");
        Optional<UserCard> userCardOptional = userCardRepository.findByUserAndCardId(profile, cardId);
        if (userCardOptional.isPresent()) {
            UserCard card = userCardOptional.get();
            if (card.getDigitalStock() < 5) {
                card.setDigitalStock(card.getDigitalStock() + 1);
                card.setMaxLevel(card.getMaxLevel() + 5);
                return ResponseEntity.ok(userCardRepository.save(card));
            } else {
                // If digital stock is larger than 5, check if this card is N card.
                Optional<GameCard> gameCard = gameCardRepository.findById((long) card.getCardId());
                if (gameCard.isPresent()) {
                    if (gameCard.get().getRarity().equals("N")) {
                        card.setDigitalStock(card.getDigitalStock() + 1);
                        card.setMaxLevel(card.getMaxLevel() + 5);
                        return ResponseEntity.ok(userCardRepository.save(card));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("This card has reached max limit."));
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new MessageResponse("Card info not found on server, not allow to edit with api, please make edit to database directly."));
                }
            }
        }
        GameCard card = gameCardRepository.findById(cardId.longValue()).orElseThrow();
        return ResponseEntity.ok(
                userCardRepository.save(
                        new UserCard(
                                profile,
                                cardId,
                                card.getSkillId(),
                                LocalDateTime.now().format(df))
                ));
    }

    @PostMapping("card/{cardId}/kaika")
    public ResponseEntity<Object> kaikaCard(@RequestParam long aimeId, @PathVariable Integer cardId) {
        Optional<UserCard> userCardOptional = userCardRepository.findByUser_Card_ExtIdAndCardId(aimeId, cardId);
        if (userCardOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Card not found."));
        } else {
            UserCard card = userCardOptional.get();
            if (!card.getKaikaDate().equals("0000-00-00 00:00:00.0")) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("No, you have done this before."));
            } else {
                card.setKaikaDate(LocalDateTime.now().format(df));
                card.setMaxLevel(card.getMaxLevel() + 40);
                card.setPrintCount(card.getPrintCount() + 1);
                return ResponseEntity.ok(userCardRepository.save(card));
            }
        }
    }

    @PostMapping("card/{cardId}/choKaika")
    public ResponseEntity<Object> choKaikaCard(@RequestParam long aimeId, @PathVariable Integer cardId) {
        Optional<UserCard> userCardOptional = userCardRepository.findByUser_Card_ExtIdAndCardId(aimeId, cardId);
        if (userCardOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Card not found."));
        } else {
            UserCard card = userCardOptional.get();
            Optional<GameCard> gameCard = gameCardRepository.findById((long) card.getCardId());
            if (gameCard.isPresent()) {
                if (gameCard.get().getRarity().equals("N")) {
                    card.setMaxLevel(100);
                    card.setLevel(100);
                    card.setDigitalStock(11);
                } else {
                    card.setMaxLevel(70);
                    card.setLevel(70);
                    card.setDigitalStock(5);
                }
            } else {
                card.setMaxLevel(70);
                card.setLevel(70);
                card.setDigitalStock(5);
            }
            if (card.getKaikaDate().equals("0000-00-00 00:00:00.0")) {
                card.setKaikaDate(LocalDateTime.now().format(df));
            }
            card.setChoKaikaDate(LocalDateTime.now().format(df));
            card.setPrintCount(card.getPrintCount() + 1);
            return ResponseEntity.ok(userCardRepository.save(card));
        }
    }

    @GetMapping("character")
    public ReducedPageResponse<UserCharacter> getCharacter(@RequestParam long aimeId,
                                                           @RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCharacter> characters = userCharacterRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(characters.getContent(), characters.getPageable().getPageNumber(), characters.getTotalPages(), characters.getTotalElements());
    }

    @GetMapping("activity")
    public List<UserActivity> getActivities(@RequestParam long aimeId) {
        return userActivityRepository.findByUser_Card_ExtId(aimeId);
    }

    @PostMapping("activity")
    public ResponseEntity<Object> updateActivities(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        Integer activityId = (Integer) request.get("id");
        Integer kind = (Integer) request.get("kind");
        Integer sortNumber = (Integer) request.get("sortNumber");
        Integer param1 = (Integer) request.get("param1");
        Integer param2 = (Integer) request.get("param2");
        Integer param3 = (Integer) request.get("param3");
        Integer param4 = (Integer) request.get("param4");

        Optional<UserActivity> userActivityOptional = userActivityRepository.findByUserAndKindAndActivityId(profile, kind, activityId);

        UserActivity userActivity;
        if (userActivityOptional.isPresent()) {
            userActivity = userActivityOptional.get();
        } else {
            userActivity = new UserActivity(profile);
            userActivity.setActivityId(activityId);
            userActivity.setKind(kind);
            userActivity.setSortNumber(sortNumber);
        }
        userActivity.setParam1(param1);
        userActivity.setParam2(param2);
        userActivity.setParam3(param3);
        userActivity.setParam4(param4);
        return ResponseEntity.ok(userActivityRepository.save(userActivity));
    }

    @GetMapping("item")
    public ReducedPageResponse<UserItem> getItem(@RequestParam long aimeId,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserItem> items = userItemRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(items.getContent(), items.getPageable().getPageNumber(), items.getTotalPages(), items.getTotalElements());
    }

    @PostMapping("item")
    public ResponseEntity<Object> updateItem(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        Integer itemKind = (Integer) request.get("itemKind");
        Integer itemId = (Integer) request.get("itemId");
        int stock = 1;
        if (request.containsKey("stock")) {
            stock = (Integer) request.get("stock");
        }

        Optional<UserItem> userItemOptional = userItemRepository.findByUserAndItemKindAndItemId(profile, itemKind, itemId);

        UserItem userItem;
        if (userItemOptional.isPresent()) {
            userItem = userItemOptional.get();
        } else {
            userItem = new UserItem(profile);
            userItem.setItemId(itemId);
            userItem.setItemKind(itemKind);
        }
        userItem.setStock(stock);
        userItem.setValid(true);
        return ResponseEntity.ok(userItemRepository.save(userItem));
    }

    @GetMapping("recent")
    public ReducedPageResponse<UserPlaylog> getRecent(@RequestParam long aimeId,
                                                      @RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserPlaylog> playlogs = userPlaylogRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page, size, Sort.Direction.DESC, "id"));
        return new ReducedPageResponse<>(playlogs.getContent(), playlogs.getPageable().getPageNumber(), playlogs.getTotalPages(), playlogs.getTotalElements());

    }

    @GetMapping("song/{id}")
    public List<UserMusicDetail> getSongDetail(@RequestParam long aimeId, @PathVariable int id) {
        return userMusicDetailRepository.findByUser_Card_ExtIdAndMusicId(aimeId, id);
    }

    @GetMapping("song/{id}/{level}")
    public List<UserPlaylog> getLevelPlaylog(@RequestParam long aimeId, @PathVariable int id, @PathVariable int level) {
        return userPlaylogRepository.findByUser_Card_ExtIdAndMusicIdAndLevel(aimeId, id, level);
    }

    @GetMapping("options")
    public UserOption getOptions(@RequestParam long aimeId) {
        return userOptionRepository.findByUser_Card_ExtId(aimeId).orElseThrow();
    }

    @GetMapping("general")
    public ResponseEntity<Object> getGeneralData(@RequestParam long aimeId, @RequestParam String key) {
        Optional<UserGeneralData> userGeneralDataOptional = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(aimeId, key);
        return userGeneralDataOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User or value not found.")));
    }

    @GetMapping("export")
    public ResponseEntity<Object> exportAllUserData(@RequestParam long aimeId) {
        OngekiDataExport data = new OngekiDataExport();
        try {
            data.setGameId("SDDT");
            data.setUserData(userDataRepository.findByCard_ExtId(aimeId).orElseThrow());
            data.setUserActivityList(userActivityRepository.findByUser_Card_ExtId(aimeId));
            data.setUserCardList(userCardRepository.findByUser_Card_ExtId(aimeId));
            data.setUserChapterList(userChapterRepository.findByUser_Card_ExtId(aimeId));
            data.setUserCharacterList(userCharacterRepository.findByUser_Card_ExtId(aimeId));
            data.setUserDeckList(userDeckRepository.findByUser_Card_ExtId(aimeId));
            data.setUserEventPointList(userEventPointRepository.findByUser_Card_ExtId(aimeId));
            data.setUserGeneralDataList(userGeneralDataRepository.findByUser_Card_ExtId(aimeId));
            data.setUserItemList(userItemRepository.findByUser_Card_ExtId(aimeId));
            data.setUserLoginBonusList(userLoginBonusRepository.findByUser_Card_ExtId(aimeId));
            data.setUserMissionPointList(userMissionPointRepository.findByUser_Card_ExtId(aimeId));
            data.setUserMusicDetailList(userMusicDetailRepository.findByUser_Card_ExtId(aimeId));
            data.setUserMusicItemList(userMusicItemRepository.findByUser_Card_ExtId(aimeId));
            data.setUserOption(userOptionRepository.findByUser_Card_ExtId(aimeId).orElseThrow());
            data.setUserPlaylogList(userPlaylogRepository.findByUser_Card_ExtId(aimeId));
            data.setUserStoryList(userStoryRepository.findByUser_Card_ExtId(aimeId));
            data.setUserTrainingRoomList(userTrainingRoomRepository.findByUser_Card_ExtId(aimeId));
            data.setUserTradeItemList(userTradeItemRepository.findByUser_Card_ExtId(aimeId));
            data.setUserEventMusicList(userEventMusicRepository.findByUser_Card_ExtId(aimeId));
            data.setUserTechEventList(userTechEventRepository.findByUser_Card_ExtId(aimeId));
            data.setUserKopList(userKopRepository.findByUser_Card_ExtId(aimeId));
            data.setUserMemoryChapterList(userMemoryChapterRepository.findByUser_Card_ExtId(aimeId));
            data.setUserScenarioList(userScenarioRepository.findByUser_Card_ExtId(aimeId));
            data.setUserBossList(userBossRepository.findByUser_Card_ExtId(aimeId));
            data.setUserTechCountList(userTechCountRepository.findByUser_Card_ExtId(aimeId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error during data export. Reason: " + e.getMessage()));
        }
        // Set filename
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-disposition", "attachment; filename=ongeki_" + aimeId + "_exported.json");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping("import")
    public ResponseEntity<Object> importAllUserData(@RequestBody OngekiDataImport data) {
        if (!data.getGameId().equals("SDDT")) {
            return ResponseEntity.unprocessableEntity().body(new MessageResponse("Wrong Game Profile, Expected 'SDDT', Get " + data.getGameId()));
        }

        ExternalUserData exUser = data.getUserData();

        Optional<Card> cardOptional = cardService.getCardByAccessCode(exUser.getAccessCode());
        Card card;
        if (cardOptional.isPresent()) {
            card = cardOptional.get();
            Optional<UserData> existUserData = userDataRepository.findByCard(cardOptional.get());
            if (existUserData.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new MessageResponse("This card already has a ongeki profile."));
                // delete all same card data
                userActivityRepository.deleteByUser(existUserData.get());
                userActivityRepository.flush();
                userCardRepository.deleteByUser(existUserData.get());
                userCardRepository.flush();
                userChapterRepository.deleteByUser(existUserData.get());
                userChapterRepository.flush();
                userCharacterRepository.deleteByUser(existUserData.get());
                userCharacterRepository.flush();
                userDeckRepository.deleteByUser(existUserData.get());
                userDeckRepository.flush();
                userEventPointRepository.deleteByUser(existUserData.get());
                userEventPointRepository.flush();
                userGeneralDataRepository.deleteByUser(existUserData.get());
                userGeneralDataRepository.flush();
                userItemRepository.deleteByUser(existUserData.get());
                userItemRepository.flush();
                userLoginBonusRepository.deleteByUser(existUserData.get());
                userLoginBonusRepository.flush();
                userMissionPointRepository.deleteByUser(existUserData.get());
                userMissionPointRepository.flush();
                userMusicDetailRepository.deleteByUser(existUserData.get());
                userMusicDetailRepository.flush();
                userMusicItemRepository.deleteByUser(existUserData.get());
                userMusicItemRepository.flush();
                userOptionRepository.deleteByUser(existUserData.get());
                userOptionRepository.flush();
                userPlaylogRepository.deleteByUser(existUserData.get());
                userPlaylogRepository.flush();
                userStoryRepository.deleteByUser(existUserData.get());
                userStoryRepository.flush();
                userTrainingRoomRepository.deleteByUser(existUserData.get());
                userTrainingRoomRepository.flush();
                userTradeItemRepository.deleteByUser(existUserData.get());
                userTradeItemRepository.flush();
                userEventMusicRepository.deleteByUser(existUserData.get());
                userEventMusicRepository.flush();
                userTechEventRepository.deleteByUser(existUserData.get());
                userTechEventRepository.flush();
                userKopRepository.deleteByUser(existUserData.get());
                userKopRepository.flush();
                userMemoryChapterRepository.deleteByUser(existUserData.get());
                userMemoryChapterRepository.flush();
                userScenarioRepository.deleteByUser(existUserData.get());
                userScenarioRepository.flush();
                userBossRepository.deleteByUser(existUserData.get());
                userBossRepository.flush();
                userTechCountRepository.deleteByUser(existUserData.get());
                userTechCountRepository.flush();

                userDataRepository.deleteByCard(card);
                userDataRepository.flush();
            }
        } else {
            card = cardService.registerByAccessCode(exUser.getAccessCode());
        }

        UserData userData = mapper.convert(exUser, new TypeReference<>() {
        });
        userData.setCard(card);
        userDataRepository.saveAndFlush(userData);

        userActivityRepository.saveAll(data.getUserActivityList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userCardRepository.saveAll(data.getUserCardList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userChapterRepository.saveAll(data.getUserChapterList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userCharacterRepository.saveAll(data.getUserCharacterList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userDeckRepository.saveAll(data.getUserDeckList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userEventPointRepository.saveAll(data.getUserEventPointList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userGeneralDataRepository.saveAll(data.getUserGeneralDataList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userItemRepository.saveAll(data.getUserItemList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userLoginBonusRepository.saveAll(data.getUserLoginBonusList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userMissionPointRepository.saveAll(data.getUserMissionPointList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userMusicDetailRepository.saveAll(data.getUserMusicDetailList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userMusicItemRepository.saveAll(data.getUserMusicItemList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        UserOption userOption = data.getUserOption();
        userOption.setUser(userData);
        userOptionRepository.save(userOption);
        userPlaylogRepository.saveAll(data.getUserPlaylogList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userStoryRepository.saveAll(data.getUserStoryList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userTrainingRoomRepository.saveAll(data.getUserTrainingRoomList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userTradeItemRepository.saveAll(data.getUserTradeItemList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userEventMusicRepository.saveAll(data.getUserEventMusicList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userTechEventRepository.saveAll(data.getUserTechEventList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userKopRepository.saveAll(data.getUserKopList().stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userMemoryChapterRepository.saveAll(Optional.ofNullable(data.getUserMemoryChapterList()).orElse(Collections.emptyList()).stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userScenarioRepository.saveAll(Optional.ofNullable(data.getUserScenarioList()).orElse(Collections.emptyList()).stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userBossRepository.saveAll(Optional.ofNullable(data.getUserBossList()).orElse(Collections.emptyList()).stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        userTechCountRepository.saveAll(Optional.ofNullable(data.getUserTechCountList()).orElse(Collections.emptyList()).stream()
                .peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        return ResponseEntity.ok(new MessageResponse("Import successfully, aimeId: " + card.getExtId()));
    }

}
