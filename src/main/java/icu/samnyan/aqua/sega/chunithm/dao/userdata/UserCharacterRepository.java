package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    Page<UserCharacter> findByUser_Card_ExtId(int extId, Pageable pageable);

    List<UserCharacter> findByUser_Card_ExtId(int extId);

    Optional<UserCharacter> findTopByUserAndCharacterIdOrderByIdDesc(UserData user, int characterId);

    Optional<UserCharacter> findByUser_Card_ExtIdAndCharacterId(int extId, int characterId);

}
