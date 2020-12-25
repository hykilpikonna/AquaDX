package icu.samnyan.aqua.sega.general.service;

import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * Find a card by External Id
     * @param extId External Id
     * @return Optional of a Card
     */
    public Optional<Card> getCardByExtId(String extId) {
        return cardRepository.findByExtId(Long.parseLong(extId));
    }

    /**
     * Find a card by External Id
     *
     * @param extId External Id
     * @return Optional of a Card
     */
    public Optional<Card> getCardByExtId(Long extId) {
        return cardRepository.findByExtId(extId);
    }

    /**
     * Find a card by it's access code
     * @param accessCode String represent of a access code
     * @return Optional of a Card
     */
    public Optional<Card> getCardByAccessCode(String accessCode) {
        return cardRepository.findByLuid(accessCode);
    }

    /**
     * Register a new card with access code
     * @param accessCode String represent of a access code
     * @return a new registered Card
     */
    public Card registerByAccessCode(String accessCode) {
        Card card = new Card();
        card.setLuid(accessCode);
        long extId = ThreadLocalRandom.current().nextLong(99999999);
        while (cardRepository.findByExtId(extId).isPresent()) {
            extId = ThreadLocalRandom.current().nextLong(99999999);
        }
        card.setExtId(extId);
        card.setRegisterTime(LocalDateTime.now());
        card.setAccessTime(LocalDateTime.now());
        return cardRepository.save(card);
    }
}
