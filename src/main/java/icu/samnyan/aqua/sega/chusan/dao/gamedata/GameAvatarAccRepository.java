package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.AvatarAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameAvatarAccRepository")
public interface GameAvatarAccRepository extends JpaRepository<AvatarAcc, Long> {

    Optional<AvatarAcc> findById(int trophyId);
}
