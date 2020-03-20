package icu.samnyan.aqua.api.controller.sega.game.ongeki;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/ongeki")
public class ApiOngekiPlayerDataController {

    private final ApiMapper mapper;

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

    private final GameCardRepository gameCardRepository;

    public ApiOngekiPlayerDataController(ApiMapper mapper, UserActivityRepository userActivityRepository, UserCardRepository userCardRepository, UserCharacterRepository userCharacterRepository, UserDataRepository userDataRepository, UserDeckRepository userDeckRepository, UserEventPointRepository userEventPointRepository, UserItemRepository userItemRepository, UserMusicDetailRepository userMusicDetailRepository, UserOptionRepository userOptionRepository, UserPlaylogRepository userPlaylogRepository, GameCardRepository gameCardRepository) {
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
        Page<UserCard> cards = userCardRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page,size));
        return new ReducedPageResponse<>(cards.getContent(), cards.getPageable().getPageNumber(), cards.getTotalPages(), cards.getTotalElements());
    }

    @PostMapping("insert")
    public UserCard insertCard(@RequestBody Map<String, Object> request) {
        UserData profile = userDataRepository.findByCard_ExtId((Integer) request.get("aimeId")).orElseThrow();
        Integer cardId = (Integer) request.get("cardId");
        GameCard card = gameCardRepository.findById(cardId.longValue()).orElseThrow();
        return userCardRepository.save(new UserCard(
                profile,
                cardId,
                card.getSkillId()
                ));
    }

    @GetMapping("character")
    public ReducedPageResponse<UserCharacter> getCharacter(@RequestParam Integer aimeId,
                                                           @RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserCharacter> characters = userCharacterRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page,size));
        return new ReducedPageResponse<>(characters.getContent(), characters.getPageable().getPageNumber(), characters.getTotalPages(), characters.getTotalElements());
    }

    @GetMapping("item")
    public ReducedPageResponse<UserItem> getItem(@RequestParam Integer aimeId,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<UserItem> items = userItemRepository.findByUser_Card_ExtId(aimeId, PageRequest.of(page,size));
        return new ReducedPageResponse<>(items.getContent(), items.getPageable().getPageNumber(), items.getTotalPages(), items.getTotalElements());
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
}
