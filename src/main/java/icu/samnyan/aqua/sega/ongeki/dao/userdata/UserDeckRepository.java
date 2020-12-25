package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserDeckRepository")
public interface UserDeckRepository extends JpaRepository<UserDeck, Long> {

    List<UserDeck> findByUser_Card_ExtId(long userId);

    Optional<UserDeck> findByUserAndDeckId(UserData userData, int deckId);

    @Transactional
    void deleteByUser(UserData user);
}
