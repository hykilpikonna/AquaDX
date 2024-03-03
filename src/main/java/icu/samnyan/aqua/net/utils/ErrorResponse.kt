package icu.samnyan.aqua.net.utils

import ext.Str
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


val SUCCESS = ResponseEntity.ok().body(mapOf("status" to "ok"))

class ApiException(val code: Int, message: Str) : RuntimeException(message) {
    companion object {
        val log = LoggerFactory.getLogger(ApiException::class.java)
    }
}

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ApiException::class)
    fun handleCustomApiException(e: ApiException): ResponseEntity<Any?> {
        // On error, return the error code and message
        return ResponseEntity.status(e.code).body(e.message)
    }
}