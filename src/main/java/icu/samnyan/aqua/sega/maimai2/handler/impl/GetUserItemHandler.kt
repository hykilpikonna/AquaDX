package icu.samnyan.aqua.sega.maimai2.handler.impl

import ext.JSON
import icu.samnyan.aqua.net.games.Maimai2
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserItem
import kotlinx.serialization.encodeToString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserItemHandler")
class GetUserItemHandler(
    val repos: Mai2Repos,
    val maimai2: Maimai2
) : BaseHandler {
    val musicUnlock = (5..8).associateWith { kind ->
        JSON.encodeToString(maimai2.musicMapping.mapKeys { UserItem().apply {
            itemKind = kind
            itemId = it.key
            stock = 1
        } }) }

    override fun handle(request: Map<String, Any>): Any {
        val userId = request["userId"] as Long
        val nextIndexVal = request["nextIndex"] as Long
        val maxCount = request["maxCount"] as Int

        val kind = (nextIndexVal / MULT).toInt()
        val nextIndex = (nextIndexVal % MULT).toInt()
        val pageNum = nextIndex / maxCount

        // All Music unlock TODO: Check user settings
        if (kind in 5..8) {
            logger.info("Response: ${maimai2.musicMapping.size} items - Music unlock")
            return mapOf(
                "userId" to userId,
                "nextIndex" to 0,
                "itemKind" to kind,
                "userItemList" to musicUnlock.getValue(kind)
            )
        }

        //

        val dbPage = repos.userItem.findByUser_Card_ExtIdAndItemKind(userId, kind, PageRequest.of(pageNum, maxCount))

        val currentIndex = kind * MULT + maxCount * pageNum + dbPage.numberOfElements
        val result = mapOf(
            "userId" to userId,
            "nextIndex" to if (dbPage.numberOfElements < maxCount) 0 else currentIndex,
            "itemKind" to kind,
            "userItemList" to dbPage.content
        )

        logger.info("Response: ${dbPage.numberOfElements} items")
        return result
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(GetUserItemHandler::class.java)
        const val MULT = 10000000000L
    }
}
