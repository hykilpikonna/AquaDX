package icu.samnyan.aqua.sega.ongeki.dao.gamedata;

import icu.samnyan.aqua.sega.ongeki.model.gamedata.GameReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiGameRewardRepository")
public interface GameRewardRepository extends JpaRepository<GameReward, Long> {
}
