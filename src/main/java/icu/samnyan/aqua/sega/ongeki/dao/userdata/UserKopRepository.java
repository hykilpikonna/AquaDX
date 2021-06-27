package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserKop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserKopRepository")
public interface UserKopRepository extends JpaRepository<UserKop, Long> {

    List<UserKop> findByUser_Card_ExtId(long userId);

    Optional<UserKop> findByUserAndKopIdAndAreaId(UserData userData, int kopId, int areaId);

    @Transactional
    void deleteByUser(UserData user);
}
