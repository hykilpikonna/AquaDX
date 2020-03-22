package icu.samnyan.aqua.sega.ongeki.dao.gamedata;

import icu.samnyan.aqua.sega.ongeki.model.gamedata.GamePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiGamePointRepository")
public interface GamePointRepository extends JpaRepository<GamePoint, Long> {
}
