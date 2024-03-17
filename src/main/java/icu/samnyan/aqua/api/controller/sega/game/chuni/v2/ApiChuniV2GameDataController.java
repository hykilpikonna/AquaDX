package icu.samnyan.aqua.api.controller.sega.game.chuni.v2;

import icu.samnyan.aqua.sega.chusan.model.*;
import icu.samnyan.aqua.sega.chusan.model.gamedata.Character;
import icu.samnyan.aqua.sega.chusan.model.gamedata.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/v2/data")
@AllArgsConstructor
public class ApiChuniV2GameDataController {

    private final Chu3GameMusicRepo gameMusicRepository;
    private final Chu3GameCharacterRepo gameCharacterRepository;
    private final Chu3GameTrophyRepo gameTrophyRepository;
    private final Chu3GameNamePlateRepo gameNamePlateRepository;
    private final Chu3GameSystemVoiceRepo gameSystemVoiceRepository;
    private final Chu3GameMapIconRepo gameMapIconRepository;
    private final Chu3GameFrameRepo gameFrameRepository;
    private final Chu3GameAvatarAccRepo gameAvatarAccRepository;

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
