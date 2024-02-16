package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.MapIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameMapIconRepository")
public interface GameMapIconRepository extends JpaRepository<MapIcon, Long> {

    Optional<MapIcon> findById(int mapIconId);
}
