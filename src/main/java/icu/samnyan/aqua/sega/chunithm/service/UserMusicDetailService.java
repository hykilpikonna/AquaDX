package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserMusicDetailRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserMusicDetail;
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
public class UserMusicDetailService {

    private final UserMusicDetailRepository userMusicDetailRepository;

    @Autowired
    public UserMusicDetailService(UserMusicDetailRepository userMusicDetailRepository) {
        this.userMusicDetailRepository = userMusicDetailRepository;
    }

    public Optional<UserMusicDetail> getByUserAndMusicIdAndLevel(UserData user, String musicId, String level) {
        return userMusicDetailRepository.findTopByUserAndMusicIdAndLevelOrderByIdDesc(user, Integer.parseInt(musicId), Integer.parseInt(level));
    }

    public UserMusicDetail save(UserMusicDetail userMusicDetail) {
        return userMusicDetailRepository.save(userMusicDetail);
    }

    public List<UserMusicDetail> saveAll(Iterable<UserMusicDetail> userMusicDetail) {
        return userMusicDetailRepository.saveAll(userMusicDetail);
    }

    public List<UserMusicDetail> getByUser(String userId) {
        return userMusicDetailRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Page<UserMusicDetail> getByUser(String userId, int pageNum, int maxCount) {
        Pageable page = PageRequest.of(pageNum, maxCount);
        return userMusicDetailRepository.findByUser_Card_ExtId(Long.parseLong(userId), page);
    }
}
