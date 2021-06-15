package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserLoginBonus;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserLoginBonusRepository")
public interface UserLoginBonusRepository extends JpaRepository<UserLoginBonus, Long> {

    Optional<UserLoginBonus> findByUserAndBonusId(UserDetail user, int bonusId);

    Page<UserLoginBonus> findByUser_Card_ExtId(long userId, Pageable page);

}
