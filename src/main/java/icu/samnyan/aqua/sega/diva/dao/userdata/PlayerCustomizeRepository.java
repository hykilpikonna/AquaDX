package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.userdata.PlayerCustomize;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface PlayerCustomizeRepository extends JpaRepository<PlayerCustomize, Long> {
    List<PlayerCustomize> findByPdId(PlayerProfile profile);

    Page<PlayerCustomize> findByPdId_PdId(int pdId, Pageable page);

    Optional<PlayerCustomize> findByPdIdAndCustomizeId(PlayerProfile currentProfile, int parseInt);
}
