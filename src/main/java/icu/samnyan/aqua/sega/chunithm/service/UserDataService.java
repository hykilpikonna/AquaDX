package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserDataRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public Optional<UserData> getUserByExtId(String aimeId) {
        return userDataRepository.findByCard_ExtId(Long.parseLong(aimeId));
    }

    public String updateLoginTime(UserData userData) {
        userData.setLastLoginDate(LocalDateTime.now());

        try {
            userDataRepository.save(userData);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    public UserData saveUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    public UserData saveAndFlushUserData(UserData userData) {
        return userDataRepository.saveAndFlush(userData);
    }
}
