package icu.samnyan.aqua

import icu.samnyan.aqua.sega.aimedb.AimeDbServer
import icu.samnyan.aqua.spring.util.AutoChecker
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AquaServerApplication

/**
 * Main method, entry point of the application
 */
fun main(args: Array<String>) {
    val ctx = SpringApplication.run(AquaServerApplication::class.java, *args)

    val aimeDbServer = ctx.getBean(AimeDbServer::class.java)
    aimeDbServer.start()

    val checker = ctx.getBean(AutoChecker::class.java)
    checker.check()
}