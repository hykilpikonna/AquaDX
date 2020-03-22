package icu.samnyan.aqua.api.controller.sega.game.ongeki;

import com.fasterxml.jackson.core.type.TypeReference;
import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.api.model.resp.sega.ongeki.ProfileResp;
import icu.samnyan.aqua.api.util.ApiMapper;
import icu.samnyan.aqua.sega.ongeki.dao.gamedata.GameCardRepository;
import icu.samnyan.aqua.sega.ongeki.dao.userdata.*;
import icu.samnyan.aqua.sega.ongeki.model.gamedata.GameCard;
import icu.samnyan.aqua.sega.ongeki.model.userdata.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/ongeki")
public class ApiOngekiPlayerDataController {

    private final ApiMapper mapper;

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

    private final UserActivityRepository userActivityRepository;
    private final UserCardRepository userCardRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserDataRepository userDataRepository;
    private final UserDeckRepository userDeckRepository;
    private final UserEventPointRepository userEventPointRepository;
    private final UserItemRepository userItemRepository;
    private final UserMusicDetailRepository userMusicDetailRepository;
    private final UserOptionRepository userOptionRepository;
    private final UserPlaylogRepository userPlaylogRepository;
    private final UserGeneralDataRepository userGeneralDataRepository;

    private final GameCardRepository gameCardRepository;

    public ApiOngekiPlayerDataController(ApiMapper mapper, UserActivityRepository userActivityRepository, UserCardRepository userCardRepository, UserCharacterRepository userCharacterRepository, UserDataRepository userDataRepository, UserDeckRepository userDeckRepository, UserEventPointRepository userEventPointRepository, UserItemRepository userItemRepository, UserMusicDetailRepository userMusicDetailRepository, UserOptionRepository userOptionRepository, UserPlaylogRepository userPlaylogRepository, UserGeneralDataRepository userGeneralDataRepository, GameCardRepository gameCardRepository) {
        this.mapper = mapper;
        this.userActivityRepository = userActivityRepository;
        this.userCardRepository = userCardRepository;
        this.userCharacterRepository = userCharacterRepository;
        this.userDataRepository = userDataRepository;
        this.userDeckRepository = userDeckRepository;
        this.userEventPointRepository = userEventPointRepository;
        this.userItemRepository = userItemRepository;
        this.userMusicDetailRepository = userMusicDetailRepository;
        this.userOptionRepository = userOptionRepository;
        this.userPlaylogRepository = userPlaylogRepository;
        this.userGeneralDataRepository = userGeneralDataRepository;
        this.gameCardRepository = gameCardRepository;
    }

    @GetMapping("profile")
    public ProfileResp getProfile(@RequestParam Integer aimeId) {
        return mapper.convert(userDataRepository.findByCard_ExtId(aimeId).orElseThrow(), new TypeReference<>() {
        });
    }

    @PostMapping("profile/userName")
    public UserData updateName(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        profile.setUserName((String) request.get("userName"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/plate")
    public UserData updatePlate(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        profile.setNameplateId((Integer) request.get("nameplateId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/trophy")
    public UserData updateTrophy(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        profile.setTrophyId((Integer) request.get("trophyId"));
        return userDataRepository.save(profile);
    }

    @PostMapping("profile/card")
    public UserData updateCard(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        profile.setCardId((Integer) request.get("cardId"));
        return userDataRepository.save(profile);
    }

    @GetMapping("card")
    public ReducedPageResponse<UserCard> getCard(@RequestParam Integer aimeId,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCard> cards = userCardRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page,size, Sort.Direction.DESC, "id"));
        return new ReducedPageResponse<>(cards.getContent(), cards.getPageable().getPageNumber(), cards.getTotalPages(), cards.getTotalElements());
    }

    /**
     * Force insert a card. This will use to create a new card or update a currently existed card star level.
     * @param request Map of aimeId and cardId
     * @return result UserCard or error message
     */
    @PostMapping("card")
    public ResponseEntity<Object> insertCard(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        Integer cardId = (Integer) request.get("cardId");
        Optional<UserCard> userCardOptional = userCardRepository.findByUserAndCardId(profile, cardId);
        if(userCardOptional.isPresent()) {
            UserCard card = userCardOptional.get();
            if(card.getDigitalStock() < 5) {
                card.setDigitalStock(card.getDigitalStock() + 1);
                card.setMaxLevel(card.getMaxLevel() + 5);
                return ResponseEntity.ok(userCardRepository.save(card));
            } else {
                // If digital stock is larger than 5, check if this card is N card.
                Optional<GameCard> gameCard = gameCardRepository.findById((long) card.getCardId());
                if(gameCard.isPresent()) {
                    if(gameCard.get().getRarity().equals("N")) {
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
    public ResponseEntity<Object> kaikaCard(@RequestParam Integer aimeId, @PathVariable Integer cardId) {
        Optional<UserCard> userCardOptional = userCardRepository.findByUser_Card_ExtIdAndCardId(aimeId, cardId);
        if(userCardOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Card not found."));
        } else {
            UserCard card = userCardOptional.get();
            if(!card.getKaikaDate().equals("0000-00-00 00:00:00.0")) {
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
    public ResponseEntity<Object> choKaikaCard(@RequestParam Integer aimeId, @PathVariable Integer cardId) {
        Optional<UserCard> userCardOptional = userCardRepository.findByUser_Card_ExtIdAndCardId(aimeId, cardId);
        if(userCardOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Card not found."));
        } else {
            UserCard card = userCardOptional.get();
            if(!card.getChoKaikaDate().equals("0000-00-00 00:00:00.0")) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("No, you have done this before."));
            } else {
                card.setChoKaikaDate(LocalDateTime.now().format(df));
                card.setPrintCount(card.getPrintCount() + 1);
                return ResponseEntity.ok(userCardRepository.save(card));
            }
        }
    }

    @GetMapping("character")
    public ReducedPageResponse<UserCharacter> getCharacter(@RequestParam Integer aimeId,
                                                           @RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCharacter> characters = userCharacterRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page,size));
        return new ReducedPageResponse<>(characters.getContent(), characters.getPageable().getPageNumber(), characters.getTotalPages(), characters.getTotalElements());
    }

    @GetMapping("activity")
    public List<UserActivity> getActivities(@RequestParam Integer aimeId) {
        return userActivityRepository.findByUser_Card_ExtId(aimeId);
    }

    @PostMapping("activity")
    public ResponseEntity<Object> updateActivities(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        Integer activityId = (Integer) request.get("id");
        Integer kind = (Integer) request.get("kind");
        Integer sortNumber = (Integer) request.get("sortNumber");
        Integer param1 = (Integer) request.get("param1");
        Integer param2 = (Integer) request.get("param2");
        Integer param3 = (Integer) request.get("param3");
        Integer param4 = (Integer) request.get("param4");

        Optional<UserActivity> userActivityOptional = userActivityRepository.findByUserAndKindAndActivityId(profile, kind, activityId);

        UserActivity userActivity;
        if(userActivityOptional.isPresent()) {
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
    public ReducedPageResponse<UserItem> getItem(@RequestParam Integer aimeId,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserItem> items = userItemRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page,size));
        return new ReducedPageResponse<>(items.getContent(), items.getPageable().getPageNumber(), items.getTotalPages(), items.getTotalElements());
    }

    @PostMapping("item")
    public ResponseEntity<Object> updateItem(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        Integer itemKind = (Integer) request.get("itemKind");
        Integer itemId = (Integer) request.get("itemId");
        int stock = 1;
        if(request.containsKey("stock")) {
            stock = (Integer) request.get("stock");
        }

        Optional<UserItem> userItemOptional = userItemRepository.findByUserAndItemKindAndItemId(profile, itemKind, itemId);

        UserItem userItem;
        if(userItemOptional.isPresent()) {
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
    public ReducedPageResponse<UserPlaylog> getRecent(@RequestParam Integer aimeId,
                                                      @RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserPlaylog> playlogs = userPlaylogRepository.findByUser_Card_ExtId(aimeId,PageRequest.of(page,size, Sort.Direction.DESC, "id"));
        return new ReducedPageResponse<>(playlogs.getContent(), playlogs.getPageable().getPageNumber(), playlogs.getTotalPages(), playlogs.getTotalElements());

    }

    @GetMapping("song/{id}")
    public List<UserMusicDetail> getSongDetail(@RequestParam Integer aimeId, @PathVariable int id) {
        return userMusicDetailRepository.findByUser_Card_ExtIdAndMusicId(aimeId, id);
    }

    @GetMapping("song/{id}/{level}")
    public List<UserPlaylog> getLevelPlaylog(@RequestParam Integer aimeId, @PathVariable int id, @PathVariable int level) {
        return userPlaylogRepository.findByUser_Card_ExtIdAndMusicIdAndLevel(aimeId, id, level);
    }

    @GetMapping("options")
    public UserOption getOptions(@RequestParam Integer aimeId) {
        return userOptionRepository.findByUser_Card_ExtId(aimeId).orElseThrow();
    }

    @GetMapping("general")
    public ResponseEntity<Object> getGeneralData(@RequestParam Integer aimeId, @RequestParam String key) {
        Optional<UserGeneralData> userGeneralDataOptional = userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(aimeId,key);
        return userGeneralDataOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User or value not found.")));
    }
}
