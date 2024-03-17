@file:Suppress("FunctionName")

package icu.samnyan.aqua.sega.maimai2.model

import icu.samnyan.aqua.net.games.GenericPlaylogRepo
import icu.samnyan.aqua.net.games.GenericUserDataRepo
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameCharge
import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameEvent
import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameSellingCard
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@NoRepositoryBean
interface UserLinked<T>: JpaRepository<T, Long> {
    fun findByUser(user: UserDetail): Optional<T>
    fun findByUser_Card_ExtId(userId: Long): List<T>
    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<T>
    fun findSingleByUser_Card_ExtId(userId: Long): Optional<T>
    @Transactional
    fun deleteByUser(user: UserDetail)
}

@Repository("Maimai2MapEncountNpcRepository")
interface Mai2MapEncountNpcRepo : UserLinked<MapEncountNpc>

@Repository("Maimai2UserActRepository")
interface Mai2UserActRepo : UserLinked<UserAct> {
    fun findByUserAndKindAndActivityId(user: UserDetail, kind: Int, id: Int): Optional<UserAct>

    fun findByUser_Card_ExtIdAndKind(userId: Long, kind: Int): List<UserAct>
}

@Repository("Maimai2UserCardRepository")
interface Mai2UserCardRepo : UserLinked<UserCard> {
    fun findByUserAndCardId(user: UserDetail, cardId: Int): Optional<UserCard>
}

@Repository("Maimai2UserCharacterRepository")
interface Mai2UserCharacterRepo : UserLinked<UserCharacter> {
    fun findByUserAndCharacterId(user: UserDetail, characterId: Int): Optional<UserCharacter>
}

@Repository("Maimai2UserChargeRepository")
interface UserChargeRepository : UserLinked<UserCharge>

@Repository("Maimai2UserCourseRepository")
interface UserCourseRepository : UserLinked<UserCourse> {
    fun findByUserAndCourseId(user: UserDetail, courseId: Int): Optional<UserCourse>
}

@Repository("Maimai2UserDataRepository")
interface UserDataRepository : GenericUserDataRepo<UserDetail> {
    fun findByCardExtId(userId: Long): Optional<UserDetail>

    @Transactional
    fun deleteByCard(card: Card)
}

@Repository("Maimai2UserExtendRepository")
interface UserExtendRepository : UserLinked<UserExtend>

@Repository("Maimai2UserFavoriteRepository")
interface UserFavoriteRepository : UserLinked<UserFavorite> {
    fun findByUserAndItemKind(user: UserDetail, kind: Int): Optional<UserFavorite>

    fun findByUserIdAndItemKind(userId: Long, kind: Int): List<UserFavorite>
}

@Repository("Maimai2UserFriendSeasonRankingRepository")
interface UserFriendSeasonRankingRepository : UserLinked<UserFriendSeasonRanking> {
    fun findByUserAndSeasonId(user: UserDetail, seasonId: Int): Optional<UserFriendSeasonRanking>
}

@Repository("Maimai2UserGeneralDataRepository")
interface UserGeneralDataRepository : UserLinked<UserGeneralData> {
    fun findByUserAndPropertyKey(user: UserDetail, key: String): Optional<UserGeneralData>

    fun findByUser_Card_ExtIdAndPropertyKey(userId: Long, key: String): Optional<UserGeneralData>
}

@Repository("Maimai2UserItemRepository")
interface UserItemRepository : UserLinked<UserItem> {
    fun findByUserAndItemKindAndItemId(user: UserDetail, itemKind: Int, itemId: Int): Optional<UserItem>

    fun findByUser_Card_ExtIdAndItemKind(userId: Long, kind: Int, page: Pageable): Page<UserItem>
}

@Repository("Maimai2UserLoginBonusRepository")
interface UserLoginBonusRepository : UserLinked<UserLoginBonus> {
    fun findByUserAndBonusId(user: UserDetail, bonusId: Int): Optional<UserLoginBonus>
}

@Repository("Maimai2UserMapRepository")
interface UserMapRepository : UserLinked<UserMap> {
    fun findByUserAndMapId(user: UserDetail, mapId: Int): Optional<UserMap>
}

@Repository("Maimai2UserMusicDetailRepository")
interface UserMusicDetailRepository : UserLinked<UserMusicDetail> {
    fun findByUser_Card_ExtIdAndMusicId(userId: Long, id: Int): List<UserMusicDetail>

    fun findByUserAndMusicIdAndLevel(user: UserDetail, musicId: Int, level: Int): Optional<UserMusicDetail>
}

@Repository("Maimai2UserOptionRepository")
interface UserOptionRepository : UserLinked<UserOption> {
}

@Repository("Maimai2UserPlaylogRepository")
interface UserPlaylogRepository : GenericPlaylogRepo<UserPlaylog>, UserLinked<UserPlaylog> {
    fun findByUser_Card_ExtIdAndMusicIdAndLevel(userId: Long, musicId: Int, level: Int): List<UserPlaylog>
}

@Repository("Maimai2UserPrintDetailRepository")
interface UserPrintDetailRepository : JpaRepository<UserPrintDetail, Long>

@Repository("Maimai2UserUdemaeRepository")
interface UserUdemaeRepository : UserLinked<UserUdemae>

@Repository("Maimai2GameChargeRepository")
interface GameChargeRepository : JpaRepository<GameCharge, Long>

@Repository("Maimai2GameEventRepository")
interface GameEventRepository : JpaRepository<GameEvent, Int> {
    fun findByTypeAndEnable(type: Int, enable: Boolean): List<GameEvent>
}

@Repository("Maimai2GameSellingCardRepository")
interface GameSellingCardRepository : JpaRepository<GameSellingCard, Long>