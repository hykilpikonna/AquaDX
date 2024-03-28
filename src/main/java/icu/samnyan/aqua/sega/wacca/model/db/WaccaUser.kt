package icu.samnyan.aqua.sega.wacca.model.db

import ext.isoDate
import ext.ls
import ext.sec
import ext.toDate
import icu.samnyan.aqua.net.games.BaseEntity
import icu.samnyan.aqua.sega.general.IntegerListConverter
import icu.samnyan.aqua.sega.general.model.Card
import jakarta.persistence.*
import java.util.*

/**
 * General user information
 */
@Entity @Table(name = "wacca_user")
class WaccaUser : BaseEntity() {
    @OneToOne
    @JoinColumn(name = "aime_card_id", unique = true)
    var card: Card = Card()

    @Column(length = 8)
    var username = ""

    var xp = 0
    var wp = 500
    var wpTotal = 500
    var wpSpent = 0
    var danType = 0
    var danLevel = 0
    @Convert(converter = IntegerListConverter::class)
    var titles: MutableList<Int> = mutableListOf(0, 0, 0)
    var rating = 0
    @Temporal(TemporalType.TIMESTAMP)
    var vipExpireTime: Date = "2077-01-01".isoDate().toDate()
    var alwaysVip = false
    var loginCount = 0
    var loginCountDays = 0
    var loginCountDaysConsec = 0
    var loginCountToday = 0
    @Convert(converter = IntegerListConverter::class)
    var playCounts: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0)
    @Convert(converter = IntegerListConverter::class)
    var friendViews: MutableList<Int> = mutableListOf(0, 0, 0)
    @Column(length = 50)
    var lastGameVer = "1.0.0"
    @Convert(converter = IntegerListConverter::class)
    var lastSongInfo: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0)
    @Temporal(TemporalType.TIMESTAMP)
    var lastConsecDate: Date = Date(0)
    @Temporal(TemporalType.TIMESTAMP)
    var lastLoginDate: Date = Date()
    var gateTutorialFlags: String = "[[1, 0], [2, 0], [3, 0], [4, 0], [5, 0]]"
    @Convert(converter = IntegerListConverter::class)
    var favoriteSongs: MutableList<Int> = mutableListOf()

    fun lStatus() = ls(card.extId, username, 1, xp, danLevel, danType, wp, ls(0, 0, 0), loginCount, loginCountDays,
        (loginCount - 1).coerceAtLeast(0), loginCountDaysConsec, vipExpireTime.sec, loginCountToday, rating)
}