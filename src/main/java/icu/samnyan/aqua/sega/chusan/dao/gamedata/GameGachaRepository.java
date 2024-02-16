package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.GameGacha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameGachaRepository")
public interface GameGachaRepository extends JpaRepository<GameGacha, Long> {
}
