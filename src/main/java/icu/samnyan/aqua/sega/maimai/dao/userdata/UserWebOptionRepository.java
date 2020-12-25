package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import icu.samnyan.aqua.sega.maimai.model.userdata.UserWebOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserWebOptionRepository")
public interface UserWebOptionRepository extends JpaRepository<UserWebOption, Long> {

    Optional<UserWebOption> findByUser_Card_ExtId(long userId);

    Optional<UserWebOption> findByUser(UserData user);

}
