package icu.samnyan.aqua.net

import ext.*
import icu.samnyan.aqua.net.components.JWT
import icu.samnyan.aqua.net.db.AquaNetUser
import icu.samnyan.aqua.net.db.AquaNetUserRepo
import icu.samnyan.aqua.net.utils.SUCCESS
import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.general.service.CardService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@API("/api/v2/card")
class CardController(
    val userRepo: AquaNetUserRepo,
    val jwt: JWT,
    val cardService: CardService,
    val cardGameService: CardGameService,
    val cardRepository: CardRepository,
) {
    @API("/summary")
    suspend fun summary(@RP cardId: Str): Any
    {
        val card = cardService.tryLookup(cardId) ?: (404 - "Card not found")

        // Lookup data for each game
        return mapOf(
            "accessCode" to card.luid,
            "registerTime" to card.registerTime,
            "accessTime" to card.accessTime,
            "summary" to cardGameService.getSummary(card),
        )
    }

    /**
     * Bind a card to the user. This action will migrate selected data from the card to the user's ghost card.
     *
     * Non-migrated data will not be lost, but will be inaccessible from the card until the card is unbound.
     *
     * @param token JWT token
     * @param cardId Card ID
     * @param migrate Things to migrate, stored as a comma-separated list of game IDs (e.g. "maimai2,chusan")
     */
    @API("/bind")
    suspend fun bind(@RP token: Str, @RP cardId: Str, @RP migrate: Str) = jwt.auth(token) { u ->
        // Check if the card is already bound
        val card = cardService.tryLookup(cardId) ?: (404 - "Card not found")
        if (card.aquaUser != null) 400 - "Card already bound to another user (@${card.aquaUser?.username})"

        // Bind the card
        card.aquaUser = u
        async { cardRepository.save(card) }

        // Migrate selected data to the new user
        val games = migrate.split(',')
        cardGameService.migrate(card, games)

        SUCCESS
    }
}

@Service
class CardGameService(
    val maimai: icu.samnyan.aqua.sega.maimai.dao.userdata.UserDataRepository,
    val maimai2: icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository,
    val chusan: icu.samnyan.aqua.sega.chusan.dao.userdata.UserDataRepository,
    val chunithm: icu.samnyan.aqua.sega.chunithm.dao.userdata.UserDataRepository,
    val ongeki: icu.samnyan.aqua.sega.ongeki.dao.userdata.UserDataRepository,
    val diva: icu.samnyan.aqua.sega.diva.dao.userdata.PlayerProfileRepository,
) {
    suspend fun migrate(card: Card, games: List<String>) = async {
        // Migrate data from the card to the user's ghost card
        // An easy migration is to change the UserData card field to the user's ghost card
        games.forEach { game ->
            when (game) {
                "maimai" -> maimai.findByCard_ExtId(card.extId).getOrNull()?.let {
                    it.card = card.aquaUser!!.ghostCard
                }
                "maimai2" -> maimai2.findByCard_ExtId(card.extId).getOrNull()?.let {
                    it.card = card.aquaUser!!.ghostCard
                }
                "chusan" -> chusan.findByCard_ExtId(card.extId).getOrNull()?.let {
                    it.card = card.aquaUser!!.ghostCard
                }
                "chunithm" -> chunithm.findByCard_ExtId(card.extId).getOrNull()?.let {
                    it.card = card.aquaUser!!.ghostCard
                }
                "ongeki" -> ongeki.findByCard_ExtId(card.extId).getOrNull()?.let {
                    it.card = card.aquaUser!!.ghostCard
                }
                // TODO: diva
//                "diva" -> diva.findByPdId(card.extId.toInt()).getOrNull()?.let {
//                    it.pdId = card.aquaUser!!.ghostCard
//                }
            }
        }
    }

    suspend fun getSummary(card: Card) = async { mapOf(
        "maimai" to maimai.findByCard_ExtId(card.extId).getOrNull()?.let {
            mapOf(
                "name" to it.userName,
                "rating" to it.playerRating,
                "lastLogin" to it.lastPlayDate,
            )
        },
        "maimai2" to maimai2.findByCard_ExtId(card.extId).getOrNull()?.let {
            mapOf(
                "name" to it.userName,
                "rating" to it.playerRating,
                "lastLogin" to it.lastPlayDate,
            )
        },
        "chusan" to chusan.findByCard_ExtId(card.extId).getOrNull()?.let {
            mapOf(
                "name" to it.userName,
                "rating" to it.playerRating,
                "lastLogin" to it.lastPlayDate,
            )
        },
        "chunithm" to chunithm.findByCard_ExtId(card.extId).getOrNull()?.let {
            mapOf(
                "name" to it.userName,
                "rating" to it.playerRating,
                "lastLogin" to it.lastPlayDate,
            )
        },
        "ongeki" to ongeki.findByCard_ExtId(card.extId).getOrNull()?.let {
            mapOf(
                "name" to it.userName,
                "rating" to it.playerRating,
                "lastLogin" to it.lastPlayDate,
            )
        },
        "diva" to diva.findByPdId(card.extId.toInt()).getOrNull()?.let {
            mapOf(
                "name" to it.playerName,
                "rating" to it.level,
            )
        },
    ) }
}