package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerPvCustomize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface PlayerPvCustomizeRepository extends JpaRepository<PlayerPvCustomize, Long> {
    Optional<PlayerPvCustomize> findByPdIdAndPvId(PlayerProfile profile, int pvId);

    Optional<PlayerPvCustomize> findByPdId_PdIdAndPvId(long pdId, int pvId);
}
