package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserTechEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserTechEventRepository")
public interface UserTechEventRepository extends JpaRepository<UserTechEvent, Long> {

    List<UserTechEvent> findByUser_Card_ExtId(long userId);

    Optional<UserTechEvent> findByUserAndEventId(UserData userData, int eventId);

    @Transactional
    void deleteByUser(UserData user);
}
