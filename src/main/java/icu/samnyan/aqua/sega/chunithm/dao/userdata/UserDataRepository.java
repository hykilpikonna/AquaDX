package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByCard(Card card);

    Optional<UserData> findByCard_ExtId(long extId);
}
