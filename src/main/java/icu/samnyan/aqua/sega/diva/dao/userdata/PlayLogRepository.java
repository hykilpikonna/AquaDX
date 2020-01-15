package icu.samnyan.aqua.sega.diva.dao.userdata;

import icu.samnyan.aqua.sega.diva.model.userdata.PlayLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface PlayLogRepository extends JpaRepository<PlayLog, Long> {
    Page<PlayLog> findByPdId_PdIdOrderByDateTimeDesc(int pdId, Pageable page);
}
