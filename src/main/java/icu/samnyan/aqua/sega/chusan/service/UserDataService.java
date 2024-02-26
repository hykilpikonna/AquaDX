package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.dao.userdata.UserDataRepository;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserDataService")
public class UserDataService {

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserData saveUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    public UserData saveAndFlushUserData(UserData userData) {
        return userDataRepository.saveAndFlush(userData);
    }

    public Optional<UserData> getUserByExtId(String aimeId) {
        return userDataRepository.findByCard_ExtId(Long.parseLong(aimeId));
    }

    public Optional<UserData> getUserByCard(Card card) {
        return Optional.ofNullable(userDataRepository.findByCard(card));
    }

    public void updateLoginTime(UserData userData) {
        userData.setLastLoginDate(LocalDateTime.now());
        userDataRepository.save(userData);
    }
}
