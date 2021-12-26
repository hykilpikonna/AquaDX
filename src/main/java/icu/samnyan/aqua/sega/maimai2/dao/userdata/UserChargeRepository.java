package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCharge;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserChargeRepository")
public interface UserChargeRepository extends JpaRepository<UserCharge, Long> {
    List<UserCharge> findByUser_Card_ExtId(Long extId);

    Optional<UserCharge> findByUserAndChargeId(UserDetail extId, int chargeId);
}
