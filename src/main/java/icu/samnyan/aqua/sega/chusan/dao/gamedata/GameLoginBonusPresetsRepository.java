package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.GameLoginBonusPreset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameLoginBonusPresetsRepository extends JpaRepository<GameLoginBonusPreset, Integer> {
    @Query(value = "select * from chusan_game_login_bonus_preset where version = ?1 and is_enabled = ?2", nativeQuery = true)
    List<GameLoginBonusPreset> findLoginBonusPresets(int version, int isEnabled);
}
