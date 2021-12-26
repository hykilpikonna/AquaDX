package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPlaylog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserPlaylogRepository")
public interface UserPlaylogRepository extends JpaRepository<UserPlaylog, Long> {

}
