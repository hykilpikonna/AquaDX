package icu.samnyan.aqua.sega.wacca.model.db

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Component

interface WcUserRepo : JpaRepository<WaccaUser, Long> {
    fun findByCardExtId(extId: Long): WaccaUser?
}

@NoRepositoryBean
interface IWaccaUserLinked<T> : JpaRepository<T, Long> {
    fun findByUser(user: WaccaUser): List<T>
    fun findByUserCardExtId(userId: Long): List<T>
    @Transactional
    fun deleteByUser(user: WaccaUser)
}

interface WcUserOptionRepo : IWaccaUserLinked<WcUserOption>
interface WcUserBingoRepo : IWaccaUserLinked<WcUserBingo> {
    fun findByUserAndPageNumber(user: WaccaUser, pageNumber: Int): WcUserBingo?
}
interface WcUserFriendRepo : IWaccaUserLinked<WcUserFriend>
interface WcUserFavoriteSongRepo : IWaccaUserLinked<WcUserFavoriteSong>
interface WcUserGateRepo : IWaccaUserLinked<WcUserGate> {
    fun findByUserAndGateId(user: WaccaUser, gateId: Int): WcUserGate?
}
interface WcUserItemRepo : IWaccaUserLinked<WcUserItem>
interface WcUserBestScoreRepo : IWaccaUserLinked<WcUserScore> {
    fun findByUserAndSongIdAndDifficulty(user: WaccaUser, songId: Int, difficulty: Int): WcUserScore?
}
interface WcUserPlayLogRepo : IWaccaUserLinked<WcUserPlayLog>
interface WcUserStageUpRepo : IWaccaUserLinked<WcUserStageUp>

@Component
class WaccaRepos(
    val user: WcUserRepo,
    val option: WcUserOptionRepo,
    val bingo: WcUserBingoRepo,
    val friend: WcUserFriendRepo,
    val favoriteSong: WcUserFavoriteSongRepo,
    val gate: WcUserGateRepo,
    val item: WcUserItemRepo,
    val bestScore: WcUserBestScoreRepo,
    val playLog: WcUserPlayLogRepo,
    val stageUp: WcUserStageUpRepo
)