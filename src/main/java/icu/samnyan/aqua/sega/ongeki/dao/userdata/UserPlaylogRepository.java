package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserPlaylog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserPlaylogRepository")
public interface UserPlaylogRepository extends JpaRepository<UserPlaylog, Long> {

    Page<UserPlaylog> findByUser_Card_ExtId(Integer userId, Pageable page);

    List<UserPlaylog> findByUser_Card_ExtIdAndMusicIdAndLevel(Integer userId, int musicId, int level);

}
