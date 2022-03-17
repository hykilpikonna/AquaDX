package icu.samnyan.aqua.api.controller.sega.manage;

import icu.samnyan.aqua.sega.chunithm.model.gamedata.Level;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Music;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserMusicDetail;
import icu.samnyan.aqua.sega.chunithm.service.GameMusicService;
import icu.samnyan.aqua.sega.chunithm.service.UserDataService;
import icu.samnyan.aqua.sega.chunithm.service.UserMusicDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/manage/chuni/v1")
public class ApiChuniV1ManageController {

    private static final Logger logger = LoggerFactory.getLogger(ApiChuniV1ManageController.class);

    private final UserDataService userDataService;

    private final UserMusicDetailService userMusicDetailService;

    private final GameMusicService gameMusicService;

    public ApiChuniV1ManageController(UserDataService userDataService, UserMusicDetailService userMusicDetailService, GameMusicService gameMusicService) {
        this.userDataService = userDataService;
        this.userMusicDetailService = userMusicDetailService;
        this.gameMusicService = gameMusicService;
    }

    /**
     * A request to fill fake score to all chart. only use for testing
     * @param aimeId The internal id of a card
     * @return Run result status
     */
//    @PostMapping("fill")
    public ResponseEntity<Object> fillMockData(@RequestParam String aimeId) {
        UserData profile = userDataService.getUserByExtId(aimeId).orElseThrow();
        List<Music> musicList = gameMusicService.getAll();
        List<UserMusicDetail> detailList = new ArrayList<>();
        musicList.forEach(x -> {
            Collection<Level> levels = x.getLevels().values();
            levels.forEach(l -> {
                Optional<UserMusicDetail> userMusicDetailOptional = userMusicDetailService.getByUserAndMusicIdAndLevel(profile, x.getMusicId(), l.getDiff());
                if (userMusicDetailOptional.isEmpty()) {
                    UserMusicDetail temp = new UserMusicDetail(
                            x.getMusicId(),
                            l.getDiff(),
                            1,
                            980000,
                            0,
                            0,
                            0,
                            5,
                            0,
                            false,
                            false,
                            false,
                            0,
                            0,
                            8,
                            false
                    );
                    temp.setUser(profile);
                    detailList.add(temp);
                }
            });
        });
        userMusicDetailService.saveAll(detailList);
        return ResponseEntity.ok("OK");
    }

}
