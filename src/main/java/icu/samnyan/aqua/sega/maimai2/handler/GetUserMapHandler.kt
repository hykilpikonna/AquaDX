package icu.samnyan.aqua.sega.maimai2.handler

import ext.toJson
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserMapHandler")
class GetUserMapHandler(val repos: Mai2Repos) : BaseHandler {
    override fun handle(request: Map<String, Any>): String {
        val userId = (request["userId"] as Number).toLong()

        val resultMap = mapOf(
            "userId" to userId,
            "nextIndex" to 0,
            "userMapList" to repos.userMap.findByUser_Card_ExtId(userId)
        )

        val json = resultMap.toJson()
        logger.info("Response: $json")
        return json
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GetUserMapHandler::class.java)
    }
}
