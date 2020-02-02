package icu.samnyan.aqua.api.controller.sega.game.chuni.amazon;

import com.fasterxml.jackson.core.type.TypeReference;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.ProfileResp;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.RatingItem;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.RecentResp;
import icu.samnyan.aqua.api.model.resp.sega.chuni.amazon.ScoreResp;
import icu.samnyan.aqua.api.util.ApiMapper;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Level;
import icu.samnyan.aqua.sega.chunithm.model.gamedata.Music;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCourse;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserMusicDetail;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserPlaylog;
import icu.samnyan.aqua.sega.chunithm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/chuni/amazon")
public class ApiAmazonController {

    private final ApiMapper mapper;

    private final UserDataService userDataService;
    private final UserPlaylogService userPlaylogService;
    private final UserMusicDetailService userMusicDetailService;
    private final UserCourseService userCourseService;

    private final GameMusicService gameMusicService;

    @Autowired
    public ApiAmazonController(ApiMapper mapper, UserDataService userDataService, UserPlaylogService userPlaylogService, UserMusicDetailService userMusicDetailService, UserCourseService userCourseService, GameMusicService gameMusicService) {
        this.mapper = mapper;
        this.userDataService = userDataService;
        this.userPlaylogService = userPlaylogService;
        this.userMusicDetailService = userMusicDetailService;
        this.userCourseService = userCourseService;
        this.gameMusicService = gameMusicService;
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
        UserCourse course = userCourseService.getByUser(aimeId)
                .stream()
                .filter(UserCourse::isClear)
                .max(Comparator.comparingInt(UserCourse::getClassId))
                .orElseGet(() -> new UserCourse(0));
        resp.setCourseClass(course.getClassId());
        return resp;
    }

    @GetMapping("recent")
    public List<RecentResp> getRecentPlay(@RequestParam String aimeId) {
        return mapper.convert(userPlaylogService.getRecentPlays(aimeId), new TypeReference<>() {
        });
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

    @GetMapping("score/list")
    public List<ScoreResp> getScoreList(@RequestParam String aimeId) {
        return mapper.convert(userMusicDetailService.getByUser(aimeId), new TypeReference<>() {
        });
    }

    @GetMapping("music")
    public List<Music> getMusicList() {
        return gameMusicService.getAll();
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
