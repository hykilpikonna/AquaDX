package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserActivity;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserActivityRepository")
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Optional<UserActivity> findTopByUserAndActivityIdAndKindOrderByIdDesc(UserData user, int activityId, int kind);

    List<UserActivity> findAllByUser_Card_ExtIdAndKindOrderBySortNumberDesc(Long extId, int kind);

    List<UserActivity> findAllByUser_Card_ExtId(Long extId);
}
