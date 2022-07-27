package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.GameGachaCard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameGachaCardRepository")
public interface GameGachaCardRepository extends JpaRepository<GameGachaCard, Long> {

    List<GameGachaCard> findAllByGachaId(int gacha_id);

}
