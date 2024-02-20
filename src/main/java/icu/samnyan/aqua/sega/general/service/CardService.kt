package icu.samnyan.aqua.sega.general.service

import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.general.model.Card
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
class CardService(val cardRepo: CardRepository) {
    /**
     * Find a card by External Id
     * @param extId External Id
     * @return Optional of a Card
     */
    fun getCardByExtId(extId: String): Optional<Card> = cardRepo.findByExtId(extId.toLong())

    /**
     * Find a card by External Id
     *
     * @param extId External Id
     * @return Optional of a Card
     */
    fun getCardByExtId(extId: Long?): Optional<Card> = cardRepo.findByExtId(extId)

    /**
     * Find a card by it's access code
     * @param accessCode String represent of a access code
     * @return Optional of a Card
     */
    fun getCardByAccessCode(accessCode: String?): Optional<Card> = cardRepo.findByLuid(accessCode)

    /**
     * Register a new card with access code
     * @param accessCode String represent of a access code
     * @return a new registered Card
     */
    fun registerByAccessCode(accessCode: String?): Card {
        val card = Card()
        card.luid = accessCode
        var extId = ThreadLocalRandom.current().nextLong(99999999)
        while (cardRepo.findByExtId(extId).isPresent) {
            extId = ThreadLocalRandom.current().nextLong(99999999)
        }
        card.extId = extId
        card.registerTime = LocalDateTime.now()
        card.accessTime = LocalDateTime.now()
        return cardRepo.save(card)
    }
}
