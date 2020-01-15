package icu.samnyan.aqua.sega.chunithm.dao.gamedata;

import icu.samnyan.aqua.sega.chunithm.model.gamedata.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Integer> {
}
