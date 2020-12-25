package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserActivity;
import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserActivityRepository")
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Optional<UserActivity> findByUserAndKindAndActivityId(UserData user, int kind, int id);

    List<UserActivity> findByUser_Card_ExtIdAndKind(long userId, int kind);
}
