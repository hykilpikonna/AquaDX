package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserCharge;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserChargeRepository")
public interface UserChargeRepository extends JpaRepository<UserCharge, Long> {
    List<UserCharge> findByUser_Card_ExtId(Long extId);

    Optional<UserCharge> findByUserAndChargeId(UserData extId, int chargeId);
}
