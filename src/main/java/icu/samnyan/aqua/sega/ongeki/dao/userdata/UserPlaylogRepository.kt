package icu.samnyan.aqua.sega.ongeki.dao.userdata

import icu.samnyan.aqua.net.games.GenericPlaylogRepo
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserPlaylog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserPlaylogRepository")
interface UserPlaylogRepository : GenericPlaylogRepo<UserPlaylog> {
    fun findByUser_Card_ExtId(userId: Long): List<UserPlaylog>

    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<UserPlaylog>

    fun findByUser_Card_ExtIdAndMusicIdAndLevel(userId: Long, musicId: Int, level: Int): List<UserPlaylog>

    @Transactional
    fun deleteByUser(user: UserData)
}
