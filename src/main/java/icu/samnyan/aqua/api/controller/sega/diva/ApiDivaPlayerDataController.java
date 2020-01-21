package icu.samnyan.aqua.api.controller.sega.diva;

import icu.samnyan.aqua.api.model.ReducedPageResponse;
import icu.samnyan.aqua.sega.diva.dao.userdata.*;
import icu.samnyan.aqua.sega.diva.model.userdata.*;
import icu.samnyan.aqua.sega.diva.service.PlayerProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/diva")
public class ApiDivaPlayerDataController {

    private final PlayerProfileService playerProfileService;

    private final PlayLogRepository playLogRepository;
    private final PlayerPvRecordRepository playerPvRecordRepository;
    private final PlayerPvCustomizeRepository playerPvCustomizeRepository;
    private final PlayerModuleRepository playerModuleRepository;
    private final PlayerCustomizeRepository playerCustomizeRepository;

    public ApiDivaPlayerDataController(PlayerProfileService playerProfileService, PlayLogRepository playLogRepository, PlayerPvRecordRepository playerPvRecordRepository, PlayerPvCustomizeRepository playerPvCustomizeRepository, PlayerModuleRepository playerModuleRepository, PlayerCustomizeRepository playerCustomizeRepository) {
        this.playerProfileService = playerProfileService;
        this.playLogRepository = playLogRepository;
        this.playerPvRecordRepository = playerPvRecordRepository;
        this.playerPvCustomizeRepository = playerPvCustomizeRepository;
        this.playerModuleRepository = playerModuleRepository;
        this.playerCustomizeRepository = playerCustomizeRepository;
    }

    @GetMapping("playerInfo")
    public Optional<PlayerProfile> getPlayerInfo(@RequestParam int pdId) {
        return playerProfileService.findByPdId(pdId);
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

    @PutMapping("rival")
    public PlayerProfile updateRival(@RequestBody Map<String, Object> request) {
        PlayerProfile profile = playerProfileService.findByPdId((Integer) request.get("pdId")).orElseThrow();
        profile.setRivalPdId((Integer) request.get("rivalPdId"));
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
//        profile.setShowClearBorder((Boolean) request.get("showClearBorder"));
        profile.setShowRgoSetting((Boolean) request.get("showRgoSetting"));
        return playerProfileService.save(profile);
    }

    @GetMapping("playLog")
    public ReducedPageResponse<PlayLog> getPlayLogs(@RequestParam int pdId,
                                                    @RequestParam(required = false, defaultValue = "0") int page,
                                                    @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayLog> playLogs = playLogRepository.findByPdId_PdIdOrderByDateTimeDesc(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(playLogs.getContent(), playLogs.getPageable().getPageNumber(), playLogs.getTotalPages(), playLogs.getTotalElements());
    }

    /**
     * PvRecord
     */

    @GetMapping("pvRecord")
    public ReducedPageResponse<PlayerPvRecord> getPvRecords(@RequestParam int pdId,
                                                            @RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayerPvRecord> pvRecords = playerPvRecordRepository.findByPdId_PdIdOrderByPvId(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(pvRecords.getContent(), pvRecords.getPageable().getPageNumber(), pvRecords.getTotalPages(), pvRecords.getTotalElements());
    }

    @GetMapping("pvRecord/{pvId}")
    public Map<String, Object> getPvRecord(@RequestParam int pdId, @PathVariable int pvId) {
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

    @GetMapping("module")
    public ReducedPageResponse<PlayerModule> getModules(@RequestParam int pdId,
                                                        @RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayerModule> modules = playerModuleRepository.findByPdId_PdId(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(modules.getContent(), modules.getPageable().getPageNumber(), modules.getTotalPages(), modules.getTotalElements());
    }

    @GetMapping("customize")
    public ReducedPageResponse<PlayerCustomize> getCustomizes(@RequestParam int pdId,
                                                              @RequestParam(required = false, defaultValue = "0") int page,
                                                              @RequestParam(required = false, defaultValue = "10") int size) {
        Page<PlayerCustomize> customizes = playerCustomizeRepository.findByPdId_PdId(pdId, PageRequest.of(page, size));
        return new ReducedPageResponse<>(customizes.getContent(), customizes.getPageable().getPageNumber(), customizes.getTotalPages(), customizes.getTotalElements());
    }


}
