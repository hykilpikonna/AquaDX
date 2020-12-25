package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserMapRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserMapService {

    private final UserMapRepository userMapRepository;

    @Autowired
    public UserMapService(UserMapRepository userMapRepository) {
        this.userMapRepository = userMapRepository;
    }

    public UserMap save(UserMap userMap) {
        return userMapRepository.save(userMap);
    }

    public List<UserMap> saveAll(Iterable<UserMap> userMap) {
        return userMapRepository.saveAll(userMap);
    }

    public List<UserMap> getByUser(UserData user) {
        return userMapRepository.findAllByUser(user);
    }

    public List<UserMap> getByUserId(String userId) {
        return userMapRepository.findAllByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserMap> getByUserAndMapId(UserData user, int mapId) {
        return userMapRepository.findTopByUserAndMapIdOrderByIdDesc(user, mapId);
    }
}
