package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserAct;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserActRepository")
public interface UserActRepository extends JpaRepository<UserAct, Long> {

    Optional<UserAct> findByUserAndKindAndActivityId(UserDetail user, int kind, int id);

    List<UserAct> findByUser_Card_ExtIdAndKind(long userId, int kind);
}
