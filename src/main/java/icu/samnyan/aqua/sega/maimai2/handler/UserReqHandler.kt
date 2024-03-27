package icu.samnyan.aqua.sega.maimai2.handler

import ext.parsing
import icu.samnyan.aqua.sega.general.BaseHandler

fun interface UserReqHandler : BaseHandler {
    override fun handle(request: Map<String, Any>): Any {
        val userId = parsing { (request["userId"] as Number).toLong() }
        return handleThis(request, userId)
    }

    fun handleThis(request: Map<String, Any>, userId: Long): Any
}
