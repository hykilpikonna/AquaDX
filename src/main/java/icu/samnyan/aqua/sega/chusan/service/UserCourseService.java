package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserCourseRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserCourse;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
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
@Service("ChusanUserCourseService")
public class UserCourseService {

    private final Chu3UserCourseRepo userCourseRepository;

    @Autowired
    public UserCourseService(Chu3UserCourseRepo userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public UserCourse save(UserCourse userCourse) {
        return userCourseRepository.save(userCourse);
    }

    public List<UserCourse> saveAll(Iterable<UserCourse> userMusicDetail) {
        return userCourseRepository.saveAll(userMusicDetail);
    }

    public List<UserCourse> getByUserId(String userId) {
        return userCourseRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Page<UserCourse> getByUserId(String userId, int pageNum, int maxCount) {
        Pageable page = PageRequest.of(pageNum, maxCount);
        return userCourseRepository.findByUser_Card_ExtId(Long.parseLong(userId), page);
    }

    public Optional<UserCourse> getByUserAndCourseId(UserData user, int courseId) {
        return userCourseRepository.findTopByUserAndCourseIdOrderByIdDesc(user, courseId);
    }
}
