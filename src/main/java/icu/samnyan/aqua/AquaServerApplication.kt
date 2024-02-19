package icu.samnyan.aqua

import icu.samnyan.aqua.net.components.EmailService
import icu.samnyan.aqua.sega.aimedb.AimeDbServer
import icu.samnyan.aqua.spring.util.AutoChecker
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.File

@SpringBootApplication
class AquaServerApplication

/**
 * Main method, entry point of the application
 */
fun main(args: Array<String>) {
    // If data/ is not found, create it
    File("data").mkdirs()

    // Run the application
    val ctx = SpringApplication.run(AquaServerApplication::class.java, *args)

    // Start the AimeDbServer
    val aimeDbServer = ctx.getBean(AimeDbServer::class.java)
    aimeDbServer.start()

    // Start the AutoChecker
    val checker = ctx.getBean(AutoChecker::class.java)
    checker.check()

    // Test the email service
    val emailService = ctx.getBean(EmailService::class.java)
    emailService.testConnection()
}