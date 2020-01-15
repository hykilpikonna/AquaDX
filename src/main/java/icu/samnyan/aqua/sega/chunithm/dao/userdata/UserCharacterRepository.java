package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    Optional<UserCharacter> findTopByUserAndCharacterIdOrderByIdDesc(UserData user, int characterId);

    Page<UserCharacter> findByUser_Card_ExtId(long parseLong, Pageable pageable);
}
