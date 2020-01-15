package icu.samnyan.aqua.sega.diva.dao.gamedata;

import icu.samnyan.aqua.sega.diva.model.gamedata.DivaCustomize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface DivaCustomizeRepository extends JpaRepository<DivaCustomize, Integer> {
}
