package icu.samnyan.aqua.sega.general.dao;

import icu.samnyan.aqua.sega.general.model.GameVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("GameVersionRepository")
public interface GameVersionRepository extends JpaRepository<GameVersion, String> {
    Optional<GameVersion> findByUuid(String uuid);
    List<GameVersion> findByLastTimeBefore(LocalDateTime time);
}
