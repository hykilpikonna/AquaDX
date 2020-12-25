package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserPlaylog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserPlaylogRepository")
public interface UserPlaylogRepository extends JpaRepository<UserPlaylog, Long> {
}
