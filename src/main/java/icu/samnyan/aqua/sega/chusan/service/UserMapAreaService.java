package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserMapAreaRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserMapArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserMapAreaService")
public class UserMapAreaService {

    private final Chu3UserMapAreaRepo userMapRepository;

    @Autowired
    public UserMapAreaService(Chu3UserMapAreaRepo userMapRepository) {
        this.userMapRepository = userMapRepository;
    }

    public UserMapArea save(UserMapArea userMap) {
        return userMapRepository.save(userMap);
    }

    public List<UserMapArea> saveAll(Iterable<UserMapArea> userMap) {
        return userMapRepository.saveAll(userMap);
    }

    public List<UserMapArea> getByUser(UserData user) {
        return userMapRepository.findByUser(user);
    }

    public List<UserMapArea> getByUserId(String userId) {
        return userMapRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserMapArea> getByUserAndMapAreaId(UserData user, int mapId) {
        return userMapRepository.findTopByUserAndMapAreaIdOrderByIdDesc(user, mapId);
    }
}
