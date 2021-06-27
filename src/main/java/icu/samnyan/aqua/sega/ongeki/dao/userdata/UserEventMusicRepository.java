package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserEventMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserEventMusicRepository")
public interface UserEventMusicRepository extends JpaRepository<UserEventMusic, Long> {

    List<UserEventMusic> findByUser_Card_ExtId(long userId);

    Optional<UserEventMusic> findByUserAndEventIdAndTypeAndMusicId(UserData userData, int eventId, int type, int musicId);

    @Transactional
    void deleteByUser(UserData user);
}
