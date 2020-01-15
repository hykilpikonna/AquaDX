package icu.samnyan.aqua.sega.diva.dao.gamedata;

import icu.samnyan.aqua.sega.diva.model.gamedata.DivaModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface DivaModuleRepository extends JpaRepository<DivaModule, Integer> {
    Optional<DivaModule> findById(int id);
}
