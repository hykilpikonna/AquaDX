package icu.samnyan.aqua.sega.allnet

import icu.samnyan.aqua.net.db.AquaNetUser
import jakarta.persistence.*
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.security.SecureRandom

/**
 * This is a one-to-many mapping of keychip to session token.
 */
@Entity
@Table(name = "allnet_keychip_sessions", indexes = [
    Index(name = "idx_last_use", columnList = "lastUse")
])
class KeychipSession(
    @ManyToOne
    @JoinColumn(name = "au_id")
    var user: AquaNetUser = AquaNetUser(),

    @Id
    @Column(length = 32)
    val token: String = genUrlSafeToken(32),

    @Column(nullable = false)
    val lastUse: Long = System.currentTimeMillis()
)


val urlSafeChars = ('a'..'z') + ('A'..'Z') + ('0'..'9') + listOf('-', '_', '.', '~')

fun genUrlSafeToken(length: Int): String {
    val random = SecureRandom()
    return (1..length)
        .map { urlSafeChars[random.nextInt(urlSafeChars.size)] }
        .joinToString("")
}

@Repository("KeychipSessionRepo")
interface KeychipSessionRepo : JpaRepository<KeychipSession, String> {
    fun findByToken(token: String): KeychipSession?
    fun deleteAllByLastUseBefore(expire: Long)
}

/**
 * Service to regularly delete unused keychip sessions.
 */
@Service
class KeychipSessionService(
    val keychipSessionRepo: KeychipSessionRepo,
    val props: AllNetProps
) {
    val logger = LoggerFactory.getLogger(KeychipSessionService::class.java)

    @Scheduled(fixedDelayString = "\${allnet.server.keychip-ses-clean-interval}")
    fun cleanup() {
        logger.info("!!! Keychip session cleanup !!!")
        val expire = System.currentTimeMillis() - props.keychipSesExpire
        keychipSessionRepo.deleteAllByLastUseBefore(expire)
    }

    fun new(user: AquaNetUser): KeychipSession {
        val session = KeychipSession(user = user)
        return keychipSessionRepo.save(session)
    }
}