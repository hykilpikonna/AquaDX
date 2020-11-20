package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserLoginBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface UserLoginBonusRepository extends JpaRepository<UserLoginBonus, Long> {

    List<UserLoginBonus> findByUser_Card_ExtId(int userId);

    Optional<UserLoginBonus> findByUserAndBonusId(UserData userData, int bonusId);

    @Transactional
    void deleteByUser(UserData user);
}
