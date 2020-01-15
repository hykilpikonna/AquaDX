package icu.samnyan.aqua.sega.diva.dao.gamedata;

import icu.samnyan.aqua.sega.diva.model.gamedata.NgWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface NgWordsRepository extends JpaRepository<NgWords, Integer> {
}
