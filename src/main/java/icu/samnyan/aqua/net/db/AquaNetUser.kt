package icu.samnyan.aqua.net.db

import icu.samnyan.aqua.sega.general.model.Card
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.Serializable

@Entity(name = "AquaNetUser")
@Table(name = "aqua_net_user")
class AquaNetUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var auId: Long = 0,

    @Column(nullable = false, unique = true, length = 32)
    var username: String = "",

    // Login credentials
    @Column(nullable = false, unique = true)
    var email: String = "",
    @Column(nullable = false)
    var pwHash: String = "",

    @Column(nullable = true, length = 32)
    var displayName: String = "",

    // Country code at most 3 characters
    @Column(length = 3)
    var country: String = "",

    // Last login time
    var lastLogin: Long = 0L,

    // Registration time
    var regTime: Long = 0L,

    // Profile fields
    var profileLocation: String = "",
    var profileBio: String = "",

    // Email confirmation
    var emailConfirmed: Boolean = false,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "ghostCard", unique = true, nullable = false)
    var ghostCard: Card = Card(),

    // One user can have multiple cards
    @OneToMany(mappedBy = "aquaUser", cascade = [CascadeType.ALL])
    var cards: MutableList<Card> = mutableListOf()
) : Serializable {
    val computedName get() = displayName.ifEmpty { username }
}

@Repository("AquaNetUserRepository")
interface AquaNetUserRepo : JpaRepository<AquaNetUser, Long> {
    fun findByAuId(auId: Long): AquaNetUser?
    fun findByEmailIgnoreCase(email: String): AquaNetUser?
    fun findByUsernameIgnoreCase(username: String): AquaNetUser?
}