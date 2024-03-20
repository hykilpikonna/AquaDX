package icu.samnyan.aqua.sega.maimai2.handler.impl

import icu.samnyan.aqua.net.games.mai2.Maimai2
import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler
import icu.samnyan.aqua.sega.maimai2.model.Mai2Repos
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserItem.Mai2ItemKind
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
        ) } }

    val itemUnlock = Mai2ItemKind.ALL.filter { it.key in 1..3 || it.key in 9..12 }
        .mapValues { (kind, kindEnum) -> maimai2.itemMapping[kindEnum.name]?.map { (id, item) ->
        mapOf(
            "itemKind" to kind,
            "itemId" to id,
            "stock" to 1,
            "isValid" to true,
        ) } ?: emptyList() }

    init {
        if (musicUnlock[5].isNullOrEmpty()) logger.warn("Mai2 music info is empty")
        if (itemUnlock[1].isNullOrEmpty()) logger.warn("Mai2 item info is empty")
    }

    override fun handle(request: Map<String, Any>): Any {
        val userId = (request["userId"] as Number).toLong()
        val nextIndexVal = (request["nextIndex"] as Number).toLong()
        val maxCount = (request["maxCount"] as Number).toInt()

        val kind = (nextIndexVal / MULT).toInt()
        val nextIndex = (nextIndexVal % MULT).toInt()
        val pageNum = nextIndex / maxCount
        val kindType = Mai2ItemKind.ALL[kind]?.name

        // Aqua Net game unlock feature
        cardRepo.findByExtId(userId).getOrNull()?.aquaUser?.gameOptions?.let { opt ->
            val items = when {
                (kind in 5..8) && opt.unlockMusic -> musicUnlock.getValue(kind)
                (kind in 1..3 || kind == 11) && opt.unlockCollectables -> itemUnlock[kind]
                (kind == 12) && opt.unlockTickets -> itemUnlock[kind]
                (kind in 9..10) && opt.unlockChara -> itemUnlock[kind]
                else -> emptyList()
            }

            // If no items are found, disable the unlock feature
            if (items.isNullOrEmpty()) return@let

            logger.info("Response: ${items.size} $kindType items - All unlock")
            return mapOf(
                "userId" to userId,
                "nextIndex" to 0,
                "itemKind" to kind,
                "userItemList" to items
            )
        }

        val dbPage = repos.userItem.findByUser_Card_ExtIdAndItemKind(userId, kind, PageRequest.of(pageNum, maxCount))

        val currentIndex = kind * MULT + maxCount * pageNum + dbPage.numberOfElements
        val result = mapOf(
            "userId" to userId,
            "nextIndex" to if (dbPage.numberOfElements < maxCount) 0 else currentIndex,
            "itemKind" to kind,
            "userItemList" to dbPage.content
        )

        logger.info("Response: ${dbPage.numberOfElements} $kindType items")
        return result
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(GetUserItemHandler::class.java)
        const val MULT = 10000000000L
    }
}
