package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import icu.samnyan.aqua.sega.maimai.model.userdata.UserGeneralData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserGeneralDataRepository")
public interface UserGeneralDataRepository extends JpaRepository<UserGeneralData, Long> {
    Optional<UserGeneralData> findByUser_Card_ExtIdAndPropertyKey(long userId, String key);

    Optional<UserGeneralData> findByUserAndPropertyKey(UserData user, String key);

}
