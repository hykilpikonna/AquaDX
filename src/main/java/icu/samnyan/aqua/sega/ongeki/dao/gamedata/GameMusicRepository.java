package icu.samnyan.aqua.sega.ongeki.dao.gamedata;

import icu.samnyan.aqua.sega.ongeki.model.gamedata.GameMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiGameMusicRepository")
public interface GameMusicRepository extends JpaRepository<GameMusic, Long> {
}
