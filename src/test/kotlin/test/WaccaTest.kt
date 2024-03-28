package test

import ext.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class WaccaTest : StringSpec({
    val version = "3.07.01.JPN.26935.S"
    var uid = 0L
    var requestNo = 10000

    data class PostResp(val resp: HttpResponse, val res: List<Any>)
    suspend fun post(url: String, par: String): PostResp {
        requestNo++
        val resp = HTTP.post("$HOST/gs/$CLIENT_ID/wacca/api/$url") {
            contentType(ContentType.Application.Json)
            setBody("""{"requestNo": ${requestNo++},"appVersion": "$version","boardId": "$BOARD_ID","chipId": "$FULL_CLIENT_ID","params": $par}""")
        }

        assert(resp.status.isSuccess()) { "Failed to post to $url: ${resp.status} - ${resp.bodyAsText()}" }
        val res = resp.bodyAsText().jsonMap()
        res["status"] shouldBe 0
        res.keys shouldBe setOf("status", "message", "serverTime", "maintNoticeTime", "maintNotPlayableTime", "maintStartTime", "params")

        return PostResp(resp, res["params"] as List<Any>)
    }

    infix fun List<Any?>.exp(expected: List<Any?>) {
        expected.size shouldBe size
        for (i in indices) {
            val a = this[i]
            when (val b = expected[i]) {
                null -> {} // Use null to ignore the value
                is List<*> -> a as List<Any> exp b as List<Any>
                else -> a shouldBe b
            }
        }
    }

    infix fun List<Any?>.exp(json: String) = exp(json.jsonArray())

    beforeTest {
        if (uid == 0L) uid = registerUser()
    }

    "housing/get #1" {
        post("housing/get", "[]").res exp "[39, 0]"
    }

    "housing/start #1" {
        post("housing/start", """["", "2024/03/24 10:39:36, ApiUserStatusLogout,0\\n2024/03/24 10:51:06, ApiUserStatusLogout,0\\n2024/03/24 10:54:19, ApiUserStatusLogout,0\\n2024/03/24 10:59:33, ApiAdvertiseGetNews,0\\n2024/03/24 11:10:31, ApiAdvertiseGetNews,0\\n2024/03/24 11:11:04, ApiUserStatusLogout,0\\n2024/03/24 11:19:51, ,0\\n2024/03/24 11:20:14, ApiAdvertiseGetNews,0\\n", "", [[1, "SERVER"], [2, "JPN"]]]""").res exp
            "[1, [1269, 1007, 1270, 1002, 1020, 1003, 1008, 1211, 1018, 1092, 1056, 32, 1260, 1230, 1258, 1251, 2212, 1264, 1125, 1037, 2001, 1272, 1126, 1119, 1104, 1070, 1047, 1044, 1027, 1004, 1001, 24, 2068, 2062, 2021, 1275, 1249, 1207, 1203, 1107, 1021, 1009, 9, 4, 3, 23, 22, 2014, 13, 1276, 1247, 1240, 1237, 1128, 1114, 1110, 1109, 1102, 1045, 1043, 1036, 1035, 1030, 1023, 1015]]"
    }

    "advertise/GetNews #1" {
        post("advertise/GetNews", "[]").res exp
            "[[], [], [], [], [], [], [], [], []]"
    }

    "user/status/get #1" {
        post("user/status/get", """["$uid"]""").res exp
            """[[0, "", 1, 0, 0, 0, 0, [0, 0, 0], 0, 0, 0, 0, 0, 0, 0], 104001, 102001, 1, [2, "1.0.0"], []]"""
    }

    "user/status/create #1" {
        post("user/status/create", """["$uid", "AZA"]""").res exp
            """[[$uid, "AZA", 1, 0, 0, 0, 0, [0, 0, 0], 0, 0, 0, 0, 0, 0, 0]]"""
    }

    "user/status/login Guest" {
        post("user/status/login", "[0]").res exp
            "[[], [], [], 0, [2077, 1, 1, 1, [], []], 0, []]"
    }

    "user/status/login #2" {
        post("user/status/login", "[$uid]").res exp
            "[[], [], [], 0, [2077, 1, 1, 1, [], []], null, []]"
    }
})