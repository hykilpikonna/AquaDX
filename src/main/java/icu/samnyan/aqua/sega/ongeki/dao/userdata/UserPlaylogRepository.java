package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserPlaylog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserPlaylogRepository")
public interface UserPlaylogRepository extends JpaRepository<UserPlaylog, Long> {

    List<UserPlaylog> findByUser_Card_ExtId(int userId);

    Page<UserPlaylog> findByUser_Card_ExtId(int userId, Pageable page);

    List<UserPlaylog> findByUser_Card_ExtIdAndMusicIdAndLevel(Integer userId, int musicId, int level);

    @Transactional
    void deleteByUser(UserData user);
}
