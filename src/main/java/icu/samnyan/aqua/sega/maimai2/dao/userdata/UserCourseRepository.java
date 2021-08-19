package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCourse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserCourseRepository")
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    Optional<UserCourse> findByUserAndCourseId(UserDetail user, int courseId);

    Page<UserCourse> findByUser_Card_ExtId(long userId, Pageable page);
}
