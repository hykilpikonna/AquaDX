package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserOptionRepository")
public interface UserOptionRepository extends JpaRepository<UserOption, Long> {

    Optional<UserOption> findByUser(UserDetail user);

    Optional<UserOption> findByUser_Card_ExtId(Long extId);
}
