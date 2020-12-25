package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.maimai.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserCharacterRepository")
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    List<UserCharacter> findByUser_Card_ExtId(long userId);

    Optional<UserCharacter> findByUserAndCharacterId(UserData user, int characterId);

}
