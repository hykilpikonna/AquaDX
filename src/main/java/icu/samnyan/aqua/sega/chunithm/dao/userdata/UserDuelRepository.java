package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserDuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserDuelRepository extends JpaRepository<UserDuel, Long> {

    Optional<UserDuel> findTopByUserAndDuelIdOrderByIdDesc(UserData user, int duelId);

    List<UserDuel> findByUser_Card_ExtId(int extId);
}
