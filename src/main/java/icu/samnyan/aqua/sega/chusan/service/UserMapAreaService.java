package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserMapRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.Chu3UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserMapAreaService")
public class UserMapAreaService {

    private final Chu3UserMapRepo userMapRepository;

    @Autowired
    public UserMapAreaService(Chu3UserMapRepo userMapRepository) {
        this.userMapRepository = userMapRepository;
    }

    public UserMap save(UserMap userMap) {
        return userMapRepository.save(userMap);
    }

    public List<UserMap> saveAll(Iterable<UserMap> userMap) {
        return userMapRepository.saveAll(userMap);
    }

    public List<UserMap> getByUser(Chu3UserData user) {
        return userMapRepository.findByUser(user);
    }

    public List<UserMap> getByUserId(String userId) {
        return userMapRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserMap> getByUserAndMapAreaId(Chu3UserData user, int mapId) {
        return userMapRepository.findTopByUserAndMapAreaIdOrderByIdDesc(user, mapId);
    }
}
