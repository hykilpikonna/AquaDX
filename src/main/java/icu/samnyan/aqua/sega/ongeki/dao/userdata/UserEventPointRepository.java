package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserEventPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserEventPointRepository")
public interface UserEventPointRepository extends JpaRepository<UserEventPoint, Long> {

    List<UserEventPoint> findByUser_Card_ExtId(long userId);

    Optional<UserEventPoint> findByUserAndEventId(UserData userData, int eventId);

    @Transactional
    void deleteByUser(UserData user);

    @Query(value = "SELECT rank from (SELECT user_id , DENSE_RANK() OVER (ORDER BY point DESC) as rank from ongeki_user_event_point where event_id = :eventId) where user_id == :userId limit 1", nativeQuery = true)
    int calculateRankByUserAndEventId(long userId, int eventId);
}
