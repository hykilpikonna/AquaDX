package icu.samnyan.aqua.api.controller.sega.diva;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/game/diva/data")
public class ApiDivaGameDataController {

    @GetMapping(value = "musicList", produces = MediaType.APPLICATION_JSON_VALUE)
    public byte[] musicList() throws IOException {
        return Files.readAllBytes(Paths.get("data/diva_musiclist.json"));
    }
}
