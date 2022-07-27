package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserGacha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserGachaRepository")
public interface UserGachaRepository extends JpaRepository<UserGacha, Long> {
    List<UserGacha> findByUser_Card_ExtId(Long extId);

    Optional<UserGacha> findByUserAndGachaId(UserData extId, int gachaId);
}
