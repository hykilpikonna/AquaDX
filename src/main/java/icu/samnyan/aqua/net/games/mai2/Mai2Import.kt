package icu.samnyan.aqua.net.games.mai2

import ext.jackson
import icu.samnyan.aqua.api.model.resp.sega.maimai2.external.Maimai2DataExport
import icu.samnyan.aqua.net.games.GameDataImport
import icu.samnyan.aqua.net.games.ImportResult
import icu.samnyan.aqua.net.games.asSqlInsert
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText
import kotlin.reflect.KClass
import kotlin.time.measureTime

data class ImportClass<T : Any>(
    val type: KClass<T>,
    val renames: Map<String, String?>? = null,
    val name: String = type.simpleName!!.lowercase()
)
val exportFields = Maimai2DataExport::class.java.declaredFields.associateBy {
    it.name.replace("List", "").lowercase()
}

class Mai2Import : GameDataImport {
    fun <T : Any> ImportClass<T>.mapTo(rawDict: Map<String, String>): T {
        // Process renaming
        var dict = renames?.let { rawDict
            .filter { (k, _) -> if (k in it) it[k] != null else true }
            .mapKeys { (k, _) -> it[k] ?: k } } ?: rawDict

        // Process Nones
        dict = dict.filterValues { it != "None" }

        return jackson.convertValue(dict, type.java)
    }

    val renameTable = mapOf(
        "mai2_item_character" to ImportClass(UserCharacter::class),
        "mai2_item_charge" to ImportClass(UserCharge::class),
        "mai2_item_friend_season_ranking" to ImportClass(UserFriendSeasonRanking::class),
        "mai2_item_item" to ImportClass(UserItem::class, mapOf("isValid" to "valid")),
        "mai2_item_login_bonus" to ImportClass(UserLoginBonus::class),
        "mai2_item_map" to ImportClass(UserMap::class),
        "mai2_playlog" to ImportClass(UserPlaylog::class, mapOf("userId" to null)),
        "mai2_profile_activity" to ImportClass(UserAct::class, mapOf("activityId" to "id")),
        "mai2_profile_detail" to ImportClass(UserDetail::class,
            mapOf("user" to null, "version" to null, "isNetMember" to null),
            name = "userdata"),
        "mai2_profile_extend" to ImportClass(UserExtend::class, mapOf("version" to null)),
        "mai2_profile_option" to ImportClass(UserOption::class, mapOf("version" to null)),
        "mai2_score_best" to ImportClass(UserMusicDetail::class),
        "mai2_score_course" to ImportClass(UserCourse::class),
        // "mai2_profile_ghost" to ImportClass(UserGhost::class),
        // "mai2_profile_rating" to ImportClass(UserRating::class),
        // "mai2_profile_region" to ImportClass(UserRegion::class),
    )

    init {
        renameTable.values.forEach {
            if (it.name !in exportFields) error("Code error! Export fields incomplete")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun importArtemisSql(sql: String): ImportResult {
        val data = Maimai2DataExport("SDEZ", UserDetail(), UserExtend(), UserOption(),
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),
            ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),
            ArrayList(), UserUdemae())
        val errors = ArrayList<String>()
        val warnings = ArrayList<String>()
        fun err(msg: String) { errors.add(msg) }
        fun warn(msg: String) { warnings.add(msg) }

        sql.lines()

        val lists = exportFields.filter { it.value.type == List::class.java }
            .mapValues { it.value.get(data) as ArrayList<Any> }

        val statements = sql.replace("\r\n", "\n").split('\n', '\r').mapNotNull {
            try { it.asSqlInsert() }
            catch (e: Exception) { err("Failed to parse insert: $it\n${e.message}"); null }
        }

        // For each insert statement, we will try to parse the values
        statements.forEachIndexed fi@{ i, insert ->
            // Try to map tables
            val tb = renameTable[insert.table] ?: return@fi warn("Unknown table ${insert.table} in insert $i")
            val field = exportFields[tb.name]!!
            val obj = tb.mapTo(insert.mapping)

            // Add value to list or set field
            lists[tb.name]?.add(obj) ?: field.set(data, obj)
        }

        return ImportResult(errors, warnings, jackson.writeValueAsString(data))
    }
}