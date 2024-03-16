package icu.samnyan.aqua.sega.maimai2.dao.userdata

import icu.samnyan.aqua.net.games.GenericUserDataRepo
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Repository("Maimai2UserDataRepository")
interface UserDataRepository : GenericUserDataRepo<UserDetail, Long> {
    override fun findByCard(card: Card): UserDetail?

    fun findByCardExtId(userId: Long): Optional<UserDetail>

    @Transactional
    fun deleteByCard(card: Card)

    @Query("select count(*) from Maimai2UserData where playerRating > :rating")
    override fun getRanking(rating: Int): Long
}
