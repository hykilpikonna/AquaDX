package icu.samnyan.aqua.net.db

import com.fasterxml.jackson.annotation.JsonIgnore
import ext.SettingField
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository

@Entity
class AquaGameOptions(
    @Id @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @SettingField("mai2")
    var unlockMusic: Boolean = false,

    @SettingField("mai2")
    var unlockChara: Boolean = false,

    @SettingField("mai2")
    var unlockCollectables: Boolean = false,

    @SettingField("mai2")
    var unlockTickets: Boolean = false,

    @SettingField("wacca")
    var waccaInfiniteWp: Boolean = false,

    @SettingField("wacca")
    var waccaAlwaysVip: Boolean = false,
)

interface AquaGameOptionsRepo : JpaRepository<AquaGameOptions, Long>
