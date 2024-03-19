package icu.samnyan.aqua.net.games

import ext.API
import ext.RP
import ext.Str
import ext.minus
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.Maimai2DataExport
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.maimai2.model.*
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail
import org.springframework.web.bind.annotation.RestController
import java.lang.reflect.Field
import java.util.*
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

@RestController
@API("api/v2/game/mai2")
class Maimai2(
    override val us: AquaUserServices,
    override val playlogRepo: Mai2UserPlaylogRepo,
    override val userDataRepo: Mai2UserDataRepo,
    val userGeneralDataRepository: Mai2UserGeneralDataRepo,
    val repos: Mai2Repos
): GameApiController<UserDetail>("mai2") {
    override suspend fun trend(@RP username: Str): List<TrendOut> = us.cardByName(username) { card ->
        findTrend(playlogRepo.findByUserCardExtId(card.extId)
            .map { TrendLog(it.playDate, it.afterRating) })
    }

    // Only show > S rank
    override val shownRanks = mai2Scores.filter { it.first >= 97 * 10000 }
    override val settableFields: Map<String, (UserDetail, String) -> Unit> = mapOf(
        "name" to { u, v -> u.userName = v
            if (!v.all { it in USERNAME_CHARS }) { 400 - "Invalid character in username" }
        },
    )

    override suspend fun userSummary(@RP username: Str) = us.cardByName(username) { card ->
        val extra = userGeneralDataRepository.findByUser_Card_ExtId(card.extId)
            .associate { it.propertyKey to it.propertyValue }

        val ratingComposition = mapOf(
            "best35" to (extra["recent_rating"] ?: ""),
            "best15" to (extra["recent_rating_new"] ?: "")
        )

        genericUserSummary(card, ratingComposition)
    }

    // Use reflection to get all properties in Mai2Repos with matching names in Maimai2DataExport
    var exportFields: Map<Field, UserLinked<*>> = listOf(*Maimai2DataExport::class.java.declaredFields)
        .filter { it.name !in arrayOf("gameId", "userData") }
        .associateWith { Mai2Repos::class.declaredMembers
            .filter { f -> f.returnType.jvmErasure.isSubclassOf(UserLinked::class) }
            .firstOrNull { f -> f.name == it.name || f.name == it.name.replace("List", "") }
            ?.call(repos) as UserLinked<*>? ?: error("No matching field found for ${it.name}")
        }

    @API("export")
    fun exportAllUserData(@RP token: Str) = us.jwt.auth(token) { u ->
        try {
            Maimai2DataExport().apply {
                gameId = "SDEZ"
                userData = repos.userData.findByCard(u.ghostCard) ?: (404 - "User not found")
                exportFields.forEach { (f, u) ->
                    f.set(this, if (f.type == List::class.java) u.findByUser(userData)
                        else u.findSingleByUser(userData).orElse(null))
                }
            }
        } catch (e: Exception) { 500 - "Error during data export. Reason: ${e.message}" }
    }
}