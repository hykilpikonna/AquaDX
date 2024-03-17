package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3GameGachaCardRepo;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameGachaCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanGameGachaCardService")
public class GameGachaCardService {

    private final Chu3GameGachaCardRepo gameGachaCardRepository;

    @Autowired
    public GameGachaCardService(Chu3GameGachaCardRepo gameGachaCardRepository) {
        this.gameGachaCardRepository = gameGachaCardRepository;
    }

    @Cacheable("gachaCard")
    public List<GameGachaCard> getAll() {
        return gameGachaCardRepository.findAll();
    }

    public List<GameGachaCard> getByGachaId(int gachaId) {
        return gameGachaCardRepository.findAllByGachaId(gachaId);
    }

    public List<GameGachaCard> getRandomCards(int gachaId, int times) {
        List<GameGachaCard> gachaCards = gameGachaCardRepository.findAllByGachaId(gachaId);
        List<GameGachaCard> randomCards = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < Math.min(times, gachaCards.size()); i++) {
            randomCards.add(gachaCards.remove(rand.nextInt(gachaCards.size())));
        }

        return randomCards;
    }
}
