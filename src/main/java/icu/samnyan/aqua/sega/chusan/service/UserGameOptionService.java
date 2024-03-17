package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserGameOptionRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserGameOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanGameOptionService")
public class UserGameOptionService {

    private final Chu3UserGameOptionRepo userGameOptionRepository;

    @Autowired
    public UserGameOptionService(Chu3UserGameOptionRepo userGameOptionRepository) {
        this.userGameOptionRepository = userGameOptionRepository;
    }

    public UserGameOption save(UserGameOption userGameOption) {
        return userGameOptionRepository.save(userGameOption);
    }

    public Optional<UserGameOption> getByUser(UserData user) {
        return userGameOptionRepository.findSingleByUser(user);
    }

    public Optional<UserGameOption> getByUserId(String userId) {
        return userGameOptionRepository.findSingleByUser_Card_ExtId(Long.parseLong(userId));
    }
}
