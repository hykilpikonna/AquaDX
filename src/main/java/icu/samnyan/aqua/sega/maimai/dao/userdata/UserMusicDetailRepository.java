package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import icu.samnyan.aqua.sega.maimai.model.userdata.UserMusicDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserMusicDetailRepository")
public interface UserMusicDetailRepository extends JpaRepository<UserMusicDetail, Long> {

    @Query("SELECT musicId FROM MaiMaiUserMusicDetail WHERE user.card.extId = :userId AND musicId >= :offset GROUP BY musicId ORDER BY musicId")
    List<Integer> findMusicIdsByUser_Card_ExtIdAndOffset(long userId, long offset, Pageable page);

    List<UserMusicDetail> findByUser_Card_ExtIdAndMusicIdIn(long userId, List<Integer> ids);

    Optional<UserMusicDetail> findByUserAndMusicIdAndLevel(UserData user, int musicId, int level);
}
