package icu.samnyan.aqua.api.controller.sega.game.chuni.v2;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameAvatarAccRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameCharacterRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameFrameRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameMapIconRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameMusicRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameTrophyRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameNamePlateRepository;
import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameSystemVoiceRepository;
import icu.samnyan.aqua.sega.chusan.model.gamedata.AvatarAcc;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Character;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Frame;
import icu.samnyan.aqua.sega.chusan.model.gamedata.MapIcon;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Music;
import icu.samnyan.aqua.sega.chusan.model.gamedata.NamePlate;
import icu.samnyan.aqua.sega.chusan.model.gamedata.SystemVoice;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Trophy;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/v2/data")
public class ApiChuniV2GameDataController {

    private final GameMusicRepository gameMusicRepository;
    private final GameCharacterRepository gameCharacterRepository;
    private final GameTrophyRepository gameTrophyRepository;
    private final GameNamePlateRepository gameNamePlateRepository;
    private final GameSystemVoiceRepository gameSystemVoiceRepository;
    private final GameMapIconRepository gameMapIconRepository;
    private final GameFrameRepository gameFrameRepository;
    private final GameAvatarAccRepository gameAvatarAccRepository;

    public ApiChuniV2GameDataController(GameMusicRepository gameMusicRepository, GameCharacterRepository gameCharacterRepository, GameTrophyRepository gameTrophyRepository, GameNamePlateRepository gameNamePlateRepository, GameSystemVoiceRepository gameSystemVoiceRepository, GameMapIconRepository gameMapIconRepository, GameFrameRepository gameFrameRepository, GameAvatarAccRepository gameAvatarAccRepository) {
        this.gameMusicRepository = gameMusicRepository;
        this.gameCharacterRepository = gameCharacterRepository;
        this.gameTrophyRepository = gameTrophyRepository;
        this.gameNamePlateRepository = gameNamePlateRepository;
        this.gameSystemVoiceRepository = gameSystemVoiceRepository;
        this.gameMapIconRepository = gameMapIconRepository;
        this.gameFrameRepository = gameFrameRepository;
        this.gameAvatarAccRepository = gameAvatarAccRepository;
    }

    @GetMapping("music")
    public List<Music> getMusic() {
        return gameMusicRepository.findAll();
    }

    @GetMapping("character")
    public List<Character> getCharacter() {
        return gameCharacterRepository.findAll();
    }

    @GetMapping("trophy")
    public List<Trophy> getTrophy() {
        return gameTrophyRepository.findAll();
    }

    @GetMapping("nameplate")
    public List<NamePlate> getNamePlate() {
        return gameNamePlateRepository.findAll();
    }

    @GetMapping("sysvoice")
    public List<SystemVoice> getSystemVoice() {
        return gameSystemVoiceRepository.findAll();
    }

    @GetMapping("mapicon")
    public List<MapIcon> getMapIcon() {
        return gameMapIconRepository.findAll();
    }

    @GetMapping("frame")
    public List<Frame> getFrame() {
        return gameFrameRepository.findAll();
    }

    @GetMapping("avatar")
    public List<AvatarAcc> getAvatarAcc() {
        return gameAvatarAccRepository.findAll();
    }

}
