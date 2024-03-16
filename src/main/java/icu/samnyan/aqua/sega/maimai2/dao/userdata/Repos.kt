package icu.samnyan.aqua.sega.maimai2.dao.userdata

import icu.samnyan.aqua.net.games.GenericPlaylogRepo
import icu.samnyan.aqua.net.games.GenericUserDataRepo
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@NoRepositoryBean
interface Deletable {
    @Transactional
    fun deleteByUser(user: UserDetail)
}

@Repository("Maimai2MapEncountNpcRepository")
interface MapEncountNpcRepository : JpaRepository<MapEncountNpc, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<MapEncountNpc>

    fun findByUserAndMusicId(user: UserDetail, musicId: Int): Optional<MapEncountNpc>

    fun findByUser_Card_ExtIdAndMusicId(userId: Long, musicId: Int, page: Pageable): Page<MapEncountNpc>
}

@Repository("Maimai2UserActRepository")
interface UserActRepository : JpaRepository<UserAct, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserAct>

    fun findByUserAndKindAndActivityId(user: UserDetail, kind: Int, id: Int): Optional<UserAct>

    fun findByUser_Card_ExtIdAndKind(userId: Long, kind: Int): List<UserAct>
}

@Repository("Maimai2UserCardRepository")
interface UserCardRepository : JpaRepository<UserCard, Long>, Deletable {
    fun findByUserAndCardId(user: UserDetail, cardId: Int): Optional<UserCard>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserCard>
}

@Repository("Maimai2UserCharacterRepository")
interface UserCharacterRepository : JpaRepository<UserCharacter, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserCharacter>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserCharacter>

    fun findByUserAndCharacterId(user: UserDetail, characterId: Int): Optional<UserCharacter>

}

@Repository("Maimai2UserChargeRepository")
interface UserChargeRepository : JpaRepository<UserCharge, Long>, Deletable {
    fun findByUser_Card_ExtId(extId: Long): List<UserCharge>

    fun findByUserAndChargeId(extId: UserDetail, chargeId: Int): Optional<UserCharge>

}

@Repository("Maimai2UserCourseRepository")
interface UserCourseRepository : JpaRepository<UserCourse, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserCourse>

    fun findByUserAndCourseId(user: UserDetail, courseId: Int): Optional<UserCourse>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserCourse>
}

@Repository("Maimai2UserDataRepository")
interface UserDataRepository : GenericUserDataRepo<UserDetail> {
    fun findByCardExtId(userId: Long): Optional<UserDetail>

    @Transactional
    fun deleteByCard(card: Card)
}

@Repository("Maimai2UserExtendRepository")
interface UserExtendRepository : JpaRepository<UserExtend, Long>, Deletable {
    fun findByUser(user: UserDetail): Optional<UserExtend>

    fun findByUser_Card_ExtId(extId: Long): Optional<UserExtend>

}

@Repository("Maimai2UserFavoriteRepository")
interface UserFavoriteRepository : JpaRepository<UserFavorite, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserFavorite>

    fun findByUserAndItemKind(user: UserDetail, kind: Int): Optional<UserFavorite>

    //Optional<UserFavorite> findByUserIdAndItemKind(long userId, int kind);
    fun findByUserIdAndItemKind(userId: Long, kind: Int): List<UserFavorite>

}

@Repository("Maimai2UserFriendSeasonRankingRepository")
interface UserFriendSeasonRankingRepository : JpaRepository<UserFriendSeasonRanking, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserFriendSeasonRanking>

    fun findByUserAndSeasonId(user: UserDetail, seasonId: Int): Optional<UserFriendSeasonRanking>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserFriendSeasonRanking>

}

@Repository("Maimai2UserGeneralDataRepository")
interface UserGeneralDataRepository : JpaRepository<UserGeneralData, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserGeneralData>

    fun findByUserAndPropertyKey(user: UserDetail, key: String): Optional<UserGeneralData>

    fun findByUser_Card_ExtIdAndPropertyKey(userId: Long, key: String): Optional<UserGeneralData>

}

@Repository("Maimai2UserItemRepository")
interface UserItemRepository : JpaRepository<UserItem, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserItem>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserItem>

    fun findByUserAndItemKindAndItemId(user: UserDetail, itemKind: Int, itemId: Int): Optional<UserItem>

    fun findByUser_Card_ExtIdAndItemKind(userId: Long, kind: Int, page: Pageable): Page<UserItem>

}

@Repository("Maimai2UserLoginBonusRepository")
interface UserLoginBonusRepository : JpaRepository<UserLoginBonus, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserLoginBonus>

    fun findByUserAndBonusId(user: UserDetail, bonusId: Int): Optional<UserLoginBonus>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserLoginBonus>

}

@Repository("Maimai2UserMapRepository")
interface UserMapRepository : JpaRepository<UserMap, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserMap>

    fun findByUserAndMapId(user: UserDetail, mapId: Int): Optional<UserMap>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserMap>

}

@Repository("Maimai2UserMusicDetailRepository")
interface UserMusicDetailRepository : JpaRepository<UserMusicDetail, Long>, Deletable {
    fun findByUser_Card_ExtId(userId: Long): List<UserMusicDetail>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserMusicDetail>

    fun findByUser_Card_ExtIdAndMusicId(userId: Long, id: Int): List<UserMusicDetail>

    fun findByUserAndMusicIdAndLevel(user: UserDetail, musicId: Int, level: Int): Optional<UserMusicDetail>

}

@Repository("Maimai2UserOptionRepository")
interface UserOptionRepository : JpaRepository<UserOption, Long>, Deletable {
    fun findByUser(user: UserDetail): Optional<UserOption>

    fun findByUser_Card_ExtId(extId: Long): Optional<UserOption>

}

@Repository("Maimai2UserPlaylogRepository")
interface UserPlaylogRepository : GenericPlaylogRepo<UserPlaylog>, Deletable {
    fun findByUserCardExtId(userId: Long, page: Pageable): Page<UserPlaylog>

    fun findByUser_Card_ExtIdAndMusicIdAndLevel(userId: Long, musicId: Int, level: Int): List<UserPlaylog>

}

@Repository("Maimai2UserPrintDetailRepository")
interface UserPrintDetailRepository : JpaRepository<UserPrintDetail, Long>

@Repository("Maimai2UserUdemaeRepository")
interface UserUdemaeRepository : JpaRepository<UserUdemae, Long>, Deletable {
    fun findByUser(user: UserDetail): Optional<UserUdemae>

    fun findByUser_Card_ExtId(extId: Long): Optional<UserUdemae>

}
