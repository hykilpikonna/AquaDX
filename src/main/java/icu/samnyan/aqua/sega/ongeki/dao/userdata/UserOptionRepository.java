package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserOptionRepository")
public interface UserOptionRepository extends JpaRepository<UserOption, Long> {

    Optional<UserOption> findByUser(UserData userData);

    Optional<UserOption> findByUser_Card_ExtId(int userId);

    @Transactional
    void deleteByUser(UserData user);
}
