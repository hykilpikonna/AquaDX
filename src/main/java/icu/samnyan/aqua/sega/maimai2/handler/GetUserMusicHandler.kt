package icu.samnyan.aqua.sega.maimai2.handler

import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import icu.samnyan.aqua.sega.util.jackson.BasicMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("Maimai2GetUserMusicHandler")
class GetUserMusicHandler(
    val mapper: BasicMapper,
    val repos: Mai2Repos
) : BaseHandler {
    override fun handle(request: Map<String, Any>): Any {
        val userId = (request["userId"] as Number).toLong()

        val db = repos.userMusicDetail.findByUser_Card_ExtId(userId)

        logger.info("Response: ${db.size} music records")
        return mapOf(
            "userId" to userId,
            "nextIndex" to 0,
            "userMusicList" to listOf(mapOf("userMusicDetailList" to db))
        )
    }

    companion object {
        val logger = LoggerFactory.getLogger(GetUserMusicHandler::class.java)
    }
}
