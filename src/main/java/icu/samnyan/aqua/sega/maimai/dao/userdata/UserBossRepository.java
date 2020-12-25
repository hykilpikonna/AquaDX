package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserBoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserBossRepository")
public interface UserBossRepository extends JpaRepository<UserBoss, Long> {

    Optional<UserBoss> findByUser_Card_ExtId(long userId);

}
