package icu.samnyan.aqua.sega.maimai2.handler

import ext.invoke
import ext.minus
import ext.toJson
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserDataHandler")
class GetUserDataHandler(val repos: Mai2Repos) : BaseHandler {
    override fun handle(request: Map<String, Any>): String {
        val userId = (request["userId"] as Number).toLong()

        val userData = repos.userData.findByCardExtId(userId)() ?: (404 - "User Data Not Found")
        val resultMap = mapOf(
            "userId" to userId,
            "userData" to userData,
            "banState" to userData.banState
        )

        val json = resultMap.toJson()
        logger.info("Response: $json")
        return json
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GetUserDataHandler::class.java)
    }
}
