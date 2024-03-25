package icu.samnyan.aqua.sega.wacca.model.db

import com.fasterxml.jackson.annotation.JsonIgnore
import icu.samnyan.aqua.net.games.BaseEntity
import jakarta.persistence.*

typealias UC = UniqueConstraint

/**
 * Base entity for all wacca user-related entities
 */
@MappedSuperclass
open class WaccaUserEntity : BaseEntity() {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    open var user: WaccaUser = WaccaUser()
}

/**
 * In-game option key-value storage
 */
@Entity @Table(name = "wacca_user_option", uniqueConstraints = [UC("", ["user_id", "opt_id"])])
class WcUserOption : WaccaUserEntity() {
    var optId = 0
    var value = 0
}

@Entity @Table(name = "wacca_user_bingo", uniqueConstraints = [UC("", ["user_id", "page_number"])])
class WcUserBingo : WaccaUserEntity() {
    var pageNumber = 0
    var pageProgress = ""
}

/**
 * The user here is the sender of the friend request.
 */
@Entity @Table(name = "wacca_friend", uniqueConstraints = [UC("", ["user_id", "with"])])
class WcUserFriend : WaccaUserEntity() {
    @ManyToOne @JoinColumn(name = "profile_reciever")
    var with: WaccaUser = WaccaUser()
    var isAccepted = false
}

@Entity @Table(name = "wacca_user_favorite_song", uniqueConstraints = [UC("", ["user_id", "song_id"])])
class WcUserFavoriteSong : WaccaUserEntity() {
    // TODO: Make this into a list instead?
    var songId = 0
}

@Entity @Table(name = "wacca_user_gate", uniqueConstraints = [UC("", ["user_id", "gate_id"])])
class WcUserGate : WaccaUserEntity() {
    var gateId = 0
    var page = 0
    var progress = 0
    var loops = 0
    var missionFlag = 0
    var totalPoints = 0
}

@Entity @Table(name = "wacca_user_item", uniqueConstraints = [UC("", ["user_id", "item_id", "type"])])
class WcUserItem : WaccaUserEntity() {
    var itemId = 0
    var type = 0
    var acquireDate = ""
    var useCount = 0
}

@Entity @Table(name = "wacca_user_ticket", uniqueConstraints = [UC("", ["user_id", "ticket_id"])])
class WcUserTicket : WaccaUserEntity() {
    var ticketId = 0
    var acquireDate = ""
    var expireDate = ""
}

@Entity @Table(name = "wacca_user_song_unlock", uniqueConstraints = [UC("", ["user_id", "song_id"])])
class WcUserSongUnlock : WaccaUserEntity() {
    var songId = 0
    var highestDifficulty = 0
    var acquireDate = ""
}

@Entity @Table(name = "wacca_user_trophy", uniqueConstraints = [UC("", ["user_id", "trophy_id", "season"])])
class WcUserTrophy : WaccaUserEntity() {
    var trophyId = 0
    var season = 0
    var progress = 0
    var badgeType = 0
}

@Entity @Table(name = "wacca_user_score", uniqueConstraints = [UC("", ["user_id", "song_id", "chart_id"])])
class WcUserScore : WaccaUserEntity() {
    var songId = 0
    var chartId = 0
    var score = 0
    var playCt = 0
    var clearCt = 0
    var misslessCt = 0
    var fullcomboCt = 0
    var allmarvCt = 0
    var gradeDCt = 0
    var gradeCCt = 0
    var gradeBCt = 0
    var gradeACt = 0
    var gradeAACt = 0
    var gradeAAACt = 0
    var gradeSCt = 0
    var gradeSSCt = 0
    var gradeSSSCt = 0
    var gradeMasterCt = 0
    var gradeSpCt = 0
    var gradeSspCt = 0
    var gradeSsspCt = 0
    var bestCombo = 0
    var lowestMissCt = 0
    var rating = 0
}

@Entity @Table(name = "wacca_user_playlog", uniqueConstraints = [UC("", ["user_id", "song_id", "chart_id", "date_scored"])])
class WcUserPlayLog : WaccaUserEntity() {
    var songId = 0
    var chartId = 0
    var score = 0
    var clear = 0
    var grade = 0
    var maxCombo = 0
    var marvCt = 0
    var greatCt = 0
    var goodCt = 0
    var missCt = 0
    var fastCt = 0
    var lateCt = 0
    var season = 0
    var dateScored = ""
}

@Entity @Table(name = "wacca_user_stageup", uniqueConstraints = [UC("", ["user_id", "stage_id"])])
class WcUserStageUp : WaccaUserEntity() {
    var version = 0
    var stageId = 0
    var clearStatus = 0
    var clearSongCt = 0
    var song1Score = 0
    var song2Score = 0
    var song3Score = 0
    var playCt = 0
}
