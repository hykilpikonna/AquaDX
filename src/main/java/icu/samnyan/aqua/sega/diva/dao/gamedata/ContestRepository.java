package icu.samnyan.aqua.sega.diva.dao.gamedata;

import icu.samnyan.aqua.sega.diva.model.gamedata.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer> {
    List<Contest> findTop8ByEnable(boolean enable);
}
