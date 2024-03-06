package icu.samnyan.aqua.net

import ext.API
import ext.Doc
import ext.RP
import ext.minus
import icu.samnyan.aqua.sega.general.service.CardService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController

@Configuration
@ConfigurationProperties(prefix = "aqua-net.frontier")
class FrontierProps {
    var enabled: Boolean = false
    var ftk: String = ""
}

@RestController
@ConditionalOnProperty("aqua-net.frontier.enabled", havingValue = "true")
@API("/api/v2/frontier")
class Frontier(
    val cardService: CardService,
    val props: FrontierProps
) {
    fun String.checkFtk() {
        if (this != props.ftk) 403 - "Invalid FTK"
    }

    @API("/register-card")
    @Doc("Register a new card by access code", "Success message")
    suspend fun registerCard(@RP ftk: String, @RP accessCode: String): Any {
        ftk.checkFtk()

        return cardService.registerByAccessCode(accessCode)
    }

    @API("/lookup-card")
    @Doc("Lookup a card by access code", "Card information")
    suspend fun lookupCard(@RP ftk: String, @RP accessCode: String): Any {
        ftk.checkFtk()

        return cardService.tryLookup(accessCode) ?: (404 - "Card not found")
    }
}
