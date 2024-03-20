package icu.samnyan.aqua.sega.maimai2.handler

import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.util.jackson.BasicMapper
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2UserLoginHandler")
class UserLoginHandler(mapper: BasicMapper) : BaseHandler {
    val resp = mapper.write(mapOf(
        "returnCode" to 1,
        "lastLoginDate" to "2020-01-01 00:00:00.0",
        "loginCount" to 1,
        "consecutiveLoginCount" to 0,
        "loginId" to 1,
        "Bearer" to "meow" // Isn't actually used by the game
    ))

    override fun handle(request: Map<String, Any>) = resp
}
