package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserMusicDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserMusicDetailRepository")
public interface UserMusicDetailRepository extends JpaRepository<UserMusicDetail, Long> {

    Optional<UserMusicDetail> findTopByUserAndMusicIdAndLevelOrderByIdDesc(UserData user, int musicId, int level);

    List<UserMusicDetail> findByUser_Card_ExtId(Long extId);

    List<UserMusicDetail> findByUser_Card_ExtIdAndMusicId(Long extId, int musicId);

    Page<UserMusicDetail> findByUser_Card_ExtId(Long extId, Pageable page);
}
