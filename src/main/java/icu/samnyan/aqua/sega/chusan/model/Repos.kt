@file:Suppress("FunctionName")

package icu.samnyan.aqua.sega.chusan.model

import icu.samnyan.aqua.net.games.GenericPlaylogRepo
import icu.samnyan.aqua.net.games.GenericUserDataRepo
import icu.samnyan.aqua.sega.chusan.model.gamedata.*
import icu.samnyan.aqua.sega.chusan.model.userdata.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import java.util.*


@NoRepositoryBean
interface UserLinked<T, ID> : JpaRepository<T, ID> {
    fun findByUser_Card_ExtId(extId: Long): List<T>
    fun findSingleByUser_Card_ExtId(extId: Long): Optional<T>
    fun findByUser_Card_ExtId(extId: Long, pageable: Pageable): Page<T>
    fun findByUser(user: UserData): List<T>
    fun findSingleByUser(user: UserData): Optional<T>
}


interface ChusanUserLoginBonusRepository : JpaRepository<UserLoginBonus, Int> {
    @Query(
        value = "select * from chusan_user_login_bonus where user = ?1 and version = ?2 and is_finished = ?3 order by last_update_date desc",
        nativeQuery = true
    )
    fun findAllLoginBonus(userId: Int, version: Int, isFinished: Int): List<UserLoginBonus>

    @Query(
        value = "select * from chusan_user_login_bonus where user = ?1 and version = ?2 and preset_id = ?3 limit 1",
        nativeQuery = true
    )
    fun findLoginBonus(userId: Int, version: Int, presetId: Int): Optional<UserLoginBonus>
}

@Repository("ChusanUserActivityRepository")
interface UserActivityRepository : UserLinked<UserActivity, Long> {
    fun findTopByUserAndActivityIdAndKindOrderByIdDesc(user: UserData, activityId: Int, kind: Int): Optional<UserActivity>

    fun findAllByUser_Card_ExtIdAndKindOrderBySortNumberDesc(extId: Long, kind: Int): List<UserActivity>
}

@Repository("ChusanUserCardPrintStateRepository")
interface UserCardPrintStateRepository : UserLinked<UserCardPrintState, Long> {
    fun findByUser_Card_ExtIdAndHasCompleted(extId: Long, hasCompleted: Boolean): List<UserCardPrintState>

    fun findByUserAndGachaIdAndHasCompleted(userData: UserData, gachaId: Int, hasCompleted: Boolean): List<UserCardPrintState>
}

@Repository("ChusanUserCharacterRepository")
interface UserCharacterRepository : UserLinked<UserCharacter, Long> {
    fun findTopByUserAndCharacterIdOrderByIdDesc(user: UserData, characterId: Int): Optional<UserCharacter>
}

@Repository("ChusanUserChargeRepository")
interface UserChargeRepository : UserLinked<UserCharge, Long> {
    fun findByUserAndChargeId(extId: UserData, chargeId: Int): Optional<UserCharge>
}

@Repository("ChusanUserCourseRepository")
interface UserCourseRepository : UserLinked<UserCourse, Long> {
    fun findTopByUserAndCourseIdOrderByIdDesc(user: UserData, courseId: Int): Optional<UserCourse>
}

@Repository("ChusanUserDataRepository")
interface UserDataRepository : GenericUserDataRepo<UserData>

@Repository("ChusanUserDuelRepository")
interface UserDuelRepository : UserLinked<UserDuel, Long> {
    fun findTopByUserAndDuelIdOrderByIdDesc(user: UserData, duelId: Int): Optional<UserDuel>
}

@Repository("ChusanUserGachaRepository")
interface UserGachaRepository : UserLinked<UserGacha, Long> {
    fun findByUserAndGachaId(extId: UserData, gachaId: Int): Optional<UserGacha>
}

@Repository("ChusanUserGameOptionRepository")
interface UserGameOptionRepository : UserLinked<UserGameOption, Long>

@Repository("ChusanUserGeneralDataRepository")
interface UserGeneralDataRepository : UserLinked<UserGeneralData, Long> {
    fun findByUserAndPropertyKey(user: UserData, key: String): Optional<UserGeneralData>

    fun findByUser_Card_ExtIdAndPropertyKey(extId: Long, key: String): Optional<UserGeneralData>
}

@Repository("ChusanUserItemRepository")
interface UserItemRepository : UserLinked<UserItem, Long> {
    fun findTopByUserAndItemIdAndItemKindOrderByIdDesc(
        user: UserData,
        itemId: Int,
        itemKind: Int
    ): Optional<UserItem>

    fun findAllByUser_Card_ExtIdAndItemKind(extId: Long, itemKind: Int, pageable: Pageable): Page<UserItem>

    fun findAllByUser_Card_ExtIdAndItemKind(extId: Long, itemKind: Int): List<UserItem>
}

@Repository
interface UserMapAreaRepository : UserLinked<UserMapArea, Long> {
    fun findTopByUserAndMapAreaIdOrderByIdDesc(user: UserData, mapAreaId: Int): Optional<UserMapArea>
}

@Repository("ChusanUserMusicDetailRepository")
interface UserMusicDetailRepository : UserLinked<UserMusicDetail, Long> {
    fun findTopByUserAndMusicIdAndLevelOrderByIdDesc(user: UserData, musicId: Int, level: Int): Optional<UserMusicDetail>

    fun findByUser_Card_ExtIdAndMusicId(extId: Long, musicId: Int): List<UserMusicDetail>
}

@Repository("ChusanUserPlaylogRepository")
interface UserPlaylogRepository : GenericPlaylogRepo<UserPlaylog> {
    fun findByUser_Card_ExtIdAndLevelNot(extId: Long, levelNot: Int, page: Pageable): List<UserPlaylog>

    fun findByUser_Card_ExtIdAndMusicIdAndLevel(extId: Long, musicId: Int, level: Int): List<UserPlaylog>
}

@Repository("ChusanGameAvatarAccRepository")
interface GameAvatarAccRepository : JpaRepository<AvatarAcc, Long>

@Repository("ChusanGameCharacterRepository")
interface GameCharacterRepository : JpaRepository<Character, Long>

@Repository("ChusanGameChargeRepository")
interface GameChargeRepository : JpaRepository<GameCharge, Long>

@Repository("ChusanGameEventRepository")
interface GameEventRepository : JpaRepository<GameEvent, Int> {
    fun findByEnable(enable: Boolean): List<GameEvent>
}

@Repository("ChusanGameFrameRepository")
interface GameFrameRepository : JpaRepository<Frame, Long>

@Repository("ChusanGameGachaCardRepository")
interface GameGachaCardRepository : JpaRepository<GameGachaCard, Long> {
    fun findAllByGachaId(gachaId: Int): List<GameGachaCard>
}

@Repository("ChusanGameGachaRepository")
interface GameGachaRepository : JpaRepository<GameGacha, Long>

interface GameLoginBonusPresetsRepository : JpaRepository<GameLoginBonusPreset, Int> {
    @Query(
        value = "select * from chusan_game_login_bonus_preset where version = ?1 and is_enabled = ?2",
        nativeQuery = true
    )
    fun findLoginBonusPresets(version: Int, isEnabled: Int): List<GameLoginBonusPreset>
}

interface GameLoginBonusRepository : JpaRepository<GameLoginBonus, Int> {
    @Query(
        value = "select * from chusan_game_login_bonus where version = ?1 and preset_id = ?2 order by need_login_day_count desc",
        nativeQuery = true
    )
    fun findGameLoginBonus(version: Int, presetId: Int): List<GameLoginBonus>

    @Query(
        value = "select * from chusan_game_login_bonus where version = ?1 and preset_id = ?2 and need_login_day_count = ?3 limit 1",
        nativeQuery = true
    )
    fun findByRequiredDays(version: Int, presetId: Int, requiredDays: Int): Optional<GameLoginBonus>
}

@Repository("ChusanGameMapIconRepository")
interface GameMapIconRepository : JpaRepository<MapIcon, Long>

@Repository("ChusanGameMusicRepository")
interface GameMusicRepository : JpaRepository<Music, Long> {
    fun findByMusicId(musicId: Int): Optional<Music>
}

@Repository("ChusanGameNamePlateRepository")
interface GameNamePlateRepository : JpaRepository<NamePlate, Long>

@Repository("ChusanGameSystemVoiceRepository")
interface GameSystemVoiceRepository : JpaRepository<SystemVoice, Long>

@Repository("ChusanGameTrophyRepository")
interface GameTrophyRepository : JpaRepository<Trophy, Long>