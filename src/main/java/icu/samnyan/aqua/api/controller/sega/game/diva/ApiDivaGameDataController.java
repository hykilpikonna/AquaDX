package icu.samnyan.aqua.api.controller.sega.game.diva;

import icu.samnyan.aqua.sega.diva.dao.gamedata.DivaCustomizeRepository;
import icu.samnyan.aqua.sega.diva.dao.gamedata.DivaModuleRepository;
import icu.samnyan.aqua.sega.diva.dao.gamedata.DivaPvRepository;
import icu.samnyan.aqua.sega.diva.model.gamedata.DivaCustomize;
import icu.samnyan.aqua.sega.diva.model.gamedata.DivaModule;
import icu.samnyan.aqua.sega.diva.model.gamedata.Pv;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/diva/data")
@AllArgsConstructor
public class ApiDivaGameDataController {

    private final DivaModuleRepository divaModuleRepository;
    private final DivaCustomizeRepository divaCustomizeRepository;
    private final DivaPvRepository divaPvRepository;

    @GetMapping(value = "musicList")
    public List<Pv> musicList() {
        return divaPvRepository.findAll();
    }

    @GetMapping(value = "moduleList")
    public List<DivaModule> moduleList() {
        return divaModuleRepository.findAll();
    }

    @GetMapping(value = "customizeList")
    public List<DivaCustomize> customizeList() {
        return divaCustomizeRepository.findAll();
    }
}
