package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.userdata.PlayerContest;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface PlayerContestRepository extends JpaRepository<PlayerContest, Long> {
    Optional<PlayerContest> findByPdIdAndContestId(PlayerProfile pdId, int contestId);

    List<PlayerContest> findTop4ByPdIdOrderByLastUpdateTimeDesc(PlayerProfile pdId);
}
