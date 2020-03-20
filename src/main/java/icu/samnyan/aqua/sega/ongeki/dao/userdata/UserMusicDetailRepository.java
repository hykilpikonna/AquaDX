package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserMusicDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserMusicDetailRepository")
public interface UserMusicDetailRepository extends JpaRepository<UserMusicDetail, Long> {
    Optional<UserMusicDetail> findByUserAndMusicIdAndLevel(UserData userData, int musicId, int level);

    Page<UserMusicDetail> findByUser_Card_ExtId(int userId, Pageable page);

    List<UserMusicDetail> findByUser_Card_ExtIdAndMusicId(int userId, int id);
}
