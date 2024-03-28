package icu.samnyan.aqua.sega.wacca

import ext.*
import icu.samnyan.aqua.sega.wacca.WaccaOptionType.SET_ICON_ID
import icu.samnyan.aqua.sega.wacca.WaccaOptionType.SET_TITLE_ID
import icu.samnyan.aqua.sega.wacca.model.BaseRequest
import icu.samnyan.aqua.sega.wacca.model.db.WaccaRepos
import icu.samnyan.aqua.sega.wacca.model.db.WaccaUser
import io.ktor.client.utils.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

val empty = emptyList<Any>()

@RestController
@API("/g/wacca/")
class WaccaServer(val rp: WaccaRepos) {
    val handlerMap = mutableMapOf<String, (BaseRequest, List<Any>) -> List<Any>>()
    val cacheMap = mutableMapOf<String, String>()

    val log = logger()

    init { api() }

    // DSL Functions
    fun options(u: WaccaUser?) = u?.let { rp.option.findByUser(u).associate { it.optId to it.value } } ?: emptyMap()
    operator fun Map<Int, Int>.get(type: WaccaOptionType) = getOrDefault(type.id, type.default)

    fun ls(vararg args: Any) = args.toList()
    operator fun String.minus(value: Any) = value

    operator fun String.invoke(block: (BaseRequest, List<Any>) -> List<Any>) { handlerMap[this.lowercase()] = block }
    infix fun String.cached(block: () -> Any) { cacheMap[this.lowercase()] = block().toJson() }

    /** Convert "3.07.01.JPN.26935.S" into "3.7.1" */
    fun String.shortVer() = split('.').let { "${it[0]}.${it[1].trimStart('0')}.${it[2].trimStart('0')}" }

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

        val br = JACKSON.parse<BaseRequest>(body)
        val resp = handlerMap[path]!!(br, br.params)
        return resp(resp.toJson())
    }
}

fun WaccaServer.api() {
    "housing/get" cached { ls("housingId" - 39, "isNewCab" - 0) }
    "housing/start" cached { ls("regionId" - 1, "recommendSongList" - ls(1269, 1007, 1270, 1002, 1020, 1003, 1008,
        1211, 1018, 1092, 1056, 32, 1260, 1230, 1258, 1251, 2212, 1264, 1125, 1037, 2001, 1272, 1126, 1119, 1104, 1070,
        1047, 1044, 1027, 1004, 1001, 24, 2068, 2062, 2021, 1275, 1249, 1207, 1203, 1107, 1021, 1009, 9, 4, 3, 23, 22,
        2014, 13, 1276, 1247, 1240, 1237, 1128, 1114, 1110, 1109, 1102, 1045, 1043, 1036, 1035, 1030, 1023, 1015))
    }
    "advertise/GetRanking" cached { empty }
    "advertise/GetNews" cached { ls("notices" - empty, "copyright" - empty, empty, empty, empty, empty, empty, empty, empty) }
    "user/info/GetMyRoom" cached { ls(0, 0, 0, 0, 0, empty, 0, 0, 0) }
    "user/status/Logout" cached { empty }
    "user/info/GetRanking" cached { ls("totalScore#" - 0, "highScoreBySong#" - 0, "cumulativeScore#" - 0,
        "stateUpScore#" - 0, "otherScore#" - 0, "waccaPoints#" - 0) }

    "user/status/get" { req, (uid) ->
        val ru = rp.user.findById(uid.long())()
        val u = ru ?: WaccaUser()
        val o = options(ru)
        u.run { ls(
            ls(uid, username, "userType" - 1, xp, danLevel, danType, wp, "titlePartIds" - ls(0, 0, 0),
                loginCount, loginCountDays, loginCountConsec, loginCountDaysConsec, vipExpireTime, loginCountToday, rating),
            o[SET_TITLE_ID], o[SET_ICON_ID],
            "status" - if (ru == null) 1 else 0, // 0 = GOOD, 1 = Register
            // 0 = Version GOOD, 1 = Game is newer, 2 = Game is older
            "version" - ls(req.appVersion.shortVer().compareTo(lastGameVer.shortVer()), lastGameVer.shortVer())
        ) }
    }
}