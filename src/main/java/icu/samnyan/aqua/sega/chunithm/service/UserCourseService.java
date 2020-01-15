package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserCourseRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCourse;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;

    @Autowired
    public UserCourseService(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public Optional<UserCourse> getByUserAndCourseId(UserData user, String courseId) {
        return userCourseRepository.findTopByUserAndCourseIdOrderByIdDesc(user, Integer.parseInt(courseId));
    }

    public UserCourse save(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    public List<UserCourse> saveAll(Iterable<UserCourse> userMusicDetail) {
        return userCourseRepository.saveAll(userMusicDetail);
    }

    public List<UserCourse> getByUser(String userId) {
        return userCourseRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Page<UserCourse> getByUser(String userId, int pageNum, int maxCount) {
        Pageable page = PageRequest.of(pageNum, maxCount);
        return userCourseRepository.findByUser_Card_ExtId(Long.parseLong(userId), page);
    }
}
