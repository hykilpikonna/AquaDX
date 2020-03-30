package icu.samnyan.aqua.sega.chunithm.dao.gamedata;

import icu.samnyan.aqua.sega.chunithm.model.gamedata.CharacterSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChuniGameCharacterSkillRepository")
public interface GameCharacterSkillRepository extends JpaRepository<CharacterSkill, Long> {
}
