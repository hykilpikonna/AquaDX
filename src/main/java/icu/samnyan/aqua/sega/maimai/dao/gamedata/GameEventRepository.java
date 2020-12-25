package icu.samnyan.aqua.sega.maimai.dao.gamedata;

import icu.samnyan.aqua.sega.maimai.model.gamedata.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiGameEventRepository")
public interface GameEventRepository extends JpaRepository<GameEvent, Long> {

    List<GameEvent> findByType(Integer type);

}
