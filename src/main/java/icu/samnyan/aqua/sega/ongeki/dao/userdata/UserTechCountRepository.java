package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserTechCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserTechCountRepository")
public interface UserTechCountRepository extends JpaRepository<UserTechCount, Long> {

    List<UserTechCount> findByUser_Card_ExtId(long userId);

    Optional<UserTechCount> findByUserAndLevelId(UserData user, int levelId);

    @Transactional
    void deleteByUser(UserData user);

}
