package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.userdata.PlayerScreenShot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface PlayerScreenShotRepository extends JpaRepository<PlayerScreenShot, Long> {
    List<PlayerScreenShot> findByPdId_PdId(long pdId);
    Optional<PlayerScreenShot> findByFileName(String fileName);

}
