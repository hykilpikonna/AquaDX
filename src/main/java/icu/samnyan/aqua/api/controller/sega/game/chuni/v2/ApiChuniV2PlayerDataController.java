package icu.samnyan.aqua.api.controller.sega.game.chuni.v2;

import com.fasterxml.jackson.core.type.TypeReference;
import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.ProfileResp;
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.RatingItem;
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.RecentResp;
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external.ChuniDataExport;
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external.ChuniDataImport;
import icu.samnyan.aqua.api.model.resp.sega.chuni.v2.external.ExternalUserData;
import icu.samnyan.aqua.api.util.ApiMapper;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Level;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Music;
import icu.samnyan.aqua.sega.chusan.model.userdata.*;
import icu.samnyan.aqua.sega.chusan.service.*;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.util.VersionInfo;
import icu.samnyan.aqua.sega.util.VersionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * For all aimeId parameter, should use String
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/v2")
public class ApiChuniV2PlayerDataController {

    private static final Logger logger = LoggerFactory.getLogger(ApiChuniV2PlayerDataController.class);

    private final ApiMapper mapper;

    private final CardService cardService;

    private final UserActivityService userActivityService;
    private final UserCharacterService userCharacterService;
    private final UserChargeService userChargeService;
    private final UserCourseService userCourseService;
    private final UserDataService userDataService;
    private final UserDuelService userDuelService;
    private final UserGameOptionService userGameOptionService;
    private final UserItemService userItemService;
    private final UserMapAreaService userMapAreaService;
    private final UserMusicDetailService userMusicDetailService;
    private final UserPlaylogService userPlaylogService;
    private final UserGeneralDataService userGeneralDataService;
    private final GameMusicService gameMusicService;

    @Autowired
    public ApiChuniV2PlayerDataController(ApiMapper mapper, CardService cardService, UserActivityService userActivityService, UserCharacterService userCharacterService, UserChargeService userChargeService, UserDataService userDataService, UserMapAreaService userMapAreaService, UserPlaylogService userPlaylogService, UserMusicDetailService userMusicDetailService, UserCourseService userCourseService, UserDuelService userDuelService, UserGameOptionService userGameOptionService, UserItemService userItemService, UserGeneralDataService userGeneralDataService, GameMusicService gameMusicService) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userActivityService = userActivityService;
        this.userCharacterService = userCharacterService;
        this.userChargeService = userChargeService;
        this.userDataService = userDataService;
        this.userMapAreaService = userMapAreaService;
        this.userPlaylogService = userPlaylogService;
        this.userMusicDetailService = userMusicDetailService;
        this.userCourseService = userCourseService;
        this.userDuelService = userDuelService;
        this.userGameOptionService = userGameOptionService;
        this.userItemService = userItemService;
        this.userGeneralDataService = userGeneralDataService;
        this.gameMusicService = gameMusicService;
    }

    /*
    // Keep it here for legacy
    @GetMapping("music")
    public List<Music> getMusicList() {
        return gameMusicService.getAll();
    }
    */

    /**
     * Get Basic info
     *
     * @return
     */
    @GetMapping("profile")
    public ProfileResp getProfile(@RequestParam String aimeId) {
        ProfileResp resp = mapper.convert(userDataService.getUserByExtId(aimeId).orElseThrow(), new TypeReference<>() {
        });
        UserCourse course = userCourseService.getByUserId(aimeId)
                .stream()
                .filter(UserCourse::isClear)
                .max(Comparator.comparingInt(UserCourse::getClassId))
                .orElseGet(() -> new UserCourse(0));
        resp.setCourseClass(course.getClassId());
        return resp;
    }

    @PutMapping("profile/username")
    public UserData updateName(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setUserName((String) request.get("userName"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/romversion")
    public UserData updateRomVersion(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setLastRomVersion((String) request.get("romVersion"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/dataversion")
    public UserData updateDataVersion(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setLastDataVersion((String) request.get("dataVersion"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/plate")
    public UserData updatePlate(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setNameplateId((Integer) request.get("nameplateId"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/frame")
    public UserData updateFrame(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setFrameId((Integer) request.get("frameId"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/trophy")
    public UserData updateTrophy(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setTrophyId((Integer) request.get("trophyId"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/mapicon")
    public UserData updateMapIcon(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setMapIconId((Integer) request.get("mapiconId"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/sysvoice")
    public UserData updateSystemVoice(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setVoiceId((Integer) request.get("voiceId"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/avatar")
    public UserData updateAvatar(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        int category = (Integer) request.get("category");
        switch (category) {
            case 1:
            profile.setAvatarWear((Integer) request.get("accId"));
            break;
            case 2:
            profile.setAvatarHead((Integer) request.get("accId"));
            break;
            case 3:
            profile.setAvatarFace((Integer) request.get("accId"));
            break;
            case 4:
            profile.setAvatarSkin((Integer) request.get("accId"));
            break;
            case 5:
            profile.setAvatarItem((Integer) request.get("accId"));
            break;
            case 6:
            profile.setAvatarFront((Integer) request.get("accId"));
            break;
            case 7:
            profile.setAvatarBack((Integer) request.get("accId"));
            break;
        }
        
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/privacy")
    public ResponseEntity<Object> updatePrivacy(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        UserGameOption option = userGameOptionService.getByUser(profile).orElseThrow();
        int privacy = (Integer) request.get("privacy");
        if (privacy != 1 && privacy != 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Wrong data"));
        }
        option.setPrivacy(privacy);
        return ResponseEntity.ok(userDataService.saveUserData(profile));
    }

    @GetMapping("recent")
    public ReducedPageResponse<RecentResp> getRecentPlay(@RequestParam String aimeId,
                                          @RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserPlaylog> playLogs = userPlaylogService.getRecentPlays(aimeId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "userPlayDate")));
        return new ReducedPageResponse<>(mapper.convert(playLogs.getContent(), new TypeReference<>() {
        }), playLogs.getPageable().getPageNumber(), playLogs.getTotalPages(), playLogs.getTotalElements());
    }

    @GetMapping("rating")
    public List<RatingItem> getRating(@RequestParam String aimeId) {

        Map<Integer, Music> musicMap = gameMusicService.getIdMap();
        List<UserMusicDetail> details = userMusicDetailService.getByUserId(aimeId);

        var user = userDataService.getUserByExtId(aimeId).orElseThrow();
        var version = VersionUtil.parseVersion(user.getLastRomVersion());

        List<RatingItem> result = new ArrayList<>();
        for (UserMusicDetail detail : details) {
            Music music = musicMap.get(detail.getMusicId());
            if (music != null) {
                Level level = music.getLevels().get(detail.getLevel());
                if (level != null) {
                    int levelBase = level.getLevel() * 100 + level.getLevelDecimal();
                    int score = detail.getScoreMax();
                    int rating = calculateRating(levelBase, score, version);
                    result.add(new RatingItem(music.getMusicId(), music.getName(), music.getArtistName(), level.getDiff(), score, levelBase, rating));
                }
            }
        }

        return result.stream()
                .filter(detail -> detail.getLevel() != 5)
                .sorted(Comparator.comparingInt(RatingItem::getRating).reversed())
                .limit(30)
                .collect(Collectors.toList());
    }

    @GetMapping("rating/recent")
    public List<RatingItem> getRecentRating(@RequestParam String aimeId) {
        Map<Integer, Music> musicMap = gameMusicService.getIdMap();
        Optional<UserGeneralData> recentOptional = userGeneralDataService.getByUserIdAndKey(aimeId, "recent_rating_list");


        var user = userDataService.getUserByExtId(aimeId).orElseThrow();
        var version = VersionUtil.parseVersion(user.getLastRomVersion());

        List<RatingItem> result = new LinkedList<>();
        if (recentOptional.isPresent()) {
            // Read from recent_rating_list
            String val = recentOptional.get().getPropertyValue();
            if (StringUtils.isNotBlank(val) && val.contains(",")) {
                String[] records = val.split(",");
                for (String record :
                        records) {
                    String[] value = record.split(":");
                    Music music = musicMap.get(Integer.parseInt(value[0]));
                    if (music != null) {
                        Level level = music.getLevels().get(Integer.parseInt(value[1]));
                        if (level != null) {
                            int levelBase = getLevelBase(level.getLevel(), level.getLevelDecimal());
                            int score = Integer.parseInt(value[2]);
                            int rating = calculateRating(levelBase, score, version);
                            result.add(new RatingItem(music.getMusicId(), music.getName(), music.getArtistName(), level.getDiff(), score, levelBase, rating));
                        }
                    }
                }
            }
        } else {
            // Use old method
            List<UserPlaylog> logList = userPlaylogService.getRecent30Plays(aimeId);
            for (UserPlaylog log : logList) {
                Music music = musicMap.get(log.getMusicId());
                if (music != null) {
                    Level level = music.getLevels().get(log.getLevel());
                    if (level != null) {
                        int levelBase = getLevelBase(level.getLevel(), level.getLevelDecimal());
                        int score = log.getScore();
                        int rating = calculateRating(levelBase, score, version);
                        result.add(new RatingItem(music.getMusicId(), music.getName(), music.getArtistName(), level.getDiff(), score, levelBase, rating));
                    }
                }
            }
        }

        return result.stream()
                .filter(detail -> detail.getLevel() != 5)
                .sorted(Comparator.comparingInt(RatingItem::getRating).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @GetMapping("song/{id}")
    public List<UserMusicDetail> getSongDetail(@RequestParam String aimeId, @PathVariable int id) {
        return userMusicDetailService.getByUserIdAndMusicId(aimeId, id);
    }

    @GetMapping("song/{id}/{level}")
    public List<UserPlaylog> getLevelPlaylog(@RequestParam String aimeId, @PathVariable int id, @PathVariable int level) {
        return userPlaylogService.getByUserIdAndMusicIdAndLevel(aimeId, id, level);
    }

    @GetMapping("character")
    public ReducedPageResponse<UserCharacter> getCharacter(@RequestParam String aimeId,
                                        @RequestParam(required = false, defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCharacter> characters = userCharacterService.getByUserId(aimeId, page, size);
        return new ReducedPageResponse<>(characters.getContent(), characters.getPageable().getPageNumber(), characters.getTotalPages(), characters.getTotalElements());
    }

    @PostMapping("character")
    public ResponseEntity<Object> updateCharacter(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        Integer characterId = (Integer) request.get("characterId");
        Optional<UserCharacter> characterOptional = userCharacterService.getByUserAndCharacterId(profile, characterId);
        UserCharacter character;
        if(characterOptional.isPresent()) {
            character = characterOptional.get();
        } else {
            character = new UserCharacter(profile);
            character.setCharacterId(characterId);
        }
        if(request.containsKey("level")) {
            character.setLevel((Integer) request.get("level"));
        }

        return ResponseEntity.ok(userCharacterService.save(character));
    }

    @GetMapping("course")
    public List<UserCourse> getCourse(@RequestParam String aimeId) {
        return userCourseService.getByUserId(aimeId);
    }

    @GetMapping("duel")
    public List<UserDuel> getDuel(@RequestParam String aimeId) {
        return userDuelService.getByUserId(aimeId);
    }

    @GetMapping("item")
    public ReducedPageResponse<UserItem> getItem(@RequestParam String aimeId,
                                  @RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserItem> items = userItemService.getByUserId(aimeId, page, size);
        return new ReducedPageResponse<>(items.getContent(), items.getPageable().getPageNumber(), items.getTotalPages(), items.getTotalElements());
    }

    @GetMapping("item/{itemKind}")
    public List<UserItem> getItemByKind(@RequestParam String aimeId, @PathVariable int itemKind) {
        return userItemService.getByUserAndItemKind(aimeId, itemKind);
    }

    @PostMapping("item")
    public ResponseEntity<Object> updateItem(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        Integer itemId = (Integer) request.get("itemId");
        Integer itemKind = (Integer) request.get("itemKind");
        Optional<UserItem> itemOptional = userItemService.getByUserAndItemIdAndKind(profile, itemId,itemKind);
        UserItem item;
        if(itemOptional.isPresent()) {
            item = itemOptional.get();
        } else {
            item = new UserItem(profile);
            item.setItemId(itemId);
            item.setItemKind(itemKind);
        }
        if(request.containsKey("stock")) {
            item.setStock((Integer) request.get("stock"));
        }
        return ResponseEntity.ok(userItemService.save(item));
    }

    @GetMapping("general")
    public ResponseEntity<Object> getGeneralData(@RequestParam String aimeId, @RequestParam String key) {
        Optional<UserGeneralData> userGeneralDataOptional = userGeneralDataService.getByUserIdAndKey(aimeId,key);
        return userGeneralDataOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User or value not found.")));
    }

    @GetMapping("export")
    public ResponseEntity<Object> exportAllUserData(@RequestParam String aimeId) {
        ChuniDataExport data = new ChuniDataExport();
        try {
            data.setGameId("SDHD");
            data.setUserData(userDataService.getUserByExtId(aimeId).orElseThrow());
            data.setUserActivityList(userActivityService.getByUserId(aimeId));
            data.setUserCharacterList(userCharacterService.getByUserId(aimeId));
            data.setUserChargeList(userChargeService.getByUserId(aimeId));
            data.setUserCourseList(userCourseService.getByUserId(aimeId));
            data.setUserDuelList(userDuelService.getByUserId(aimeId));
            data.setUserGameOption(userGameOptionService.getByUserId(aimeId).orElseThrow());
            data.setUserItemList(userItemService.getByUserId(aimeId));
            data.setUserMapList(userMapAreaService.getByUserId(aimeId));
            data.setUserMusicDetailList(userMusicDetailService.getByUserId(aimeId));
            data.setUserPlaylogList(userPlaylogService.getByUserId(aimeId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error during data export. Reason: " + e.getMessage()));
        }
        // Set filename
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-disposition", "attachment; filename=chusan_" + aimeId + "_exported.json");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping("import")
    public ResponseEntity<Object> importAllUserData(@RequestBody ChuniDataImport data) {
        if(!data.getGameId().equals("SDHD")) {
            return ResponseEntity.unprocessableEntity().body(new MessageResponse("Wrong Game Profile, Expected 'SDHD', Get " + data.getGameId()));
        }

        ExternalUserData exUser = data.getUserData();

        Optional<Card> cardOptional = cardService.getCardByAccessCode(exUser.getAccessCode());
        Card card;
        if (cardOptional.isPresent()) {
            if (userDataService.getUserByCard(cardOptional.get()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("This card already has a chusan profile."));
            } else {
                card = cardOptional.get();
            }
        } else {
            card = cardService.registerByAccessCode(exUser.getAccessCode());
        }

        UserData userData = mapper.convert(exUser, new TypeReference<>() {
        });
        userData.setCard(card);
        userDataService.saveAndFlushUserData(userData);

        List<UserActivity> userActivityList = data.getUserActivityList();
        userActivityList.forEach(x -> x.setUser(userData));
        userActivityService.saveAll(userActivityList);

        List<UserCharacter> userCharacterList = data.getUserCharacterList();
        userCharacterList.forEach(x -> x.setUser(userData));
        userCharacterService.saveAll(userCharacterList);

        List<UserCharge> userChargeList = data.getUserChargeList();
        userCharacterList.forEach(x -> x.setUser(userData));
        userChargeService.saveAll(userChargeList);

        List<UserCourse> userCourseList = data.getUserCourseList();
        userCourseList.forEach(x -> x.setUser(userData));
        userCourseService.saveAll(userCourseList);

        List<UserDuel> userDuelList = data.getUserDuelList();
        userDuelList.forEach(x -> x.setUser(userData));
        userDuelService.saveAll(userDuelList);

        UserGameOption userGameOption = data.getUserGameOption();
        userGameOption.setUser(userData);
        userGameOptionService.save(userGameOption);

        List<UserItem> userItemList = data.getUserItemList();
        userItemList.forEach(x -> x.setUser(userData));
        userItemService.saveAll(userItemList);

        List<UserMapArea> userMapList = data.getUserMapList();
        userMapList.forEach(x -> x.setUser(userData));
        userMapAreaService.saveAll(userMapList);

        List<UserMusicDetail> userMusicDetailList = data.getUserMusicDetailList();
        userMusicDetailList.forEach(x -> x.setUser(userData));
        userMusicDetailService.saveAll(userMusicDetailList);

        List<UserPlaylog> userPlaylogList = data.getUserPlaylogList();
        userPlaylogList.forEach(x -> x.setUser(userData));
        userPlaylogService.saveAll(userPlaylogList);

        return ResponseEntity.ok(new MessageResponse("Import successfully, aimeId: " + card.getExtId()));
    }

    private int getLevelBase(int level, int levelDecimal) {
        return level * 100 + levelDecimal;
    }

    private int calculateRating(int levelBase, int score, VersionInfo version) {
        if (score >= 1009000) return levelBase + 215; //SSS+
        if (score >= 1007500) return levelBase + 200 + (score - 1007500) / 100; //SSS
        if (score >= 1005000) return levelBase + 150 + (score - 1005000) / 50; //SS+
        if (score >= 1000000) return levelBase + 100 + (score - 1000000) / 100; //SS
        if (score >= 975000) return levelBase + (score - 975000) / 250; //S+, S
        if (score >= 925000) return levelBase - 300 + (score - 925000) * 3 / 500; //AA
        if (score >= 900000) return levelBase - 500 + (score - 900000) * 4 / 500; //A
        if (score >= 800000) return ((levelBase - 500) / 2 + (score - 800000) * ((levelBase - 500) / 2) / (100000)); //BBB
        return 0; //C
    }
}
