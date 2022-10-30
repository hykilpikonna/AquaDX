package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserBoss;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserBossRepository")
public interface UserBossRepository extends JpaRepository<UserBoss, Long> {

    List<UserBoss> findByUser_Card_ExtId(long userId);

    Optional<UserBoss> findByUserAndMusicId(UserData user, int musicId);

    @Transactional
    void deleteByUser(UserData user);

}
