package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.Frame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameFrameRepository")
public interface GameFrameRepository extends JpaRepository<Frame, Long> {

    Optional<Frame> findById(int trophyId);
}
