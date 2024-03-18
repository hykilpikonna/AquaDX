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

    @SettingField("Unlock All Music",
        "Unlock all music and master difficulty in game")
    var unlockMusic: Boolean = false,

    @SettingField("Unlock All Chara",
        "Unlock all characters and partners in game")
    var unlockChara: Boolean = false,

    @SettingField("Unlock All Collectables",
        "Unlock all collectables (nameplate, title, icon, frame) in game. " +
        "This setting is not relevant in chusan because in-game user box is disabled in chusan.")
    var unlockCollectables: Boolean = false,

    @SettingField("Unlock All Tickets" ,
        "Unlock all map tickets (the game still limits which tickets can be used)")
    var unlockTickets: Boolean = false,
)

interface AquaGameOptionsRepository : JpaRepository<AquaGameOptions, Long>