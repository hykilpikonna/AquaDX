package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserExtendRepository")
public interface UserExtendRepository extends JpaRepository<UserExtend, Long> {

    Optional<UserExtend> findByUser(UserDetail user);

    Optional<UserExtend> findByUser_Card_ExtId(Long extId);
}
