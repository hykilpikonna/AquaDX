package icu.samnyan.aqua.sega.general.service;

import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<Card> getCardByExtId(String extId) {
        return cardRepository.findByExtId(Integer.parseInt(extId));
    }

    public Optional<Card> getCardByExtId(int extId) {
        return cardRepository.findByExtId(extId);
    }
}
