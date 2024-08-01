package icu.samnyan.aqua.api.controller.sega.game.ongeki;

import icu.samnyan.aqua.sega.ongeki.dao.gamedata.*;
import icu.samnyan.aqua.sega.ongeki.model.gamedata.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/ongeki/data")
@ConditionalOnProperty(prefix = "aquaviewer.api", name = "enabled", havingValue = "true")
@AllArgsConstructor
public class ApiOngekiGameDataController {

    private final GameCardRepository gameCardRepository;
    private final GameCharaRepository gameCharaRepository;
    private final GameEventRepository gameEventRepository;
    private final GameMusicRepository gameMusicRepository;
    private final GameSkillRepository gameSkillRepository;

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
