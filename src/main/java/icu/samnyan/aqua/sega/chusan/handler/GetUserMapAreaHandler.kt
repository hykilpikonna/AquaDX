package icu.samnyan.aqua.sega.chusan.handler

import ext.logger
import icu.samnyan.aqua.sega.chusan.model.Chu3UserMapRepo
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.util.jackson.StringMapper
import org.springframework.stereotype.Component

/**
 * Handle GetUserMap request
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("ChusanGetUserMapAreaHandler")
class GetUserMapAreaHandler(
    private val mapper: StringMapper,
    private val userMapRepo: Chu3UserMapRepo
) : BaseHandler {

    @Suppress("UNCHECKED_CAST")
    override fun handle(request: Map<String, Any>): String {
        val userId = request["userId"] as String?
        val maps = (request["mapAreaIdList"] as List<Map<String, String>>)
            .mapNotNull { it["mapAreaId"]?.toIntOrNull() }

        val resultMap = mapOf(
            "userId" to userId,
            "userMapAreaList" to userMapRepo.findAllUserMaps(userId?.toLong() ?: return "{}", maps)
        )

        val json = mapper.write(resultMap)
        logger.info("Response: $json")
        return json
    }

    companion object {
        private val logger = logger()
    }
}
