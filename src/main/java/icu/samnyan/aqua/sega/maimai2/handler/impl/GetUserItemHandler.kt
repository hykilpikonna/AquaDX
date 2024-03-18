package icu.samnyan.aqua.sega.maimai2.handler.impl

import icu.samnyan.aqua.net.games.Maimai2
import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserItemHandler")
class GetUserItemHandler(
    val repos: Mai2Repos,
    val maimai2: Maimai2,
    val cardRepo: CardRepository,
) : BaseHandler {
    val musicUnlock = (5..8).associateWith { kind ->
        maimai2.musicMapping.map { mapOf(
            "itemKind" to kind,
            "itemId" to it.key,
            "stock" to 1,
            "isValid" to true,
        ).toMap() } }

    override fun handle(request: Map<String, Any>): Any {
        val userId = (request["userId"] as Number).toLong()
        val nextIndexVal = (request["nextIndex"] as Number).toLong()
        val maxCount = (request["maxCount"] as Number).toInt()

        val kind = (nextIndexVal / MULT).toInt()
        val nextIndex = (nextIndexVal % MULT).toInt()
        val pageNum = nextIndex / maxCount

        // Aqua Net game unlock feature
        cardRepo.findByExtId(userId).getOrNull()?.aquaUser?.gameOptions?.let { opt ->
            // All Music unlock
            if (kind in 5..8 && opt.unlockMusic) {
                logger.info("Response: ${maimai2.musicMapping.size} items - Music unlock")
                return mapOf(
                    "userId" to userId,
                    "nextIndex" to 0,
                    "itemKind" to kind,
                    "userItemList" to musicUnlock.getValue(kind)
                )
            }
        }

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
