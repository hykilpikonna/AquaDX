package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameEventRepository")
public interface GameEventRepository extends JpaRepository<GameEvent, Integer> {

    List<GameEvent> findByEnable(boolean enable);
}
