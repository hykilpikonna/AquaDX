package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserGeneralData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserGeneralDataRepository")
public interface UserGeneralDataRepository extends JpaRepository<UserGeneralData, Long> {

    List<UserGeneralData> findByUser_Card_ExtId(long userId);

    Optional<UserGeneralData> findByUserAndPropertyKey(UserDetail user, String key);

    Optional<UserGeneralData> findByUser_Card_ExtIdAndPropertyKey(long userId, String key);

    @Transactional
    void deleteByUser(UserDetail user);
}
