package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.net.utils.GenericPlaylogRepo;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPlaylog;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserPlaylogRepository")
public interface UserPlaylogRepository extends JpaRepository<UserPlaylog, Long>, GenericPlaylogRepo {

    List<UserPlaylog> findByUser_Card_ExtId(long userId);

    Page<UserPlaylog> findByUser_Card_ExtId(long userId, Pageable page);

    List<UserPlaylog> findByUser_Card_ExtIdAndMusicIdAndLevel(long userId, int musicId, int level);

    @Transactional
    void deleteByUser(UserDetail user);

}
