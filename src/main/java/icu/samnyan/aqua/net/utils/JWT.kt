package icu.samnyan.aqua.net.utils

import ext.Str
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

        // Initialize key
        key = Keys.hmacShaKeyFor(props.secret.toByteArray())

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

    fun parse(token: Str): AquaNetUser? =
        userRepo.findByAuId(parser.parseSignedClaims(token).payload.subject.toLong())
}
