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

    public List<UserMap> getByUser(UserData user) {
        return userMapRepository.findAllByUser(user);
    }

    public List<UserMap> getByUser(String userId) {
        return userMapRepository.findAllByUser_Card_ExtId(Integer.parseInt(userId));
    }

    public Optional<UserMap> getByUserAndMapId(UserData user, String mapId) {
        return userMapRepository.findTopByUserAndMapIdOrderByIdDesc(user, Integer.parseInt(mapId));
    }

    public UserMap save(UserMap userMap) {
        return userMapRepository.save(userMap);
    }

    public List<UserMap> saveAll(Iterable<UserMap> userMap) {
        return userMapRepository.saveAll(userMap);
    }
}
