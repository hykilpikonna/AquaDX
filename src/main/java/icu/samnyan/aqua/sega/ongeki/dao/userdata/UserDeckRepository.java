package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserDeckRepository")
public interface UserDeckRepository extends JpaRepository<UserDeck, Long> {
    Optional<UserDeck> findByUserAndDeckId(UserData userData, int deckId);

    List<UserDeck> findByUser_Card_ExtId(int userId);
}
