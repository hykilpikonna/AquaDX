package icu.samnyan.aqua.sega.chunithm.dao.gamedata;

import icu.samnyan.aqua.sega.chunithm.model.gamedata.GameCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChuniGameChargeRepository")
public interface GameChargeRepository extends JpaRepository<GameCharge, Long> {
}
