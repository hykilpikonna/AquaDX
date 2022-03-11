package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserGameOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserGameOptionRepository")
public interface UserGameOptionRepository extends JpaRepository<UserGameOption, Long> {

    Optional<UserGameOption> findByUser(UserData user);

    Optional<UserGameOption> findByUser_Card_ExtId(Long extId);
}
