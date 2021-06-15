package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.MapEncountNpc;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2MapEncountNpcRepository")
public interface MapEncountNpcRepository extends JpaRepository<MapEncountNpc, Long> {

    Optional<MapEncountNpc> findByUserAndMusicId(UserDetail user, int musicId);

    Page<MapEncountNpc> findByUser_Card_ExtIdAndMusicId(long userId, int musicId, Pageable page);

}
