package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserScenarioRepository")
public interface UserScenarioRepository extends JpaRepository<UserScenario, Long> {

    List<UserScenario> findByUser_Card_ExtId(long userId);

    Optional<UserScenario> findByUserAndScenarioId(UserData user, int scenarioId);

}
