package icu.samnyan.aqua.sega.maimai2.dao.gamedata;

import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2GameEventRepository")
public interface GameEventRepository extends JpaRepository<GameEvent, Integer> {

    List<GameEvent> findByTypeAndEnable(int type, boolean enable);
}
