package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserEventPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserEventPointRepository")
public interface UserEventPointRepository extends JpaRepository<UserEventPoint, Long> {
    Optional<UserEventPoint> findByUserAndEventId(UserData userData, int eventId);

    List<UserEventPoint> findByUser_Card_ExtId(int userId);
}
