package icu.samnyan.aqua.sega.maimai2.handler

import ext.invoke
import ext.minus
import ext.toJson
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserExtendHandler")
class GetUserExtendHandler(val repos: Mai2Repos) : BaseHandler {
    override fun handle(request: Map<String, Any>): Any {
        val userId = (request["userId"] as Number).toLong()

        val userExtend = repos.userExtend.findSingleByUser_Card_ExtId(userId)() ?: (404 - "User Extend Not Found")
        val resultMap = mapOf(
            "userId" to userId,
            "userExtend" to userExtend
        )

        val json = resultMap.toJson()
        logger.info("Response: $json")
        return json
    }

    companion object {
        val logger = LoggerFactory.getLogger(GetUserExtendHandler::class.java)
    }
}
