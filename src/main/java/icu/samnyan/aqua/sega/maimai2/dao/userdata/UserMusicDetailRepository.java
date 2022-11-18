package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserMusicDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserMusicDetailRepository")
public interface UserMusicDetailRepository extends JpaRepository<UserMusicDetail, Long> {

    List<UserMusicDetail> findByUser_Card_ExtId(long userId);

    Page<UserMusicDetail> findByUser_Card_ExtId(long userId, Pageable page);

    List<UserMusicDetail> findByUser_Card_ExtIdAndMusicId(long userId, int id);

    Optional<UserMusicDetail> findByUserAndMusicIdAndLevel(UserDetail user, int musicId, int level);

    @Transactional
    void deleteByUser(UserDetail user);

}
