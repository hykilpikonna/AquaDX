package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.dao.userdata.ChusanUserLoginBonusRepository;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserLoginBonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ChusanUserLoginBonusService")
public class UserLoginBonusService {
    private final ChusanUserLoginBonusRepository chusanUserLoginBonusRepository;

    @Autowired
    public UserLoginBonusService(ChusanUserLoginBonusRepository chusanUserLoginBonusRepository){
        this.chusanUserLoginBonusRepository = chusanUserLoginBonusRepository;
    }

    public Optional<UserLoginBonus> getUserLoginBonus(int userId, int preset_id){
        return this.chusanUserLoginBonusRepository.findLoginBonus(userId, 1, preset_id);
    }

    public List<UserLoginBonus> getAllUserLoginBonus(int userId){
        return this.chusanUserLoginBonusRepository.findAllLoginBonus(userId, 1, 0);
    }

    public void saveUserLoginBonus(UserLoginBonus userLoginBonus){
        this.chusanUserLoginBonusRepository.save(userLoginBonus);
    }

    public void saveAll(Iterable<UserLoginBonus> userLoginBonuses) {
        this.chusanUserLoginBonusRepository.saveAll(userLoginBonuses);
    }
}
