package icu.samnyan.aqua.sega.chusan.dao.userdata

import icu.samnyan.aqua.sega.chusan.model.userdata.UserData
import icu.samnyan.aqua.sega.chusan.model.userdata.UserGeneralData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserGeneralDataRepository")
interface UserGeneralDataRepository : JpaRepository<UserGeneralData, Long> {
    fun findByUserAndPropertyKey(user: UserData, key: String): Optional<UserGeneralData>

    fun findByUser_Card_ExtIdAndPropertyKey(extId: Long, key: String): Optional<UserGeneralData>

    fun findByUser_Card_ExtId(extId: Long): List<UserGeneralData>
}
