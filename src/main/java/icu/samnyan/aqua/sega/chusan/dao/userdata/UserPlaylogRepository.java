package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.net.games.GenericPlaylogRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserPlaylog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserPlaylogRepository")
public interface UserPlaylogRepository extends GenericPlaylogRepo<UserPlaylog> {
    List<UserPlaylog> findByUser_Card_ExtIdAndLevelNot(Long extId, int levelNot, Pageable page);

    Page<UserPlaylog> findByUserCardExtId(Long extId, Pageable page);

    List<UserPlaylog> findByUser_Card_ExtIdAndMusicIdAndLevel(Long extId, int musicId, int level);
}
