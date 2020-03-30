package icu.samnyan.aqua.api.controller.sega.game.chuni.amazon;

import icu.samnyan.aqua.sega.chunithm.model.gamedata.Music;
import icu.samnyan.aqua.sega.chunithm.service.GameMusicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/amazon/data")
public class ApiAmazonGameDataController {

    private final GameMusicService gameMusicService;

    public ApiAmazonGameDataController(GameMusicService gameMusicService) {
        this.gameMusicService = gameMusicService;
    }

    @GetMapping("music")
    public List<Music> getMusic() {
        return gameMusicService.getAll();
    }

}
