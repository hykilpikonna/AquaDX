package icu.samnyan.aqua.sega.wacca

import ext.*
import icu.samnyan.aqua.sega.general.dao.CardRepository
import icu.samnyan.aqua.sega.wacca.WaccaItemType.*
import icu.samnyan.aqua.sega.wacca.WaccaOptionType.SET_ICON_ID
import icu.samnyan.aqua.sega.wacca.WaccaOptionType.SET_TITLE_ID
import icu.samnyan.aqua.sega.wacca.model.BaseRequest
import icu.samnyan.aqua.sega.wacca.model.db.WaccaRepos
import icu.samnyan.aqua.sega.wacca.model.db.WaccaUser
import icu.samnyan.aqua.sega.wacca.model.db.WcUserGate
import io.ktor.client.utils.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

val empty = emptyList<Any>()

@RestController
@API("/g/wacca/")
class WaccaServer(val rp: WaccaRepos, val cardRepo: CardRepository) {
    val handlerMap = mutableMapOf<String, (BaseRequest, List<Any>) -> Any>()
    val cacheMap = mutableMapOf<String, String>()

    val log = logger()
    val season = 3
    val enabledGates = 1..24

    init { init() }

    // DSL Functions
    fun user(uid: Any) = rp.user.findByCardExtId(uid.long())
    fun options(u: WaccaUser?) = u?.let { rp.option.findByUser(u).associate { it.optId to it.value } } ?: emptyMap()
    operator fun Map<Int, Int>.get(type: WaccaOptionType) = getOrDefault(type.id, type.default)
    operator fun String.minus(value: Any) = value
    operator fun String.invoke(block: (BaseRequest, List<Any>) -> Any) { handlerMap[this.lowercase()] = block }
    infix fun String.cached(block: () -> Any) { cacheMap[this.lowercase()] =
        block().let { if (it is String) it else it.toJson() } }

    /** Convert "3.07.01.JPN.26935.S" into "3.7.1" */
    fun String.shortVer() = split('.').let { if (it.size < 3) "1.0.0" else
        "${it[0]}.${it[1].toInt()}.${it[2].toInt()}" }

    /** Generate response message */
    fun resp(paramsJson: String, status: Int = 0, message: String = ""): ResponseEntity<String> {
        val resp = """{"status":$status,"message":"${message.replace("\"", "\\\"")}","serverTime":${millis() / 1000},""" +
            """"maintNoticeTime":0,"maintNotPlayableTime":0,"maintStartTime":0,"params":$paramsJson}"""

        return ResponseEntity.ok().headers(
            "X-Wacca-Hash" to resp.md5(),
            "Content-Type" to "application/json; charset=utf-8"
        ).body(resp)
    }

    /** Handle all requests */
    @API("/api/**")
    fun handle(req: HttpServletRequest, @RB body: String): Any {
        val path = req.requestURI.removePrefix("/g/wacca").removePrefix("/api").removePrefix("/").lowercase()
        if (path in cacheMap) return resp(cacheMap[path]!!)
        if (path !in handlerMap) return resp("[]", 1, "Not Found")

        log.info("Wacca < $path : $body")

        val br = JACKSON.parse<BaseRequest>(body)
        return handlerMap[path]!!(br, br.params).let { when (it) {
            is String -> return resp(it)
            is List<*> -> return resp(it.toJson())
            else -> Error("Invalid response type ${it.javaClass}")
        } }.let { log.info("Wacca > $path : $it") }
    }
}

fun WaccaServer.init() {
    "housing/get" cached { ls("housingId" - 39, "isNewCab" - 0) }
    "housing/start" cached { ls("regionId" - 1, "recommendSongList" - ls(1269, 1007, 1270, 1002, 1020, 1003, 1008,
        1211, 1018, 1092, 1056, 32, 1260, 1230, 1258, 1251, 2212, 1264, 1125, 1037, 2001, 1272, 1126, 1119, 1104, 1070,
        1047, 1044, 1027, 1004, 1001, 24, 2068, 2062, 2021, 1275, 1249, 1207, 1203, 1107, 1021, 1009, 9, 4, 3, 23, 22,
        2014, 13, 1276, 1247, 1240, 1237, 1128, 1114, 1110, 1109, 1102, 1045, 1043, 1036, 1035, 1030, 1023, 1015))
    }
    "advertise/GetRanking" cached { "[]" }
    "advertise/GetNews" cached { "[[], [], [], [], [], [], [], [], []]" }
    "user/info/GetMyRoom" cached { ls(0, 0, 0, 0, 0, empty, 0, 0, 0) }
    "user/status/Logout" cached { "[]" }
    "user/info/GetRanking" cached { ls("totalScore#" - 0, "highScoreBySong#" - 0, "cumulativeScore#" - 0,
        "stateUpScore#" - 0, "otherScore#" - 0, "waccaPoints#" - 0) }

    "user/status/get" { req, (uid) ->
        val ru = user(uid)
        val u = ru ?: WaccaUser()
        val o = options(ru)
        u.run { ls(u.lStatus(),
            o[SET_TITLE_ID], o[SET_ICON_ID],
            "status" - if (ru == null) 1 else 0, // 0 = GOOD, 1 = Register
            "version" - ls(req.appVersion.shortVer().compareTo(lastGameVer.shortVer())
                .let { if (it < 0) 1 else if (it > 0) 2 else 0 }, // 0 = Version GOOD, 1 = Game is newer, 2 = Game is older
                lastGameVer.shortVer()),
            o.map { (k, v) -> ls(k, v) }
        ) }
    }

    "user/status/create" { _, (uid, name) ->
        if (user(uid) != null) 400 - "User already exists"

        val u = rp.user.save(WaccaUser().apply {
            card = cardRepo.findByExtId(uid.long())() ?: (404 - "Card not found")
            username = name.toString()
        })

        // Starter items
        rp.item.saveAll(
            ls(104001, 104002, 104003, 104005).map { TITLE(u, it) } +
            ls(102001, 102002).map { ICON(u, it) } +
            ls(103001, 203001).map { NOTE_COLOR(u, it) } +
            ls(105001, 205005).map { NOTE_SOUND(u, it) } +
            (ls(210001, 210002, 310001, 310002) + (210054..210061)).map { NAVIGATOR(u, it) } +
            ls(211001).map { USER_PLATE(u, it) } +
            ls(312000, 312001).map { TOUCH_EFFECT(u, it) }
        )

        ls(u.lStatus())
    }

    "user/status/login" api@ { _, (uid) ->
        val u = user(uid)
        if (uid == 0 || u == null) return@api "[[], [], [], 0, [2077, 1, 1, 1, [], []], 0, []]"

        // Record login
        rp.user.save(u.apply {
            loginCountConsec = loginCount++
            if (millis() - lastLoginDate.time > 86400000) { // Is new day
                loginCountDays++
                loginCountToday = 0
                if (millis() - lastLoginDate.time < 172800000) loginCountDaysConsec++
            }
            loginCountToday++
            lastLoginDate = Date()
        })

        "[[], [], [], 0, [2077, 1, 1, 1, [], []], ${u.lastLoginDate.time / 1000}, []]"
    }

    "user/status/GetDetail" api@ { _, (uid) ->
        val u = user(uid) ?: return@api "[]"
        val o = options(u)
        val items = rp.item.findByUser(u).groupBy { it.type }
        val scores = rp.bestScore.findByUser(u)
        val gates = rp.gate.findByUser(u)
        val bingo = rp.bingo.findByUser(u).firstOrNull()
        val go = u.card.aquaUser?.gameOptions

        // TODO: make this and vip configurable
        u.wp = 999999

        u.run { ls("status" - lStatus(),
            "options" - o.map { (k, v) -> ls(k, v) },
            "seasonalPlayModeCounts" - (ls(playcountSingle, playcountMultiVs, playcountMultiCoop, playcountStageup, playcountTimeFree)
                .mapIndexed { i, it -> ls(season, i + 1, it) } + ls(ls(0, 1, 1))),
            "items" - ls(MUSIC_UNLOCK, TITLE, ICON, TROPHY, SKILL, TICKET, NOTE_COLOR, NOTE_SOUND, NAVIGATOR, USER_PLATE, TOUCH_EFFECT).map {
                if (it == TICKET && go?.unlockTickets == true) (0..4).map { ls(it, 106002, 0) }
                else items[it()]?.map { it.ls() } ?: empty
            },
            "scores" - scores.map { it.ls() },
            "songPlayStatus" - ls(lastSongId, 1),
            "seasonInfo" - ls(xp, wpTotal, wpSpent, scores.sumOf { it.score },
                items[TITLE()]?.size ?: 0, items[ICON()]?.size ?: 0, 0,
                items[NOTE_COLOR()]?.size ?: 0, items[NOTE_SOUND()]?.size ?: 0, items[USER_PLATE()]?.size ?: 0,
                gates.sumOf { it.totalPoints }),
            "playAreaList" - "[[0],[0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0],[0,0,0,0,0],[0,0,0,0],[0,0,0,0,0,0,0],[0]]".jsonArray(),
            "songUpdateTime" - lastLoginDate.time / 1000,
            "favorites" - rp.favoriteSong.findByUser(u).map { it.songId },
            "stoppedSongIds" - empty,
            "events" - empty,
            "gate" - gates.associateBy { it.gateId }.let { gateMap -> enabledGates.map {
                gateMap[it]?.ls() ?: WcUserGate().apply { gateId = it }.ls()
            } },
            "lastSongInfo" - ls(lastSongId, lastSongDifficulty, lastFolderOrder, lastFolderId, lastSongOrder),
            "gateTutorialFlags" - gateTutorialFlags.jsonArray(),
            "gatchaInfo" - empty,
            "friendList" - empty,
            "bingoStatus" - ls(
                "pageNumber" - (bingo?.pageNumber ?: 0),
                "pageStatus" - (bingo?.pageProgress?.jsonArray() ?: empty)
            )
        ) }
    }

}