package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserGameOptionEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserGameOptionExRepository extends JpaRepository<UserGameOptionEx, Long> {
    Optional<UserGameOptionEx> findByUser(UserData user);

    Optional<UserGameOptionEx> findByUser_Card_ExtId(long parseLong);
}
