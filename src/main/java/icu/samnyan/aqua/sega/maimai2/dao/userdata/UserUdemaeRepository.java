package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserUdemae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserUdemaeRepository")
public interface UserUdemaeRepository extends JpaRepository<UserUdemae, Long> {

    Optional<UserUdemae> findByUser(UserDetail user);

    Optional<UserUdemae> findByUser_Card_ExtId(Long extId);
}
