package icu.samnyan.aqua.sega.diva.dao.gamedata;

import icu.samnyan.aqua.sega.diva.model.gamedata.Festa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface FestaRepository extends JpaRepository<Festa, Integer> {
    List<Festa> findTop2ByEnableOrderByCreateDateDesc(boolean enable);
}
