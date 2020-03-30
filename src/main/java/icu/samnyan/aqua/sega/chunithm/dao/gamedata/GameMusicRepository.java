package icu.samnyan.aqua.sega.chunithm.dao.gamedata;

import icu.samnyan.aqua.sega.chunithm.model.gamedata.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChuniGameMusicRepository")
public interface GameMusicRepository extends JpaRepository<Music, Long> {

    Optional<Music> findByMusicId(int musicId);
}
