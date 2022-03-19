package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.SystemVoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameSystemVoiceRepository")
public interface GameSystemVoiceRepository extends JpaRepository<SystemVoice, Long> {

    Optional<SystemVoice> findById(int trophyId);
}
