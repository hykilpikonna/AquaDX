package icu.samnyan.aqua.api.controller.sega.game.maimai2;

import com.fasterxml.jackson.core.type.TypeReference;
import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.api.model.resp.sega.maimai2.ProfileResp;
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.ExternalUserData;
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.Maimai2DataExport;
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.Maimai2DataImport;
import icu.samnyan.aqua.api.util.ApiMapper;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.*;
import icu.samnyan.aqua.sega.maimai2.model.userdata.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/maimai2")
public class ApiMaimai2PlayerDataController {

    private final ApiMapper mapper;

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

    private final CardService cardService;

    private final UserActRepository userActRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserDataRepository userDataRepository;
    private final UserItemRepository userItemRepository;
    private final UserLoginBonusRepository userLoginBonusRepository;
    private final UserMusicDetailRepository userMusicDetailRepository;
    private final UserOptionRepository userOptionRepository;
    private final UserPlaylogRepository userPlaylogRepository;
    private final UserGeneralDataRepository userGeneralDataRepository;
    private final MapEncountNpcRepository mapEncountNpcRepository;
    private final UserChargeRepository userChargeRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserExtendRepository userExtendRepository;
    private final UserFavoriteRepository userFavoriteRepository;
    private final UserFriendSeasonRankingRepository userFriendSeasonRankingRepository;
    private final UserMapRepository userMapRepository;
    private final UserUdemaeRepository userUdemaeRepository;

    public ApiMaimai2PlayerDataController(ApiMapper mapper, CardService cardService, UserActRepository userActRepository,
    UserCharacterRepository userCharacterRepository, UserDataRepository userDataRepository, UserItemRepository userItemRepository,
    UserLoginBonusRepository userLoginBonusRepository, UserMusicDetailRepository userMusicDetailRepository, UserOptionRepository userOptionRepository,
    UserPlaylogRepository userPlaylogRepository, UserGeneralDataRepository userGeneralDataRepository, MapEncountNpcRepository mapEncountNpcRepository,
    UserChargeRepository userChargeRepository, UserCourseRepository userCourseRepository, UserExtendRepository userExtendRepository,
    UserFavoriteRepository userFavoriteRepository, UserFriendSeasonRankingRepository userFriendSeasonRankingRepository, UserMapRepository userMapRepository,
    UserUdemaeRepository userUdemaeRepository) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userActRepository = userActRepository;
        this.userCharacterRepository = userCharacterRepository;
        this.userDataRepository = userDataRepository;
        this.userItemRepository = userItemRepository;
        this.userLoginBonusRepository = userLoginBonusRepository;
        this.userMusicDetailRepository = userMusicDetailRepository;
        this.userOptionRepository = userOptionRepository;
        this.userPlaylogRepository = userPlaylogRepository;
        this.userGeneralDataRepository = userGeneralDataRepository;
        this.mapEncountNpcRepository = mapEncountNpcRepository;
        this.userChargeRepository = userChargeRepository;
        this.userCourseRepository = userCourseRepository;
        this.userExtendRepository = userExtendRepository;
        this.userFavoriteRepository = userFavoriteRepository;
        this.userFriendSeasonRankingRepository = userFriendSeasonRankingRepository;
        this.userMapRepository = userMapRepository;
        this.userUdemaeRepository = userUdemaeRepository;
    }

    @GetMapping("profile")
    public ProfileResp getProfile(@RequestParam long aimeId) {
        return mapper.convert(userDataRepository.findByCard_ExtId(aimeId).orElseThrow(), new TypeReference<>() {
        });
    }

    @PostMapping("profile/username")
    public UserDetail updateName(@RequestBody Map<String, Object> request) {
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setUserName((String) request.get("userName"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/icon")
    public UserDetail updateIcon(@RequestBody Map<String, Object> request) {
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setIconId((Integer) request.get("iconId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/plate")
    public UserDetail updatePlate(@RequestBody Map<String, Object> request) {
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setPlateId((Integer) request.get("plateId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/frame")
    public UserDetail updateFrame(@RequestBody Map<String, Object> request) {
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setFrameId((Integer) request.get("frameId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/title")
    public UserDetail updateTrophy(@RequestBody Map<String, Object> request) {
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setTitleId((Integer) request.get("titleId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/partner")
    public UserDetail updatePartner(@RequestBody Map<String, Object> request) {
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
        profile.setPartnerId((Integer) request.get("partnerId"));
        return userDataRepository.save(profile);
    }

    @GetMapping("character")
    public ReducedPageResponse<UserCharacter> getCharacter(@RequestParam long aimeId,
                                                           @RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCharacter> characters = userCharacterRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(characters.getContent(), characters.getPageable().getPageNumber(), characters.getTotalPages(), characters.getTotalElements());
    }

    @GetMapping("activity")
    public List<UserAct> getActivities(@RequestParam long aimeId) {
        return userActRepository.findByUser_Card_ExtId(aimeId);
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
        UserDetail profile = userDataRepository.findByCard_ExtId(((Number) request.get("aimeId")).longValue()).orElseThrow();
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
        Maimai2DataExport data = new Maimai2DataExport();
        try {
            data.setGameId("SDEZ");
            data.setUserData(userDataRepository.findByCard_ExtId(aimeId).orElseThrow());
            data.setUserExtend(userExtendRepository.findByUser_Card_ExtId(aimeId).orElseThrow());
            data.setUserOption(userOptionRepository.findByUser_Card_ExtId(aimeId).orElseThrow());
            data.setUserUdemae(userUdemaeRepository.findByUser_Card_ExtId(aimeId).orElseThrow());
            data.setUserCharacterList(userCharacterRepository.findByUser_Card_ExtId(aimeId));
            data.setUserGeneralDataList(userGeneralDataRepository.findByUser_Card_ExtId(aimeId));
            data.setUserItemList(userItemRepository.findByUser_Card_ExtId(aimeId));
            data.setUserLoginBonusList(userLoginBonusRepository.findByUser_Card_ExtId(aimeId));
            data.setUserMusicDetailList(userMusicDetailRepository.findByUser_Card_ExtId(aimeId));
            data.setUserPlaylogList(userPlaylogRepository.findByUser_Card_ExtId(aimeId));
            data.setMapEncountNpcList(mapEncountNpcRepository.findByUser_Card_ExtId(aimeId));
            data.setUserActList(userActRepository.findByUser_Card_ExtId(aimeId));
            data.setUserChargeList(userChargeRepository.findByUser_Card_ExtId(aimeId));
            data.setUserCourseList(userCourseRepository.findByUser_Card_ExtId(aimeId));
            data.setUserFavoriteList(userFavoriteRepository.findByUser_Card_ExtId(aimeId));
            data.setUserFriendSeasonRankingList(userFriendSeasonRankingRepository.findByUser_Card_ExtId(aimeId));
            data.setUserMapList(userMapRepository.findByUser_Card_ExtId(aimeId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error during data export. Reason: " + e.getMessage()));
        }
        // Set filename
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-disposition", "attachment; filename=maimai2_" + aimeId + "_exported.json");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping("import")
    public ResponseEntity<Object> importAllUserData(@RequestBody Maimai2DataImport data) {
        if (!data.getGameId().equals("SDEZ")) {
            return ResponseEntity.unprocessableEntity().body(new MessageResponse("Wrong Game Profile, Expected 'SDEZ', Get " + data.getGameId()));
        }

        ExternalUserData exUser = data.getUserData();

        Optional<Card> cardOptional = cardService.getCardByAccessCode(exUser.getAccessCode());
        Card card;
        if (cardOptional.isPresent()) {
            card = cardOptional.get();
            Optional<UserDetail> existUserData = userDataRepository.findByCard(cardOptional.get());
            if (existUserData.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new MessageResponse("This card already has a maimai2 profile."));
                // delete all same card data
                userFavoriteRepository.deleteByUser(existUserData.get());
                userFavoriteRepository.flush();
                userFriendSeasonRankingRepository.deleteByUser(existUserData.get());
                userFriendSeasonRankingRepository.flush();
                userMapRepository.deleteByUser(existUserData.get());
                userMapRepository.flush();
                userUdemaeRepository.deleteByUser(existUserData.get());
                userUdemaeRepository.flush();
                userGeneralDataRepository.deleteByUser(existUserData.get());
                userGeneralDataRepository.flush();
                userItemRepository.deleteByUser(existUserData.get());
                userItemRepository.flush();
                userLoginBonusRepository.deleteByUser(existUserData.get());
                userLoginBonusRepository.flush();
                userMusicDetailRepository.deleteByUser(existUserData.get());
                userMusicDetailRepository.flush();
                userOptionRepository.deleteByUser(existUserData.get());
                userOptionRepository.flush();
                userPlaylogRepository.deleteByUser(existUserData.get());
                userPlaylogRepository.flush();
                userCharacterRepository.deleteByUser(existUserData.get());
                userCharacterRepository.flush();
                mapEncountNpcRepository.deleteByUser(existUserData.get());
                mapEncountNpcRepository.flush();
                userActRepository.deleteByUser(existUserData.get());
                userActRepository.flush();
                userChargeRepository.deleteByUser(existUserData.get());
                userChargeRepository.flush();
                userCourseRepository.deleteByUser(existUserData.get());
                userCourseRepository.flush();
                userExtendRepository.deleteByUser(existUserData.get());
                userExtendRepository.flush();
                userOptionRepository.deleteByUser(existUserData.get());
                userOptionRepository.flush();

                userDataRepository.deleteByCard(card);
                userDataRepository.flush();
            }
        } else {
            card = cardService.registerByAccessCode(exUser.getAccessCode());
        }

        UserDetail userData = mapper.convert(exUser, new TypeReference<>() {
        });
        userData.setCard(card);
        userDataRepository.saveAndFlush(userData);

        userFavoriteRepository.saveAll(data.getUserFavoriteList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userFriendSeasonRankingRepository.saveAll(data.getUserFriendSeasonRankingList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userMapRepository.saveAll(data.getUserMapList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userGeneralDataRepository.saveAll(data.getUserGeneralDataList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userItemRepository.saveAll(data.getUserItemList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userLoginBonusRepository.saveAll(data.getUserLoginBonusList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userMusicDetailRepository.saveAll(data.getUserMusicDetailList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userPlaylogRepository.saveAll(data.getUserPlaylogList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userCharacterRepository.saveAll(data.getUserCharacterList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        mapEncountNpcRepository.saveAll(data.getMapEncountNpcList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userActRepository.saveAll(data.getUserActList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userChargeRepository.saveAll(data.getUserChargeList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));
        userCourseRepository.saveAll(data.getUserCourseList().stream().peek(x -> x.setUser(userData)).collect(Collectors.toList()));

        UserExtend userExtend = data.getUserExtend();
        userExtend.setUser(userData);
        userExtendRepository.save(userExtend);

        UserOption userOption = data.getUserOption();
        userOption.setUser(userData);
        userOptionRepository.save(userOption);

        UserUdemae userUdemae = data.getUserUdemae();
        userUdemae.setUser(userData);
        userUdemaeRepository.save(userUdemae);

        return ResponseEntity.ok(new MessageResponse("Import successfully, aimeId: " + card.getExtId()));
    }

}
