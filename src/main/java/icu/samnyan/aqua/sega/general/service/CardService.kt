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
     * Find a card by External ID
     * @param extId External ID
     * @return Optional of a Card
     */
    fun getCardByExtId(extId: String): Optional<Card> = cardRepo.findByExtId(extId.toLong())

    /**
     * Find a card by External ID
     *
     * @param extId External ID
     * @return Optional of a Card
     */
    fun getCardByExtId(extId: Long?): Optional<Card> = cardRepo.findByExtId(extId)

    /**
     * Find a card by its access code
     * @param accessCode String represent of an access code
     * @return Optional of a Card
     */
    fun getCardByAccessCode(accessCode: String?): Optional<Card> = cardRepo.findByLuid(accessCode)

    /**
     * Register a new card with access code
     * @param accessCode String represent of an access code
     * @return a new registered Card
     */
    fun registerByAccessCode(accessCode: String?): Card {
        var eid = ThreadLocalRandom.current().nextLong(99999999)
        while (cardRepo.findByExtId(eid).isPresent) {
            eid = ThreadLocalRandom.current().nextLong(99999999)
        }

        return cardRepo.save(Card().apply {
            luid = accessCode
            extId = eid
            registerTime = LocalDateTime.now()
            accessTime = registerTime
        })
    }

    fun randExtID(lower: Long = 0, upper: Long = 99999999): Long {
        var eid = ThreadLocalRandom.current().nextLong(lower, upper)
        while (cardRepo.findByExtId(eid).isPresent) {
            eid = ThreadLocalRandom.current().nextLong(lower, upper)
        }
        return eid
    }
}
