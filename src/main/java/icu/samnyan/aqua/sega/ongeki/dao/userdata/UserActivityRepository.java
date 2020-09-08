package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserActivity;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserActivityRepository")
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    List<UserActivity> findByUser_Card_ExtId(int userId);

    Optional<UserActivity> findByUserAndKindAndActivityId(UserData userData, int kind, int activityId);

    List<UserActivity> findByUser_Card_ExtIdAndKindOrderBySortNumberDesc(int userId, int kind);

    @Transactional
    void deleteByUser(UserData user);
}
