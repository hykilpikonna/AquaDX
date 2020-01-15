package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.common.Difficulty;
import icu.samnyan.aqua.sega.diva.model.common.Edition;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerPvRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface PlayerPvRecordRepository extends JpaRepository<PlayerPvRecord, Long> {
    Optional<PlayerPvRecord> findByPdIdAndPvIdAndEditionAndDifficulty(PlayerProfile profile, int pvId, Edition edition, Difficulty difficulty);

    @Query("SELECT COUNT(t1.id) as ranking from DivaPlayerPvRecord as t1 " +
            "where t1.maxScore >= (" +
            "SELECT maxScore from DivaPlayerPvRecord where pvId = :pvId and pdId = :pdId and edition = :edition and difficulty = :difficulty" +
            ") and t1.pvId = :pvId and t1.edition = :edition and t1.difficulty = :difficulty")
    Integer rankByPvIdAndPdIdAndEditionAndDifficulty(@Param("pvId") int pvId,
                                                     @Param("pdId") PlayerProfile pdId,
                                                     @Param("edition") Edition edition,
                                                     @Param("difficulty") Difficulty difficulty
    );

    List<PlayerPvRecord> findByPdId(PlayerProfile profile);

    Optional<PlayerPvRecord> findByIdAndPdId_PdId(long id, int pdId);

    List<PlayerPvRecord> findByPdIdAndEdition(PlayerProfile profile, Edition edition);

    List<PlayerPvRecord> findTop3ByPvIdAndEditionAndDifficultyOrderByMaxScore(int pvId, Edition edition, Difficulty difficulty);

    Page<PlayerPvRecord> findByPdId_PdIdOrderByPvId(int pdId, Pageable page);

    List<PlayerPvRecord> findByPdId_PdIdAndPvId(int pdId, int pvId);
}
