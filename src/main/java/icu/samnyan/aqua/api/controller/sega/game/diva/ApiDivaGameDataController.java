package icu.samnyan.aqua.api.controller.sega.game.diva;

import icu.samnyan.aqua.sega.diva.dao.gamedata.DivaCustomizeRepository;
import icu.samnyan.aqua.sega.diva.dao.gamedata.DivaModuleRepository;
import icu.samnyan.aqua.sega.diva.model.gamedata.DivaCustomize;
import icu.samnyan.aqua.sega.diva.model.gamedata.DivaModule;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/diva/data")
public class ApiDivaGameDataController {

    private final DivaModuleRepository divaModuleRepository;
    private final DivaCustomizeRepository divaCustomizeRepository;

    public ApiDivaGameDataController(DivaModuleRepository divaModuleRepository, DivaCustomizeRepository divaCustomizeRepository) {
        this.divaModuleRepository = divaModuleRepository;
        this.divaCustomizeRepository = divaCustomizeRepository;
    }

    @GetMapping(value = "musicList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource musicList() throws IOException {
        return new FileSystemResource(Paths.get("data/diva_musiclist.json"));
    }

    @GetMapping(value = "moduleList")
    public List<DivaModule> moduleList() throws IOException {
        return divaModuleRepository.findAll();
    }

    @GetMapping(value = "customizeList")
    public List<DivaCustomize> customizeList() throws IOException {
        return divaCustomizeRepository.findAll();
    }
}
