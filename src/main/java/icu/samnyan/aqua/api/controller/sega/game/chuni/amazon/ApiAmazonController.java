package icu.samnyan.aqua.api.controller.sega.game.chuni.amazon;

import com.fasterxml.jackson.core.type.TypeReference;
import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.ProfileResp;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.RatingItem;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.RecentResp;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.external.ChuniDataExport;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.external.ChuniDataImport;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.external.ExternalUserData;
import icu.samnyan.aqua.api.util.ApiMapper;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Level;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Music;
import icu.samnyan.aqua.sega.chunithm.model.userdata.*;
import icu.samnyan.aqua.sega.chunithm.service.*;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
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
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/amazon")
public class ApiAmazonController {

    private static final Logger logger = LoggerFactory.getLogger(ApiAmazonController.class);

    private final ApiMapper mapper;

    private final CardService cardService;

    private final UserActivityService userActivityService;
    private final UserCharacterService userCharacterService;
    private final UserChargeService userChargeService;
    private final UserCourseService userCourseService;
    private final UserDataService userDataService;
    private final UserDataExService userDataExService;
    private final UserDuelService userDuelService;
    private final UserGameOptionService userGameOptionService;
    private final UserGameOptionExService userGameOptionExService;
    private final UserItemService userItemService;
    private final UserMapService userMapService;
    private final UserMusicDetailService userMusicDetailService;
    private final UserPlaylogService userPlaylogService;
    private final UserGeneralDataService userGeneralDataService;

    private final GameMusicService gameMusicService;

    @Autowired
    public ApiAmazonController(ApiMapper mapper, CardService cardService, UserActivityService userActivityService, UserCharacterService userCharacterService, UserChargeService userChargeService, UserDataService userDataService, UserDataExService userDataExService, UserGameOptionExService userGameOptionExService, UserMapService userMapService, UserPlaylogService userPlaylogService, UserMusicDetailService userMusicDetailService, UserCourseService userCourseService, UserDuelService userDuelService, UserGameOptionService userGameOptionService, UserItemService userItemService, UserGeneralDataService userGeneralDataService, GameMusicService gameMusicService) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userActivityService = userActivityService;
        this.userCharacterService = userCharacterService;
        this.userChargeService = userChargeService;
        this.userDataService = userDataService;
        this.userDataExService = userDataExService;
        this.userGameOptionExService = userGameOptionExService;
        this.userMapService = userMapService;
        this.userPlaylogService = userPlaylogService;
        this.userMusicDetailService = userMusicDetailService;
        this.userCourseService = userCourseService;
        this.userDuelService = userDuelService;
        this.userGameOptionService = userGameOptionService;
        this.userItemService = userItemService;
        this.userGeneralDataService = userGeneralDataService;
        this.gameMusicService = gameMusicService;
    }


    @GetMapping("music")
    public List<Music> getMusicList() {
        return gameMusicService.getAll();
    }

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

    @PutMapping("profile/userName")
    public UserData updateName(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setUserName((String) request.get("userName"));
        return userDataService.saveUserData(profile);
    }

    @PutMapping("profile/plate")
    public UserData updatePlate(@RequestBody Map<String, Object> request) {
        UserData profile = userDataService.getUserByExtId((String) request.get("aimeId")).orElseThrow();
        profile.setNameplateId((Integer) request.get("nameplateId"));
        profile.setFrameId((Integer) request.get("frameId"));
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
        List<UserMusicDetail> details = userMusicDetailService.getByUser(aimeId);

        List<RatingItem> result = new ArrayList<>();
        for (UserMusicDetail detail : details) {
            Music music = musicMap.get(detail.getMusicId());
            if (music != null) {
                Level level = music.getLevels().get(detail.getLevel());
                if (level != null) {
                    int levelBase = level.getLevel() * 100 + level.getLevelDecimal();
                    int score = detail.getScoreMax();
                    int rating = calculateRating(levelBase, score);
                    result.add(new RatingItem(music.getMusicId(), music.getName(), music.getArtistName(), level.getDiff(), score, levelBase, rating));
                }
            }
        }

        return result.stream()
                .filter(detail -> detail.getLevel() != 4)
                .sorted(Comparator.comparingInt(RatingItem::getRating).reversed())
                .limit(30)
                .collect(Collectors.toList());
    }

    @GetMapping("rating/recent")
    public List<RatingItem> getRecentRating(@RequestParam String aimeId) {
        Map<Integer, Music> musicMap = gameMusicService.getIdMap();
        List<UserPlaylog> logList = userPlaylogService.getRecent30Plays(aimeId);

        List<RatingItem> result = new ArrayList<>();
        for (UserPlaylog log : logList) {
            Music music = musicMap.get(log.getMusicId());
            if (music != null) {
                Level level = music.getLevels().get(log.getLevel());
                if (level != null) {
                    int levelBase = level.getLevel() * 100 + level.getLevelDecimal();
                    int score = log.getScore();
                    int rating = calculateRating(levelBase, score);

                    result.add(new RatingItem(music.getMusicId(), music.getName(), music.getArtistName(), level.getDiff(), score, levelBase, rating));
                }
            }
        }

        return result.stream()
                .filter(detail -> detail.getLevel() != 4)
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

    @GetMapping("export")
    public ResponseEntity<Object> exportAllUserData(@RequestParam String aimeId) {
        ChuniDataExport data = new ChuniDataExport();
        try {
            data.setGameId("SDBT");
            data.setUserData(userDataService.getUserByExtId(aimeId).orElseThrow());
            data.setUserActivityList(userActivityService.getByUserId(aimeId));
            data.setUserCharacterList(userCharacterService.getByUserId(aimeId));
            data.setUserChargeList(userChargeService.getByUserId(aimeId));
            data.setUserCourseList(userCourseService.getByUserId(aimeId));
            data.setUserDataEx(userDataExService.getByExtId(aimeId).orElseThrow());
            data.setUserDuelList(userDuelService.getByUserId(aimeId));
            data.setUserGameOption(userGameOptionService.getByUserId(aimeId).orElseThrow());
            data.setUserGameOptionEx(userGameOptionExService.getByUserId(aimeId).orElseThrow());
            data.setUserItemList(userItemService.getByUserId(aimeId));
            data.setUserMapList(userMapService.getByUserId(aimeId));
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
        headers.set("content-disposition", "attachment; filename=chuni_" + aimeId + "_exported.json");
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @PostMapping("import")
    public ResponseEntity<Object> importAllUserData(@RequestBody ChuniDataImport data) {
        if(!data.getGameId().equals("SDBT")) {
            return ResponseEntity.unprocessableEntity().body(new MessageResponse("Wrong Game Profile, Expected 'SDBT', Get " + data.getGameId()));
        }

        ExternalUserData exUser = data.getUserData();

        Optional<Card> cardOptional = cardService.getCardByAccessCode(exUser.getAccessCode());
        Card card;
        if (cardOptional.isPresent()) {
            if (userDataService.getUserByCard(cardOptional.get()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("This card already has a chunithm profile."));
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

        UserDataEx userDataEx = data.getUserDataEx();
        userDataEx.setUser(userData);
        userDataExService.save(userDataEx);

        List<UserDuel> userDuelList = data.getUserDuelList();
        userDuelList.forEach(x -> x.setUser(userData));
        userDuelService.saveAll(userDuelList);

        UserGameOption userGameOption = data.getUserGameOption();
        userGameOption.setUser(userData);
        userGameOptionService.save(userGameOption);

        UserGameOptionEx userGameOptionEx = data.getUserGameOptionEx();
        userGameOptionEx.setUser(userData);
        userGameOptionExService.save(userGameOptionEx);

        List<UserItem> userItemList = data.getUserItemList();
        userItemList.forEach(x -> x.setUser(userData));
        userItemService.saveAll(userItemList);

        List<UserMap> userMapList = data.getUserMapList();
        userMapList.forEach(x -> x.setUser(userData));
        userMapService.saveAll(userMapList);

        List<UserMusicDetail> userMusicDetailList = data.getUserMusicDetailList();
        userMusicDetailList.forEach(x -> x.setUser(userData));
        userMusicDetailService.saveAll(userMusicDetailList);

        List<UserPlaylog> userPlaylogList = data.getUserPlaylogList();
        userPlaylogList.forEach(x -> x.setUser(userData));
        userPlaylogService.saveAll(userPlaylogList);

        return ResponseEntity.ok(new MessageResponse("Import successfully, aimeId: " + card.getExtId()));
    }

    @GetMapping("general")
    public ResponseEntity<Object> getGeneralData(@RequestParam String aimeId, @RequestParam String key) {
        Optional<UserGeneralData> userGeneralDataOptional = userGeneralDataService.getByUserIdAndKey(aimeId,key);
        return userGeneralDataOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User or value not found.")));
    }

    private int calculateRating(int levelBase, int score) {
        if (score >= 1007500) return levelBase + 200;
        if (score >= 1005000) return levelBase + 150 + (score - 1005000) * 10 / 500;
        if (score >= 1000000) return levelBase + 100 + (score - 1000000) * 5 / 500;
        if (score >= 975000) return levelBase + (score - 975000) * 2 / 500;
        if (score >= 950000) return levelBase - 150 + (score - 950000) * 3 / 500;
        if (score >= 925000) return levelBase - 300 + (score - 925000) * 3 / 500;
        if (score >= 900000) return levelBase - 500 + (score - 900000) * 4 / 500;
        return 0;
    }
}
