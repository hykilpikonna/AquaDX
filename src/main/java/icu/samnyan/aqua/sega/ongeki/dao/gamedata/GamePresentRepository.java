package icu.samnyan.aqua.sega.ongeki.dao.gamedata;

import icu.samnyan.aqua.sega.ongeki.model.gamedata.GamePoint;
import icu.samnyan.aqua.sega.ongeki.model.gamedata.GamePresent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiGamePresentRepository")
public interface GamePresentRepository extends JpaRepository<GamePresent, Long> {
}
