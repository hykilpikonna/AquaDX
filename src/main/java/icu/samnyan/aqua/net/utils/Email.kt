package icu.samnyan.aqua.net.utils

import ext.Bool
import ext.Str
import icu.samnyan.aqua.net.db.AquaNetUser
import jakarta.annotation.PostConstruct
import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Service

@Configuration
@ConfigurationProperties(prefix = "aqua-net.email")
class EmailProperties {
    var enable: Bool = false

    lateinit var senderName: Str

    lateinit var senderAddr: Str
}

/**
 * Email service. All email related operations should be placed here.
 *
 * Library Documentation: https://www.simplejavamail.org/
 */
@Service
@Import(SimpleJavaMailSpringSupport::class)
class EmailService(
    val mailer: Mailer,
    val props: EmailProperties,
) {
    val log: Logger = LoggerFactory.getLogger(EmailService::class.java)

    /**
     * Test the connection of the email service on startup
     */
    @PostConstruct
    fun testConnection() {
        if (!props.enable) return

        try {
            mailer.testConnection()
            log.info("Email Service Connected")
        } catch (e: Exception) {
            log.error("Email Service Connection Failed", e)
        }
    }

    /**
     * Send a confirmation email to the user
     */
    fun confirmationEmail(user: AquaNetUser) {

    }

    fun testEmail(addr: Str, name: Str) {
        if (!props.enable) return

        log.info("Sending test email to $addr")
        mailer.sendMail(EmailBuilder.startingBlank()
            .from(props.senderName, props.senderAddr)
            .to(name, addr)
            .withSubject("Test Email")
            .withPlainText("This is a test email to check if AquaNet Email Works").buildEmail()).thenRun {
                log.info("Test email sent to $addr")

        }
    }
}