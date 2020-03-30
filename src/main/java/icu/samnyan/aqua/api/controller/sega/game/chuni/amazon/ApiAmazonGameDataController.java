package icu.samnyan.aqua.api.controller.sega.game.chuni.amazon;

import icu.samnyan.aqua.sega.chunithm.dao.gamedata.GameCharacterRepository;
import icu.samnyan.aqua.sega.chunithm.dao.gamedata.GameCharacterSkillRepository;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Character;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.CharacterSkill;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Music;
import icu.samnyan.aqua.sega.chunithm.service.GameMusicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/amazon/data")
public class ApiAmazonGameDataController {

    private final GameMusicService gameMusicService;
    private final GameCharacterRepository gameCharacterRepository;
    private final GameCharacterSkillRepository gameCharacterSkillRepository;

    public ApiAmazonGameDataController(GameMusicService gameMusicService, GameCharacterRepository gameCharacterRepository, GameCharacterSkillRepository gameCharacterSkillRepository) {
        this.gameMusicService = gameMusicService;
        this.gameCharacterRepository = gameCharacterRepository;
        this.gameCharacterSkillRepository = gameCharacterSkillRepository;
    }

    @GetMapping("music")
    public List<Music> getMusic() {
        return gameMusicService.getAll();
    }

    @GetMapping("character")
    public List<Character> getCharacter() {
        return gameCharacterRepository.findAll();
    }

    @GetMapping("skill")
    public List<CharacterSkill> getSkill() {
        return gameCharacterSkillRepository.findAll();
    }

//    @PostMapping("character")
//    public List<Character> importCharacter(@RequestBody List<Character> req) {
//        return gameCharacterRepository.saveAll(req);
//    }
//
//    @PostMapping("skill")
//    public List<CharacterSkill> importSkill(@RequestBody List<CharacterSkill> req) {
//        return gameCharacterSkillRepository.saveAll(req);
//    }

}
