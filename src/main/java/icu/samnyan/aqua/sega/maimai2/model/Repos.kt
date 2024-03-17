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

interface Mai2MapEncountNpcRepo : UserLinked<MapEncountNpc>

interface Mai2UserActRepo : UserLinked<UserAct> {
    fun findByUserAndKindAndActivityId(user: UserDetail, kind: Int, id: Int): Optional<UserAct>

    fun findByUser_Card_ExtIdAndKind(userId: Long, kind: Int): List<UserAct>
}

interface Mai2UserCardRepo : UserLinked<UserCard> {
    fun findByUserAndCardId(user: UserDetail, cardId: Int): Optional<UserCard>
}

interface Mai2UserCharacterRepo : UserLinked<UserCharacter> {
    fun findByUserAndCharacterId(user: UserDetail, characterId: Int): Optional<UserCharacter>
}

interface Mai2UserChargeRepo : UserLinked<UserCharge>

interface Mai2UserCourseRepo : UserLinked<UserCourse> {
    fun findByUserAndCourseId(user: UserDetail, courseId: Int): Optional<UserCourse>
}

interface Mai2UserDataRepo : GenericUserDataRepo<UserDetail> {
    fun findByCardExtId(userId: Long): Optional<UserDetail>

    @Transactional
    fun deleteByCard(card: Card)
}

interface Mai2UserExtendRepo : UserLinked<UserExtend>

interface Mai2UserFavoriteRepo : UserLinked<UserFavorite> {
    fun findByUserAndItemKind(user: UserDetail, kind: Int): Optional<UserFavorite>

    fun findByUserIdAndItemKind(userId: Long, kind: Int): List<UserFavorite>
}

interface Mai2UserFriendSeasonRankingRepo : UserLinked<UserFriendSeasonRanking> {
    fun findByUserAndSeasonId(user: UserDetail, seasonId: Int): Optional<UserFriendSeasonRanking>
}

interface Mai2UserGeneralDataRepo : UserLinked<UserGeneralData> {
    fun findByUserAndPropertyKey(user: UserDetail, key: String): Optional<UserGeneralData>

    fun findByUser_Card_ExtIdAndPropertyKey(userId: Long, key: String): Optional<UserGeneralData>
}

interface Mai2UserItemRepo : UserLinked<UserItem> {
    fun findByUserAndItemKindAndItemId(user: UserDetail, itemKind: Int, itemId: Int): Optional<UserItem>

    fun findByUser_Card_ExtIdAndItemKind(userId: Long, kind: Int, page: Pageable): Page<UserItem>
}

interface Mai2UserLoginBonusRepo : UserLinked<UserLoginBonus> {
    fun findByUserAndBonusId(user: UserDetail, bonusId: Int): Optional<UserLoginBonus>
}

interface Mai2UserMapRepo : UserLinked<UserMap> {
    fun findByUserAndMapId(user: UserDetail, mapId: Int): Optional<UserMap>
}

interface Mai2UserMusicDetailRepo : UserLinked<UserMusicDetail> {
    fun findByUser_Card_ExtIdAndMusicId(userId: Long, id: Int): List<UserMusicDetail>

    fun findByUserAndMusicIdAndLevel(user: UserDetail, musicId: Int, level: Int): Optional<UserMusicDetail>
}

interface Mai2UserOptionRepo : UserLinked<UserOption>

interface Mai2UserPlaylogRepo : GenericPlaylogRepo<UserPlaylog>, UserLinked<UserPlaylog> {
    fun findByUser_Card_ExtIdAndMusicIdAndLevel(userId: Long, musicId: Int, level: Int): List<UserPlaylog>
}

interface Mai2UserPrintDetailRepo : JpaRepository<UserPrintDetail, Long>

interface Mai2UserUdemaeRepo : UserLinked<UserUdemae>

interface Mai2GameChargeRepo : JpaRepository<GameCharge, Long>

interface Mai2GameEventRepo : JpaRepository<GameEvent, Int> {
    fun findByTypeAndEnable(type: Int, enable: Boolean): List<GameEvent>
}

interface Mai2GameSellingCardRepo : JpaRepository<GameSellingCard, Long>