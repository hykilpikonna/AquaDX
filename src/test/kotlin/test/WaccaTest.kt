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

    beforeTest {
        if (uid == 0L) uid = registerUser()
    }

    "housing/get" {
        post("housing/get", "[]").res shouldBe "[39, 0]".jsonArray()
    }
})