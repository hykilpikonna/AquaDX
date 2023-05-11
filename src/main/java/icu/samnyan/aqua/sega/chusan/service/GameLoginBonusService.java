package icu.samnyan.aqua.sega.chusan.service;


import icu.samnyan.aqua.sega.chusan.dao.gamedata.GameLoginBonusRepository;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameLoginBonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ChusanGameLoginBonusService")
public class GameLoginBonusService {
    private final GameLoginBonusRepository gameLoginBonusRepository;

    @Autowired
    public GameLoginBonusService(GameLoginBonusRepository gameLoginBonusRepository){
        this.gameLoginBonusRepository = gameLoginBonusRepository;
    }

    public List<GameLoginBonus> getAllGameLoginBonus(int presetId){
        return this.gameLoginBonusRepository.findGameLoginBonus(1, presetId);
    }

    public Optional<GameLoginBonus> getGameLoginBonusByDay(int presetId, int day){
        return this.gameLoginBonusRepository.findByRequiredDays(1, presetId, day);
    }
}
