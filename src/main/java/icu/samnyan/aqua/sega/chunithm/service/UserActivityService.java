package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserActivityRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserActivity;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;

    @Autowired
    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public UserActivity save(UserActivity userActivity) {
        return userActivityRepository.save(userActivity);
    }

    public List<UserActivity> saveAll(List<UserActivity> userActivityList) {
        return userActivityRepository.saveAll(userActivityList);
    }

    public Optional<UserActivity> getByUserAndActivityIdAndKind(UserData user, int activityId, int kind) {
        return userActivityRepository.findTopByUserAndActivityIdAndKindOrderByIdDesc(user, activityId, kind);
    }

    public List<UserActivity> getAllByUserIdAndKind(String userId, String kind) {
        return userActivityRepository.findAllByUser_Card_ExtIdAndKindOrderBySortNumberDesc(Long.parseLong(userId), Integer.parseInt(kind));
    }

    public List<UserActivity> getByUserId(String userId) {
        return userActivityRepository.findAllByUser_Card_ExtId(Long.parseLong(userId));
    }
}
