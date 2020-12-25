package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserDuelRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserDuel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserDuelService {

    private final UserDuelRepository userDuelRepository;

    @Autowired
    public UserDuelService(UserDuelRepository userDuelRepository) {
        this.userDuelRepository = userDuelRepository;
    }

    public Optional<UserDuel> getByUserAndDuelId(UserData user, int duelId) {
        return userDuelRepository.findTopByUserAndDuelIdOrderByIdDesc(user, duelId);
    }

    public UserDuel save(UserDuel userDuel) {
        return userDuelRepository.save(userDuel);
    }

    public List<UserDuel> saveAll(Iterable<UserDuel> userDuel) {
        return userDuelRepository.saveAll(userDuel);
    }

    public List<UserDuel> getByUserId(String userId) {
        return userDuelRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }
}
