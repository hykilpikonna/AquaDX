package icu.samnyan.aqua.net.games

import ext.*
import icu.samnyan.aqua.net.db.AquaUserServices
import icu.samnyan.aqua.net.utils.*
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData
import icu.samnyan.aqua.sega.general.model.Card
import kotlinx.serialization.Serializable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.util.*
import kotlin.jvm.optionals.getOrNull
import kotlin.reflect.KClass

data class TrendOut(val date: String, val rating: Int, val plays: Int)

data class RankCount(val name: String, val count: Int)

data class GenericGameSummary(
    val name: String,
    val iconId: Int,

    val aquaUser: Map<String, Any?>?,

    val serverRank: Long,
    val accuracy: Double,
    val rating: Int,
    val ratingHighest: Int,
    val ranks: List<RankCount>,
    val detailedRanks: Map<Int, Map<String, Int>>,
    val maxCombo: Int,
    val fullCombo: Int,
    val allPerfect: Int,
    val totalScore: Long,

    val plays: Int,
    val totalPlayTime: Long,
    val joined: String,
    val lastSeen: String,
    val lastVersion: String,

    val ratingComposition: Map<String, Any>,

    val recent: List<IGenericGamePlaylog>
)

data class GenericRankingPlayer(
    val rank: Int,
    val name: String,
    val username: String?,
    val accuracy: Double,
    val rating: Int,
    val allPerfect: Int,
    val fullCombo: Int,
    val lastSeen: String
)

@Serializable
data class GenericMusicMeta(
    val name: String?,
    val ver: String,
    val notes: List<GenericNoteMeta>
)

@Serializable
data class GenericNoteMeta(
    val lv: Double,
)

@Serializable
data class GenericItemMeta(
    val name: String? = null,
    val disable: Boolean? = null,
    val ver: String? = null
)

// Here are some interfaces to generalize across multiple games
interface IGenericUserData {
    val userName: String
    val iconId: Int
    val playerRating: Int
    val highestRating: Int
    val firstPlayDate: Any
    val lastPlayDate: Any
    val lastRomVersion: String
    val totalScore: Long
    var card: Card?
}

interface IGenericGamePlaylog {
    val musicId: Int
    val level: Int
    val userPlayDate: Any
    val achievement: Int
    val maxCombo: Int
    val isFullCombo: Boolean
    val beforeRating: Int
    val afterRating: Int
    val isAllPerfect: Boolean
}

@NoRepositoryBean
interface GenericUserDataRepo<T : IGenericUserData> : JpaRepository<T, Long> {
    fun findByCard(card: Card): T?
    fun findByCard_ExtId(extId: Long): Optional<T>
    @Query("select count(*) from #{#entityName} where playerRating > :rating")
    fun getRanking(rating: Int): Long
}

@NoRepositoryBean
interface GenericPlaylogRepo<T: IGenericGamePlaylog> : JpaRepository<T, Long> {
    fun findByUserCardExtId(extId: Long): List<T>
    fun findByUserCardExtId(extId: Long, page: Pageable): Page<T>
}

abstract class GameApiController<T : IGenericUserData>(name: String, userDataClass: KClass<T>) {
    val musicMapping = resJson<Map<String, GenericMusicMeta>>("/meta/$name/music.json")
        ?.mapKeys { it.key.toInt() } ?: emptyMap()

    val itemMapping = resJson<Map<String, Map<String, GenericItemMeta>>>("/meta/$name/items.json") ?: emptyMap()

    abstract val us: AquaUserServices
    abstract val userDataRepo: GenericUserDataRepo<T>
    abstract val playlogRepo: GenericPlaylogRepo<*>
    abstract val shownRanks: List<Pair<Int, String>>
    abstract val settableFields: Map<String, (T, String) -> Unit>

    @API("trend")
    abstract suspend fun trend(@RP username: String): List<TrendOut>
    @API("user-summary")
    abstract suspend fun userSummary(@RP username: String): GenericGameSummary

    @API("recent")
    suspend fun recent(@RP username: String): List<IGenericGamePlaylog> = us.cardByName(username) { card ->
        playlogRepo.findByUserCardExtId(card.extId)
    }

    private var rankingCache: Pair<Long, List<GenericRankingPlayer>>? = null
    @API("ranking")
    fun ranking(): List<GenericRankingPlayer> {
        // Read from cache if we just computed it less than 2 minutes ago
        rankingCache?.let { (t, r) ->
            if (millis() - t < 120_000) return r
        }

        // TODO: pagination
        val players = userDataRepo.findAll().sortedByDescending { it.playerRating }
        return players.filter { it.card != null }.mapIndexed { i, user ->
            val plays = playlogRepo.findByUserCardExtId(user.card!!.extId)

            GenericRankingPlayer(
                rank = i + 1,
                name = user.userName,
                accuracy = plays.acc(),
                rating = user.playerRating,
                allPerfect = plays.count { it.isAllPerfect },
                fullCombo = plays.count { it.isFullCombo },
                lastSeen = user.lastPlayDate.toString(),
                username = user.card!!.aquaUser?.username ?: "user${user.card!!.id}"
            )
        }.also { rankingCache = millis() to it }  // Update the cache
    }

    @API("playlog")
    fun playlog(@RP id: Long): IGenericGamePlaylog = playlogRepo.findById(id).getOrNull() ?: (404 - "Playlog not found")

    val userDetailFields by lazy { userDataClass.gettersMap().let { vm ->
        settableFields.map { (k, _) -> k to (vm[k] ?: error("Field $k not found")) }.toMap()
    } }

    @API("user-detail")
    suspend fun userDetail(@RP username: String) = us.cardByName(username) { card ->
        val u = userDataRepo.findByCard(card) ?: (404 - "User not found")
        userDetailFields.toList().associate { (k, f) -> k to f.invoke(u) }
    }

    @API("user-detail-set")
    suspend fun userDetailSet(@RP token: String, @RP field: String, @RP value: String): Any {
        val prop = settableFields[field] ?: (400 - "Invalid field $field")

        return us.jwt.auth(token) { u ->
            val user = async { userDataRepo.findByCard(u.ghostCard) } ?: (404 - "User not found")
            prop(user, value)
            async { userDataRepo.save(user) }
            SUCCESS
        }
    }

    fun genericUserSummary(card: Card, ratingComp: Map<String, String>): GenericGameSummary {
        // Summary values: total plays, player rating, server-wide ranking
        // number of each rank, max combo, number of full combo, number of all perfect
        val user = userDataRepo.findByCard(card) ?: (404 - "Game data not found")
        val plays = playlogRepo.findByUserCardExtId(card.extId)

        // Detailed ranks: Find the number of each rank in each level category
        // map<level, map<rank, count>>
        val rankMap = shownRanks.associate { (_, v) -> v to 0 }
        val detailedRanks = HashMap<Int, MutableMap<String, Int>>()
        plays.forEach { play ->
            val lvl = musicMapping[play.musicId]?.notes?.getOrNull(if (play.level == 10) 0 else play.level)?.lv ?: return@forEach
            shownRanks.find { (s, _) -> play.achievement > s }?.let { (_, v) ->
                val ranks = detailedRanks.getOrPut(lvl.toInt()) { rankMap.toMutableMap() }
                ranks[v] = ranks[v]!! + 1
            }
        }

        // Collapse detailed ranks to get non-detailed ranks map<rank, count>
        val ranks = shownRanks.associate { (_, v) -> v to 0 }.toMutableMap().also { ranks ->
            plays.forEach { play ->
                shownRanks.find { (s, _) -> play.achievement > s }?.let { (_, v) -> ranks[v] = ranks[v]!! + 1 }
            }
        }

        return GenericGameSummary(
            name = user.userName,
            iconId = user.iconId,
            aquaUser = card.aquaUser?.publicFields,
            serverRank = userDataRepo.getRanking(user.playerRating),
            accuracy = plays.acc(),
            rating = user.playerRating,
            ratingHighest = user.highestRating,
            ranks = ranks.map { (k, v) -> RankCount(k, v) },
            detailedRanks = detailedRanks,
            maxCombo = plays.maxOfOrNull { it.maxCombo } ?: 0,
            fullCombo = plays.count { it.isFullCombo },
            allPerfect = plays.count { it.isAllPerfect },
            totalScore = user.totalScore,
            plays = plays.size,
            totalPlayTime = plays.count() * 3L, // TODO: Give a better estimate
            joined = user.firstPlayDate.toString(),
            lastSeen = user.lastPlayDate.toString(),
            lastVersion = user.lastRomVersion,
            ratingComposition = ratingComp,
            recent = plays.sortedBy { it.userPlayDate.toString() }.takeLast(15).reversed()
        )
    }
}