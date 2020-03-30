package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserCharacterRepository")
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    List<UserCharacter> findByUser_Card_ExtId(int userId);

    Page<UserCharacter> findByUser_Card_ExtId(int userId, Pageable page);

    Optional<UserCharacter> findByUserAndCharacterId(UserData userData, int characterId);

}
