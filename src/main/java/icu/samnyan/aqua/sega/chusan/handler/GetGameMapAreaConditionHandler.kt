package icu.samnyan.aqua.sega.chusan.handler

import ext.logger
import icu.samnyan.aqua.sega.general.BaseHandler
import icu.samnyan.aqua.sega.util.jackson.StringMapper
import org.springframework.stereotype.Component

@Component("ChusanGetGameMapAreaConditionHandler")
class GetGameMapAreaConditionHandler(val mapper: StringMapper) : BaseHandler {
    override fun handle(request: Map<String, Any>): String {
        val cond = listOf(
            mapOf("mapAreaId" to 2206201, "mapAreaConditionList" to listOf(
                mapOf("type" to 3, "conditionId" to 6832, "logicalOpe" to 1)
            )),
            mapOf("mapAreaId" to 2206203, "mapAreaConditionList" to listOf(
                mapOf("type" to 3, "conditionId" to 6833, "logicalOpe" to 1)
            )),
            mapOf("mapAreaId" to 2206204, "mapAreaConditionList" to listOf(
                mapOf("type" to 3, "conditionId" to 6834, "logicalOpe" to 1),
                mapOf("type" to 3, "conditionId" to 6835, "logicalOpe" to 1)
            )),
            mapOf("mapAreaId" to 2206205, "mapAreaConditionList" to listOf(
                mapOf("type" to 3, "conditionId" to 6837, "logicalOpe" to 1)
            )),
            mapOf("mapAreaId" to 2206206, "mapAreaConditionList" to listOf(
                mapOf("type" to 3, "conditionId" to 6838, "logicalOpe" to 1)
            )),
            mapOf("mapAreaId" to 2206207, "mapAreaConditionList" to listOf(
                mapOf("type" to 2, "conditionId" to 2206201, "logicalOpe" to 1),
                mapOf("type" to 2, "conditionId" to 2206202, "logicalOpe" to 1),
                mapOf("type" to 2, "conditionId" to 2206203, "logicalOpe" to 1),
                mapOf("type" to 2, "conditionId" to 2206204, "logicalOpe" to 1),
                mapOf("type" to 2, "conditionId" to 2206205, "logicalOpe" to 1),
                mapOf("type" to 2, "conditionId" to 2206206, "logicalOpe" to 1)
            )),
            mapOf("mapAreaId" to 3229301, "mapAreaConditionList" to listOf(
                mapOf("type" to 1, "conditionId" to 3020701, "logicalOpe" to 2)
            )),
            mapOf("mapAreaId" to 3229302, "mapAreaConditionList" to listOf(
                mapOf("type" to 1, "conditionId" to 3020701, "logicalOpe" to 1)
            ))
        )

        val resultMap: MutableMap<String, Any> = linkedMapOf(
            "gameMapAreaConditionList" to cond
        )

        val json = mapper.write(resultMap)
        logger.info("Response: $json")
        return json
    }

    companion object {
        private val logger = logger()
    }
}
