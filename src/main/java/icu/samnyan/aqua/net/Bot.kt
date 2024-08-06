package icu.samnyan.aqua.net

import ext.*
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.SUCCESS
import icu.samnyan.aqua.sega.general.service.CardService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Configuration
@ConfigurationProperties(prefix = "aqua-net.bot")
class BotProps {
    var enabled: Boolean = false
    var secret: String = ""
}

@RestController
@ConditionalOnProperty("aqua-net.frontier.enabled", havingValue = "true")
@API("/api/v2/bot")
class BotController(
    val cardService: CardService,
    val us: AquaUserServices,
    val props: BotProps
) {
    fun Str.checkSecret() {
        if (this != props.secret) 403 - "Invalid Secret"
    }

    @PostMapping("/ranking-ban")
    @Doc("Register a new card by access code", "Card information")
    suspend fun rankingBan(@RP secret: Str, @RP username: Str) {
        secret.checkSecret()

        us.cardByName(username) { card ->
            card.rankingBanned = true
            cardService.cardRepo.save(card)

            SUCCESS
        }
    }
}
