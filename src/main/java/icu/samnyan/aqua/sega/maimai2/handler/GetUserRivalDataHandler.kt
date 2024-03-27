package icu.samnyan.aqua.sega.maimai2.handler

import ext.invoke
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("Maimai2GetUserRivalDataHandler")
class GetUserRivalDataHandler(val repos: Mai2Repos) : BaseHandler {
    override fun handle(request: Map<String, Any>): Any {
        val userId = (request["userId"] as Number).toLong()
        val rivalId = (request["rivalId"] as Number).toLong()

        return mapOf(
            "userId" to userId,
            "userRivalData" to mapOf(
                "rivalId" to rivalId,
                "rivalName" to (repos.userData.findByCardExtId(rivalId)()?.userName ?: "")
            )
        )
    }
}
