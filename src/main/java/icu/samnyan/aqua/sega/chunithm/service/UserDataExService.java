package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserDataExRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserDataEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserDataExService {

    private final UserDataExRepository userDataExRepository;

    @Autowired
    public UserDataExService(UserDataExRepository userDataExRepository) {
        this.userDataExRepository = userDataExRepository;
    }

    public Optional<UserDataEx> getByUser(UserData user) {
        return userDataExRepository.findByUser(user);
    }

    public UserDataEx save(UserDataEx userDataEx) {
        return userDataExRepository.save(userDataEx);
    }

    public Optional<UserDataEx> getUserByExtId(String userId) {
        return userDataExRepository.findByUser_Card_ExtId(Integer.parseInt(userId));
    }
}
