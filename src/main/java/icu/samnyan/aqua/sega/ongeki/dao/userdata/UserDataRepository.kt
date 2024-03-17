package icu.samnyan.aqua.sega.ongeki.dao.userdata

import icu.samnyan.aqua.net.games.GenericUserDataRepo
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserDataRepository")
interface UserDataRepository : GenericUserDataRepo<UserData> {
    fun findByCard_ExtIdIn(userIds: Collection<Long>): List<UserData>

    @Transactional
    fun deleteByCard(card: Card)
}
