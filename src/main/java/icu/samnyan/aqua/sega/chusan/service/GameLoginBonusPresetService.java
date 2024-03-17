package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.GameLoginBonusPresetsRepository;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameLoginBonusPreset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChusanGameLoginBonusPresetService")
public class GameLoginBonusPresetService {
    private final GameLoginBonusPresetsRepository gameLoginBonusPresetsRepository;

    @Autowired
    public GameLoginBonusPresetService(GameLoginBonusPresetsRepository gameLoginBonusPresetsRepository){
        this.gameLoginBonusPresetsRepository = gameLoginBonusPresetsRepository;
    }

    public List<GameLoginBonusPreset> getGameLoginBonusPresets(int version){
        return this.gameLoginBonusPresetsRepository.findLoginBonusPresets(version, 1);
    }
}
