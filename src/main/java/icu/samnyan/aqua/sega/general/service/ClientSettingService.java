package icu.samnyan.aqua.sega.general.service;

import icu.samnyan.aqua.sega.general.dao.GameVersionRepository;
import icu.samnyan.aqua.sega.general.model.GameVersion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static icu.samnyan.aqua.sega.util.AquaConst.DEFAULT_KEYCHIP_ID;

@Service
public class ClientSettingService {

    private final GameVersionRepository gameVersionRepository;

    public ClientSettingService(GameVersionRepository gameVersionRepository) {
        this.gameVersionRepository = gameVersionRepository;
    }

    public void writeSetting(GameVersion setting) {
        gameVersionRepository.save(setting);
    }

    public Optional<GameVersion> getSetting(String serial) {
        if (serial.equals(DEFAULT_KEYCHIP_ID)) {
            return Optional.empty();
        }
        try {
            var vo = gameVersionRepository.findByUuid(serial);
            if (vo.isPresent()) {
                var v = vo.get();
                v.setLastTime(LocalDateTime.now());
                gameVersionRepository.save(v);
                return Optional.of(v);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
