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
    var auId: Int = 0,

    // Login credentials
    @Column(nullable = false, unique = true) var email: String = "",
    var pwHash: String = "",

    var displayName: String = "",

    // Country code at most 3 characters
    @Column(length = 3) var country: String = "",

    // Last login time
    var lastLogin: Long = 0L,

    // Registration time
    var regTime: Long = 0L,

    // Profile fields
    var profileLocation: String = "",
    var profileBio: String = "",

    // One user can have multiple cards
    @OneToMany(mappedBy = "aquaNetUser", cascade = [CascadeType.ALL])
    var cards: MutableList<Card> = mutableListOf()
) : Serializable

@Repository("AquaNetUserRepository")
interface AquaNetUserRepo : JpaRepository<AquaNetUser, Int> {
    fun existsByEmail(email: String): Boolean
}