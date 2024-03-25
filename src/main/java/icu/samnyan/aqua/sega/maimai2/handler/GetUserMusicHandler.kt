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
//        val nextIndexVal = (request["nextIndex"] as Number).toInt()
//        val maxCount = (request["maxCount"] as Number).toInt()
//
//        val pageNum = nextIndexVal / maxCount
//
//        val dbPage = userMusicDetailRepository.findByUser_Card_ExtId(userId, PageRequest.of(pageNum, maxCount))
//
//        val currentIndex = (maxCount * pageNum + dbPage.numberOfElements).toLong()
//
//        val resultMap = mutableMapOf(
//            "userId" to userId,
//            "nextIndex" to if (dbPage.numberOfElements < maxCount) 0 else currentIndex,
//            "userMusicList" to listOf(mapOf("userMusicDetailList" to dbPage.content))
//        )
//
//        val json = mapper.write(resultMap)
//        logger.info("Response: $json")

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
