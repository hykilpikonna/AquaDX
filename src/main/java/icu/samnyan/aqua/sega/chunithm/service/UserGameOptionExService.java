package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserGameOptionExRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserGameOptionEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserGameOptionExService {

    private final UserGameOptionExRepository userGameOptionExRepository;

    @Autowired
    public UserGameOptionExService(UserGameOptionExRepository userGameOptionExRepository) {
        this.userGameOptionExRepository = userGameOptionExRepository;
    }

    public UserGameOptionEx save(UserGameOptionEx userGameOptionEx) {
        return userGameOptionExRepository.save(userGameOptionEx);
    }

    public Optional<UserGameOptionEx> getByUser(UserData userData) {
        return userGameOptionExRepository.findByUser(userData);
    }

    public Optional<UserGameOptionEx> getByUserId(String userId) {
        return userGameOptionExRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }
}
