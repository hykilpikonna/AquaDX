package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import icu.samnyan.aqua.sega.maimai.model.userdata.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserOptionRepository")
public interface UserOptionRepository extends JpaRepository<UserOption, Long> {

    Optional<UserOption> findByUser_Card_ExtId(long userId);

    Optional<UserOption> findByUser(UserData user);

}
