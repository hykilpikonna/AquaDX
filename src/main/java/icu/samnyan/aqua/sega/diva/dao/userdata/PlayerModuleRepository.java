package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.userdata.PlayerModule;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface PlayerModuleRepository extends JpaRepository<PlayerModule, Long> {
    List<PlayerModule> findByPdId(PlayerProfile profile);

    Page<PlayerModule> findByPdId_PdId(int pdId, Pageable pageable);
}
