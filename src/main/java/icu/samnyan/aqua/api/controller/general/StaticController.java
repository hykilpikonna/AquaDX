package icu.samnyan.aqua.api.controller.general;

import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerScreenShotRepository;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerScreenShot;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/static")
public class StaticController {

    private final PlayerScreenShotRepository playerScreenShotRepository;

    public StaticController(PlayerScreenShotRepository playerScreenShotRepository) {
        this.playerScreenShotRepository = playerScreenShotRepository;
    }

    @GetMapping(value = "screenshot/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getScreenshotFile(@PathVariable String filename) {
        Optional<PlayerScreenShot> ss = playerScreenShotRepository.findByFileName(filename);
        if (ss.isPresent()) {
            return ResponseEntity.ok(new FileSystemResource(Paths.get("data/" + ss.get().getFileName())));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
