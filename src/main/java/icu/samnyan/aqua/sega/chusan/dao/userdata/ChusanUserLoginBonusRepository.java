package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserLoginBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChusanUserLoginBonusRepository extends JpaRepository<UserLoginBonus, Integer>{
    @Query(value = "select * from chusan_user_login_bonus where user = ?1 and version = ?2 and is_finished = ?3 order by last_update_date desc", nativeQuery = true)
    List<UserLoginBonus> findAllLoginBonus(int user_id, int version, int is_finished);

    @Query(value = "select * from chusan_user_login_bonus where user = ?1 and version = ?2 and preset_id = ?3 limit 1", nativeQuery = true)
    Optional<UserLoginBonus> findLoginBonus(int user_id, int version, int preset_id);
}
