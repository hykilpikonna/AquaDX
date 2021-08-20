package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.response.data.GameRanking;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserPlaylog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserPlaylogRepository extends JpaRepository<UserPlaylog, Long> {
    List<UserPlaylog> findByUser_Card_ExtIdAndLevelNot(Long extId, int levelNot, Pageable page);

    Page<UserPlaylog> findByUser_Card_ExtId(Long extId, Pageable page);

    List<UserPlaylog> findByUser_Card_ExtIdAndMusicIdAndLevel(Long extId, int musicId, int level);

    List<UserPlaylog> findByUser_Card_ExtId(Long extId);
    
    @Query("SELECT NEW icu.samnyan.aqua.sega.chunithm.model.response.data.GameRanking(c.musicId, COUNT(c.musicId)) FROM ChuniUserPlaylog c WHERE NOT c.level = 4 GROUP BY c.musicId ORDER BY COUNT(c.musicId) DESC")
    Page<GameRanking> findGameRankingByPlaylog(Pageable page);
}
