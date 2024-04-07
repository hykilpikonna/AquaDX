package icu.samnyan.aqua.api.controller.sega.game.diva;

import icu.samnyan.aqua.api.model.MessageResponse;
import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.api.model.resp.sega.diva.PvRankRecord;
import icu.samnyan.aqua.sega.diva.dao.userdata.*;
import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import icu.samnyan.aqua.sega.diva.model.userdata.*;
import icu.samnyan.aqua.sega.diva.service.PlayerProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/diva")
@AllArgsConstructor
public class ApiDivaPlayerDataController {

    private final PlayerProfileService playerProfileService;

    private final GameSessionRepository gameSessionRepository;
    private final PlayLogRepository playLogRepository;
    private final PlayerPvRecordRepository playerPvRecordRepository;
    private final PlayerPvCustomizeRepository playerPvCustomizeRepository;
    private final PlayerModuleRepository playerModuleRepository;
    private final PlayerCustomizeRepository playerCustomizeRepository;
    private final PlayerScreenShotRepository playerScreenShotRepository;

    @PostMapping("forceUnlock")
    public ResponseEntity<MessageResponse> forceUnlock(@RequestParam long pdId) {
        PlayerProfile profile = playerProfileService.findByPdId(pdId).orElseThrow();
        Optional<GameSession> session = gameSessionRepository.findByPdId(profile);
        if(session.isPresent()) {
            gameSessionRepository.delete(session.get());
            return ResponseEntity.ok(new MessageResponse("Session deleted."));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Session doesn't exist."));
        }
    }

    @GetMapping("playerInfo")
    public Optional<PlayerProfile> getPlayerInfo(@RequestParam long pdId) {
        return playerProfileService.findByPdId(pdId);
    }

    @GetMapping("playerInfo/rival")
    public Map<String, String> getRivalInfo(@RequestParam long pdId) {
        var rId = playerProfileService.findByPdId(pdId).orElseThrow().getRivalPdId();
        Map<String, String> result = new HashMap<>();
        if (rId == -1) {
            result.put("rival", "Not Set");
        } else {
            Optional<PlayerProfile> profile = playerProfileService.findByPdId(rId);
            if (profile.isPresent()) {
                result.put("rival", profile.get().getPlayerName());
            } else {
                result.put("rival", "Player Not Found");
            }
        }
        return result;
    }

    @PutMapping("playerInfo/rival")
    public PlayerProfile updateRivalWithId(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setRivalPdId((Integer) request.get("rivalId"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/rival/byRecord")
    public PlayerProfile updateRivalWithRecord(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        PlayerPvRecord record = playerPvRecordRepository.findById(((Integer) request.get("recordId")).longValue()).orElseThrow();
        profile.setRivalPdId(record.getPdId().getPdId());
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/playerName")
    public PlayerProfile updateName(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setPlayerName((String) request.get("playerName"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/title")
    public PlayerProfile updateTitle(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setLevelTitle((String) request.get("title"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/plate")
    public PlayerProfile updatePlate(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setPlateId((Integer) request.get("plateId"));
        profile.setPlateEffectId((Integer) request.get("plateEffectId"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/commonModule")
    public PlayerProfile updateModule(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setCommonModule((String) request.get("commonModule"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/commonCustomize")
    public PlayerProfile updateCustomize(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setCommonCustomizeItems((String) request.get("commonCustomize"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/commonSkin")
    public PlayerProfile updateSkin(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setCommonSkin((Integer) request.get("skinId"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/myList")
    public PlayerProfile updateMyList(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        switch ((Integer) request.get("myListId")) {
            case 0:
                profile.setMyList0((String) request.get("myListData"));
                break;
            case 1:
                profile.setMyList1((String) request.get("myListData"));
                break;
            case 2:
                profile.setMyList2((String) request.get("myListData"));
                break;
        }
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/se")
    public PlayerProfile updateSe(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setButtonSe((Integer) request.get("buttonSe"));
        profile.setChainSlideSe((Integer) request.get("chainSlideSe"));
        profile.setSlideSe((Integer) request.get("slideSe"));
        profile.setSliderTouchSe((Integer) request.get("sliderTouchSe"));
        return playerProfileService.save(profile);
    }

    @PutMapping("playerInfo/display")
    public PlayerProfile updateDisplay(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setShowInterimRanking((Boolean) request.get("showInterimRanking"));
        profile.setShowClearStatus((Boolean) request.get("showClearStatus"));
        profile.setShowGreatBorder((Boolean) request.get("showGreatBorder"));
        profile.setShowExcellentBorder((Boolean) request.get("showExcellentBorder"));
        profile.setShowRivalBorder((Boolean) request.get("showRivalBorder"));
        profile.setShowRgoSetting((Boolean) request.get("showRgoSetting"));
        return playerProfileService.save(profile);
    }

    @GetMapping("playLog")
    public ReducedPageResponse<PlayLog> getPlayLogs(@RequestParam long pdId,
                                                    @RequestParam(required = false, defaultValue = "0") int page,
                                                    @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayLog> playLogs = playLogRepository.findByPdId_PdIdOrderByDateTimeDesc(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(playLogs.getContent(), playLogs.getPageable().getPageNumber(), playLogs.getTotalPages(), playLogs.getTotalElements());
    }

    /**
     * PvRecord
     */

    @GetMapping("pvRecord")
    public ReducedPageResponse<PlayerPvRecord> getPvRecords(@RequestParam long pdId,
                                                            @RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayerPvRecord> pvRecords = playerPvRecordRepository.findByPdId_PdIdOrderByPvId(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(pvRecords.getContent(), pvRecords.getPageable().getPageNumber(), pvRecords.getTotalPages(), pvRecords.getTotalElements());
    }

    @GetMapping("pvRecord/{pvId}")
    public Map<String, Object> getPvRecord(@RequestParam long pdId, @PathVariable int pvId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("records", playerPvRecordRepository.findByPdId_PdIdAndPvId(pdId, pvId));
        playerPvCustomizeRepository.findByPdId_PdIdAndPvId(pdId, pvId).ifPresent(x -> resultMap.put("customize", x));
        return resultMap;
    }

    @PutMapping("pvRecord/{pvId}")
    public PlayerPvCustomize updatePvCustomize(@RequestBody Map<String, Object> request, @PathVariable int pvId) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        PlayerPvCustomize playerPvCustomize = playerPvCustomizeRepository.findByPdIdAndPvId(profile, pvId)
                .orElseGet(() -> new PlayerPvCustomize(profile, pvId));
        playerPvCustomize.setModule((String) request.get("module"));
        playerPvCustomize.setCustomize((String) request.get("customize"));
        playerPvCustomize.setCustomizeFlag((String) request.get("customizeFlag"));
        playerPvCustomize.setSkin((Integer) request.get("skin"));
        playerPvCustomize.setButtonSe((Integer) request.get("buttonSe"));
        playerPvCustomize.setSlideSe((Integer) request.get("slideSe"));
        playerPvCustomize.setChainSlideSe((Integer) request.get("chainSlideSe"));
        playerPvCustomize.setSliderTouchSe((Integer) request.get("sliderTouchSe"));
        return playerPvCustomizeRepository.save(playerPvCustomize);
    }

    @GetMapping("pvRecord/{pvId}/ranking/{difficulty}")
    public ReducedPageResponse<PvRankRecord> getPvRanking(@PathVariable int pvId,
                                                          @PathVariable String difficulty,
                                                          @RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "10") int size) {
        Difficulty diff = null;
        Edition edition = Edition.ORIGINAL;
        switch (difficulty) {
            case "EASY":
                diff = Difficulty.EASY;
                break;
            case "NORMAL":
                diff = Difficulty.NORMAL;
                break;
            case "HARD":
                diff = Difficulty.HARD;
                break;
            case "EXTREME":
                diff = Difficulty.EXTREME;
                break;
            case "EXTRA_EXTREME": {
                diff = Difficulty.EXTREME;
                edition = Edition.EXTRA;
                break;
            }
        }
        if(diff != null) {
            Page<PlayerPvRecord> pvRecords = playerPvRecordRepository.findByPvIdAndEditionAndDifficultyOrderByMaxScoreDesc(pvId, edition,diff, PageRequest.of(page, size));

            List<PvRankRecord> rankList = new LinkedList<>();

            pvRecords.forEach(x ->{
                rankList.add(new PvRankRecord(x.getId(),x.getPdId().getPlayerName(),x.getMaxScore(),x.getMaxAttain()));
            });

            return new ReducedPageResponse<>(rankList, pvRecords.getPageable().getPageNumber(), pvRecords.getTotalPages(), pvRecords.getTotalElements());
        }
        return null;
    }

    @GetMapping("module")
    public ReducedPageResponse<PlayerModule> getModules(@RequestParam long pdId,
                                                        @RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayerModule> modules = playerModuleRepository.findByPdId_PdId(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(modules.getContent(), modules.getPageable().getPageNumber(), modules.getTotalPages(), modules.getTotalElements());
    }

    @GetMapping("customize")
    public ReducedPageResponse<PlayerCustomize> getCustomizes(@RequestParam long pdId,
                                                              @RequestParam(required = false, defaultValue = "0") int page,
                                                              @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayerCustomize> customizes = playerCustomizeRepository.findByPdId_PdId(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(customizes.getContent(), customizes.getPageable().getPageNumber(), customizes.getTotalPages(), customizes.getTotalElements());
    }

    @GetMapping("screenshot")
    public List<PlayerScreenShot> getScreenshotList(@RequestParam long pdId) {
        return playerScreenShotRepository.findByPdId_PdId(pdId);
    }

}
