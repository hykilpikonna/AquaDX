package icu.samnyan.aqua.sega.chusan.handler

import com.fasterxml.jackson.core.JsonProcessingException
import ext.int
import ext.long
import icu.samnyan.aqua.sega.chusan.service.UserItemService
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.util.jackson.StringMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Handler for getting user item.
 * This get call before profile create.
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("ChusanGetUserItemHandler")
class GetUserItemHandler(private val mapper: StringMapper, private val userItemService: UserItemService) : BaseHandler {
    @Throws(JsonProcessingException::class)
    override fun handle(request: Map<String, Any>): String {
        val userId = request["userId"].toString()
        val nextIndexVal = request["nextIndex"]?.long() ?: 0
        val maxCount = request["maxCount"]?.int() ?: 100

        val mul = 10000000000L

        val kind = (nextIndexVal / mul).toInt()
        val nextIndex = (nextIndexVal % mul).toInt()
        val pageNum = nextIndex / maxCount

        val userItemPage = userItemService.getByUserAndItemKind(userId, kind, pageNum, maxCount)
        val userItemList = userItemPage.content
        val currentIndex = kind * mul + maxCount * pageNum + userItemPage.numberOfElements

        // TODO: Music unlock

        val resultMap = mapOf(
            "userId" to userId,
            "length" to userItemPage.numberOfElements,
            "nextIndex" to if (userItemPage.numberOfElements < maxCount) -1 else currentIndex,
            "itemKind" to kind,
            "userItemList" to userItemList
        )

        val json = mapper.write(resultMap)
        logger.info("Response: $json")
        return json
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(GetUserItemHandler::class.java)
    }
}
