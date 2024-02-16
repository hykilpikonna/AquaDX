package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.Trophy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameTrophyRepository")
public interface GameTrophyRepository extends JpaRepository<Trophy, Long> {

    Optional<Trophy> findById(int trophyId);
}
