package icu.samnyan.aqua.sega.chusan.dao.gamedata;

import icu.samnyan.aqua.sega.chusan.model.gamedata.NamePlate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanGameNamePlateRepository")
public interface GameNamePlateRepository extends JpaRepository<NamePlate, Long> {

    Optional<NamePlate> findById(int trophyId);
}
