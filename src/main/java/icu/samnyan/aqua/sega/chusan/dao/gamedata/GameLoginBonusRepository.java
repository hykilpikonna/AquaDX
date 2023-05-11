package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.GameLoginBonus;
import icu.samnyan.aqua.sega.chusan.model.gamedata.GameLoginBonusPreset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameLoginBonusRepository extends JpaRepository<GameLoginBonus, Integer> {
    @Query(value = "select * from chusan_game_login_bonus where version = ?1 and preset_id = ?2 order by need_login_day_count desc", nativeQuery = true)
    List<GameLoginBonus> findGameLoginBonus(int version, int presetId);

    @Query(value = "select * from chusan_game_login_bonus where version = ?1 and preset_id = ?2 and need_login_day_count = ?3 limit 1", nativeQuery = true)
    Optional<GameLoginBonus> findByRequiredDays(int version, int presetId, int requiredDays);
}
