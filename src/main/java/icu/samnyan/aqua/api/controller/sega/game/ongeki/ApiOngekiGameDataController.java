package icu.samnyan.aqua.api.controller.sega.game.ongeki;

import icu.samnyan.aqua.sega.ongeki.dao.gamedata.*;
import icu.samnyan.aqua.sega.ongeki.model.gamedata.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/ongeki/data")
public class ApiOngekiGameDataController {

    private final GameCardRepository gameCardRepository;
    private final GameCharaRepository gameCharaRepository;
    private final GameEventRepository gameEventRepository;
    private final GameMusicRepository gameMusicRepository;
    private final GameSkillRepository gameSkillRepository;

    public ApiOngekiGameDataController(GameCardRepository gameCardRepository, GameCharaRepository gameCharaRepository, GameEventRepository gameEventRepository, GameMusicRepository gameMusicRepository, GameSkillRepository gameSkillRepository) {
        this.gameCardRepository = gameCardRepository;
        this.gameCharaRepository = gameCharaRepository;
        this.gameEventRepository = gameEventRepository;
        this.gameMusicRepository = gameMusicRepository;
        this.gameSkillRepository = gameSkillRepository;
    }

    @GetMapping("cardList")
    public List<GameCard> getCardList() {
        return gameCardRepository.findAll();
    }

    @GetMapping("charaList")
    public List<GameChara> getCharaList() {
        return gameCharaRepository.findAll();
    }

    @GetMapping("eventList")
    public List<GameEvent> getEventList() {
        return gameEventRepository.findAll();
    }

    @GetMapping("musicList")
    public List<GameMusic> getMusicList() {
        return gameMusicRepository.findAll();
    }

    @GetMapping("skillList")
    public List<GameSkill> getSkillList() {
        return gameSkillRepository.findAll();
    }

    @PostMapping("cardList")
    public List<GameCard> getCardList(@RequestBody List<GameCard> req) {
        return gameCardRepository.saveAll(req);
    }

    @PostMapping("charaList")
    public List<GameChara> getCharaList(@RequestBody List<GameChara> req) {
        return gameCharaRepository.saveAll(req);
    }

    @PostMapping("eventList")
    public List<GameEvent> getEventList(@RequestBody List<GameEvent> req) {
        return gameEventRepository.saveAll(req);
    }

    @PostMapping("musicList")
    public List<GameMusic> getMusicList(@RequestBody List<GameMusic> req) {
        return gameMusicRepository.saveAll(req);
    }

    @PostMapping("skillList")
    public List<GameSkill> getSkillList(@RequestBody List<GameSkill> req) {
        return gameSkillRepository.saveAll(req);
    }

}
