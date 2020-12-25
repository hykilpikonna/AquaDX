package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserDataRepository")
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByCard_ExtId(long userId);
}
