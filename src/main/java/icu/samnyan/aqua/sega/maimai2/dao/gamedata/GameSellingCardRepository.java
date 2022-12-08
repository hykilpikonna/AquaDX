package icu.samnyan.aqua.sega.maimai2.dao.gamedata;

import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameSellingCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2GameSellingCardRepository")
public interface GameSellingCardRepository extends JpaRepository<GameSellingCard, Long> {
}
