package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserDataRepository")
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByCard(Card card);

    Optional<UserData> findByCard_ExtId(long aimeId);

    @Transactional
    void deleteByCard(Card card);
}
