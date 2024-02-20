package icu.samnyan.aqua.net

import ext.RP
import ext.Str
import ext.async
import ext.minus
import icu.samnyan.aqua.net.components.JWT
import icu.samnyan.aqua.net.db.AquaNetUserRepo
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.general.service.CardService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@API("/api/v2/card")
class CardController(
    val userRepo: AquaNetUserRepo,
    val jwt: JWT,
    val cardService: CardService,
    val cardSummary: CardSummary,
) {
    @API("/summary")
    suspend fun summary(@RP cardId: Str): Any
    {
        val card = cardService.tryLookup(cardId) ?: (404 - "Card not found")

        // Lookup data for each game
        return mapOf(
            "id" to card.extId,
            "accessCode" to card.luid,
            "registerTime" to card.registerTime,
            "accessTime" to card.accessTime,
            "summary" to cardSummary.getSummary(card),
        )
    }
}

@Service
class CardSummary(
    val maimai: icu.samnyan.aqua.sega.maimai.dao.userdata.UserDataRepository,
    val maimai2: icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository,
    val chusan: icu.samnyan.aqua.sega.chusan.dao.userdata.UserDataRepository,
    val chunithm: icu.samnyan.aqua.sega.chunithm.dao.userdata.UserDataRepository,
    val ongeki: icu.samnyan.aqua.sega.ongeki.dao.userdata.UserDataRepository,
    val diva: icu.samnyan.aqua.sega.diva.dao.userdata.PlayerProfileRepository,
) {
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