package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.dao.userdata.UserGachaRepository;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserGacha;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserGachaService")
public class UserGachaService {

    private final UserGachaRepository userGachaRepository;

    public UserGachaService(UserGachaRepository userGachaRepository) {
        this.userGachaRepository = userGachaRepository;
    }

    public UserGacha save(UserGacha userGacha) {
        return userGachaRepository.save(userGacha);
    }

    public List<UserGacha> saveAll(List<UserGacha> newUserGachaList) {
        return userGachaRepository.saveAll(newUserGachaList);
    }

    public List<UserGacha> getByUserId(String userId) {
        return userGachaRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserGacha> getByUserAndGachaId(UserData user, int gachaId) {
        return userGachaRepository.findByUserAndGachaId(user, gachaId);
    }

}
