package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserDataRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.Chu3UserData;
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

    private final Chu3UserDataRepo userDataRepository;

    @Autowired
    public UserDataService(Chu3UserDataRepo userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public Chu3UserData saveUserData(Chu3UserData userData) {
        return userDataRepository.save(userData);
    }

    public Chu3UserData saveAndFlushUserData(Chu3UserData userData) {
        return userDataRepository.saveAndFlush(userData);
    }

    public Optional<Chu3UserData> getUserByExtId(String aimeId) {
        return userDataRepository.findByCard_ExtId(Long.parseLong(aimeId));
    }

    public Optional<Chu3UserData> getUserByCard(Card card) {
        return Optional.ofNullable(userDataRepository.findByCard(card));
    }

    public void updateLoginTime(Chu3UserData userData) {
        userData.setLastLoginDate(LocalDateTime.now());
        userDataRepository.save(userData);
    }
}
