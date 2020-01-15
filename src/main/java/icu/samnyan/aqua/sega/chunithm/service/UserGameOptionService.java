package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserGameOptionRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserGameOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserGameOptionService {

    private final UserGameOptionRepository userGameOptionRepository;

    @Autowired
    public UserGameOptionService(UserGameOptionRepository userGameOptionRepository) {
        this.userGameOptionRepository = userGameOptionRepository;
    }

    public Optional<UserGameOption> getByUserId(String userId) {
        return userGameOptionRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserGameOption> getByUser(UserData user) {
        return userGameOptionRepository.findByUser(user);
    }

    public UserGameOption save(UserGameOption userGameOption) {
        return userGameOptionRepository.save(userGameOption);
    }
}
