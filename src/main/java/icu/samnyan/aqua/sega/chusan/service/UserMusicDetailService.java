package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.UserMusicDetailRepository;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserMusicDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserMusicDetailService")
public class UserMusicDetailService {

    private final UserMusicDetailRepository userMusicDetailRepository;

    @Autowired
    public UserMusicDetailService(UserMusicDetailRepository userMusicDetailRepository) {
        this.userMusicDetailRepository = userMusicDetailRepository;
    }

    public UserMusicDetail save(UserMusicDetail userMusicDetail) {
        return userMusicDetailRepository.save(userMusicDetail);
    }

    public List<UserMusicDetail> saveAll(Iterable<UserMusicDetail> userMusicDetail) {
        return userMusicDetailRepository.saveAll(userMusicDetail);
    }

    public List<UserMusicDetail> getByUserId(String userId) {
        return userMusicDetailRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Page<UserMusicDetail> getByUserId(String userId, Pageable page) {
        return userMusicDetailRepository.findByUser_Card_ExtId(Long.parseLong(userId), page);
    }

    public List<UserMusicDetail> getByUserIdAndMusicId(String userId, int musicId) {
        return userMusicDetailRepository.findByUser_Card_ExtIdAndMusicId(Long.parseLong(userId), musicId);
    }

    public Optional<UserMusicDetail> getByUserAndMusicIdAndLevel(UserData user, int musicId, int level) {
        return userMusicDetailRepository.findTopByUserAndMusicIdAndLevelOrderByIdDesc(user, musicId, level);
    }
}
