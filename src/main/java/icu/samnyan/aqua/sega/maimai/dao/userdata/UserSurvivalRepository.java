package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserSurvival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserSurvivalRepository")
public interface UserSurvivalRepository extends JpaRepository<UserSurvival, Long> {

    List<UserSurvival> findByUser_Card_ExtId(long userId);

}
