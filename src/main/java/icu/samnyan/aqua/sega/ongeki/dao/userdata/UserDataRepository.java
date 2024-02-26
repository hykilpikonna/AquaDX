package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.net.utils.GenericUserDataRepo;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserDataRepository")
public interface UserDataRepository extends JpaRepository<UserData, Long>, GenericUserDataRepo {
    List<UserData> findByCard_ExtIdIn(Collection<Long> userIds);

    UserData findByCard(Card card);

    Optional<UserData> findByCard_ExtId(long aimeId);

    @Transactional
    void deleteByCard(Card card);
}
