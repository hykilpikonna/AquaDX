package icu.samnyan.aqua.sega.maimai2.model.userdata

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import ext.Str
import icu.samnyan.aqua.net.utils.IGenericUserData
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.maimai2.util.IntegerListConverter
import icu.samnyan.aqua.sega.util.jackson.AccessCodeSerializer
import jakarta.persistence.*
import java.io.Serializable

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Entity(name = "Maimai2UserData")
@Table(name = "maimai2_user_detail")
class UserDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var id: Long = 0,

    @JsonSerialize(using = AccessCodeSerializer::class)
    @JsonProperty(value = "accessCode", access = JsonProperty.Access.READ_ONLY)
    @OneToOne
    @JoinColumn(name = "aime_card_id")
    var card: Card? = null,

    override var userName: String = "",

    @JsonInclude
    @Transient
    var friendCode: Str = "",
    var isNetMember: Int = 0,

    @JsonInclude
    @Transient
    var nameplateId: Int = 0,
    override var iconId: Int = 0,

    @JsonInclude
    @Transient
    var trophyId: Int = 0,
    var plateId: Int = 0,
    var titleId: Int = 0,
    var partnerId: Int = 0,
    var frameId: Int = 0,
    var selectMapId: Int = 0,
    var totalAwake: Int = 0,
    var gradeRating: Int = 0,
    var musicRating: Int = 0,
    override var playerRating: Int = 0,
    override var highestRating: Int = 0,
    var gradeRank: Int = 0,
    var classRank: Int = 0,
    var courseRank: Int = 0,

    @Convert(converter = IntegerListConverter::class)
    var charaSlot: List<Int>? = null, // Entries: 5

    @Convert(converter = IntegerListConverter::class)
    var charaLockSlot: List<Int>? = null, // Entries: 5

    var contentBit: Long = 0,
    var playCount: Int = 0,
    var eventWatchedDate: String = "",
    var lastGameId: String = "",
    override var lastRomVersion: String = "",
    var lastDataVersion: String = "",
    var lastLoginDate: String = "",
    override var lastPlayDate: String = "",
    var lastPlayCredit: Int = 0,
    var lastPlayMode: Int = 0,
    var lastPlaceId: Int = 0,
    var lastPlaceName: String = "",
    var lastAllNetId: Int = 0,
    var lastRegionId: Int = 0,
    var lastRegionName: String = "",
    var lastClientId: String = "",
    var lastCountryCode: String = "",
    var lastSelectEMoney: Int = 0,
    var lastSelectTicket: Int = 0,
    var lastSelectCourse: Int = 0,
    var lastCountCourse: Int = 0,
    var firstGameId: String = "",
    var firstRomVersion: String = "",
    var firstDataVersion: String = "",
    override var firstPlayDate: String = "",
    var compatibleCmVersion: String = "",
    var dailyBonusDate: String = "",
    var dailyCourseBonusDate: String = "",
    var lastPairLoginDate: String = "",
    var lastTrialPlayDate: String = "",
    var playVsCount: Int = 0,
    var playSyncCount: Int = 0,
    var winCount: Int = 0,
    var helpCount: Int = 0,
    var comboCount: Int = 0,
    var totalDeluxscore: Long = 0,
    var totalBasicDeluxscore: Long = 0,
    var totalAdvancedDeluxscore: Long = 0,
    var totalExpertDeluxscore: Long = 0,
    var totalMasterDeluxscore: Long = 0,
    var totalReMasterDeluxscore: Long = 0,

    @JsonInclude
    @Transient
    var totalHiscore: Long = 0,

    @JsonInclude
    @Transient
    var totalBasicHighscore: Long = 0,

    @JsonInclude
    @Transient
    var totalAdvancedHighscore: Long = 0,

    @JsonInclude
    @Transient
    var totalExpertHighscore: Long = 0,

    @JsonInclude
    @Transient
    var totalMasterHighscore: Long = 0,

    @JsonInclude
    @Transient
    var totalReMasterHighscore: Long = 0,
    var totalSync: Int = 0,
    var totalBasicSync: Int = 0,
    var totalAdvancedSync: Int = 0,
    var totalExpertSync: Int = 0,
    var totalMasterSync: Int = 0,
    var totalReMasterSync: Int = 0,
    var totalAchievement: Long = 0,
    var totalBasicAchievement: Long = 0,
    var totalAdvancedAchievement: Long = 0,
    var totalExpertAchievement: Long = 0,
    var totalMasterAchievement: Long = 0,
    var totalReMasterAchievement: Long = 0,
    var playerOldRating: Long = 0,
    var playerNewRating: Long = 0,
    var banState: Int = 0,
    var dateTime: Long = 0,

    @JsonInclude
    @Transient
    var cmLastEmoneyBrand: Int = 2,

    @JsonInclude
    @Transient
    var cmLastEmoneyCredit: Int = 69,
    var mapStock: Int = 0,
) : Serializable, IGenericUserData {
    override val totalScore: Long
        get() = totalDeluxscore
}
