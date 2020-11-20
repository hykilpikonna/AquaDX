package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserTrainingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserTrainingRoomRepository")
public interface UserTrainingRoomRepository extends JpaRepository<UserTrainingRoom, Long> {

    Optional<UserTrainingRoom> findByUserAndRoomId(UserData user, int roomId);

    List<UserTrainingRoom> findByUser_Card_ExtId(int userId);

    @Transactional
    void deleteByUser(UserData user);
}
