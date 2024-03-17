package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserPlaylogRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserPlaylog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserPlaylogService")
public class UserPlaylogService {

    private final Chu3UserPlaylogRepo userPlaylogRepository;

    @Autowired
    public UserPlaylogService(Chu3UserPlaylogRepo userPlaylogRepository) {
        this.userPlaylogRepository = userPlaylogRepository;
    }

    public UserPlaylog save(UserPlaylog userPlaylog) {
        return userPlaylogRepository.save(userPlaylog);
    }

    public List<UserPlaylog> saveAll(List<UserPlaylog> userPlaylogList) {
        return userPlaylogRepository.saveAll(userPlaylogList);
    }

    public Page<UserPlaylog> getRecentPlays(String userId, Pageable page) {
        return userPlaylogRepository.findByUserCardExtId(Long.parseLong(userId), page);
    }

    public List<UserPlaylog> getRecent30Plays(String userId) {
        Pageable page = PageRequest.of(0, 30, Sort.by(Sort.Direction.DESC, "userPlayDate"));
        return userPlaylogRepository.findByUser_Card_ExtIdAndLevelNot(Long.parseLong(userId), 5, page);
    }

    public List<UserPlaylog> getByUserId(String userId) {
        return userPlaylogRepository.findByUserCardExtId(Long.parseLong(userId));
    }

    public List<UserPlaylog> getByUserIdAndMusicIdAndLevel(String userId, int id, int level) {
        return userPlaylogRepository.findByUser_Card_ExtIdAndMusicIdAndLevel(Long.parseLong(userId), id, level);
    }
}
