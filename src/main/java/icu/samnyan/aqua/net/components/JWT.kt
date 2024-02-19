package icu.samnyan.aqua.net.components

import ext.Str
import ext.minus
import icu.samnyan.aqua.net.db.AquaNetUser
import icu.samnyan.aqua.net.db.AquaNetUserRepo
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Configuration
@ConfigurationProperties(prefix = "aqua-net.jwt")
class JWTProperties {
    var secret: Str = "Open Sesame!"
}

@Service
class JWT(
    val props: JWTProperties,
    val userRepo: AquaNetUserRepo
) {
    val log = LoggerFactory.getLogger(JWT::class.java)!!
    lateinit var key: SecretKey
    lateinit var parser: JwtParser

    @PostConstruct
    fun onLoad() {
        // Check secret
        if (props.secret == "Open Sesame!") {
            log.warn("USING DEFAULT JWT SECRET, PLEASE SET aqua-net.jwt IN CONFIGURATION")
        }

        // Pad byte array to 256 bits
        var ba = props.secret.toByteArray()
        if (ba.size < 32) {
            log.warn("JWT Secret is less than 256 bits, padding with 0. PLEASE USE A STRONGER SECRET!")
            ba = ByteArray(32).also { ba.copyInto(it) }
        }

        // Initialize key
        key = Keys.hmacShaKeyFor(ba)

        // Create parser
        parser = Jwts.parser()
            .verifyWith(key)
            .build()

        log.info("JWT Service Enabled")
    }


    fun gen(user: AquaNetUser): Str = Jwts.builder().header()
        .keyId("aqua-net")
        .and()
        .subject(user.auId.toString())
        .issuedAt(Date())
        .signWith(key)
        .compact()

    fun parse(token: Str): AquaNetUser? = try {
        userRepo.findByAuId(parser.parseSignedClaims(token).payload.subject.toLong())
    } catch (e: Exception) {
        log.debug("Failed to parse JWT", e)
        null
    }

    fun auth(token: Str) = parse(token) ?: (400 - "Invalid token")
}