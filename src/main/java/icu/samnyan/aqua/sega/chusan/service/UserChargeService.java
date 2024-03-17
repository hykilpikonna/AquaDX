package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserChargeRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserCharge;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserChargeService")
public class UserChargeService {

    private final Chu3UserChargeRepo userChargeRepository;

    public UserChargeService(Chu3UserChargeRepo userChargeRepository) {
        this.userChargeRepository = userChargeRepository;
    }

    public UserCharge save(UserCharge userCharge) {
        return userChargeRepository.save(userCharge);
    }

    public List<UserCharge> saveAll(List<UserCharge> newUserChargeList) {
        return userChargeRepository.saveAll(newUserChargeList);
    }

    public List<UserCharge> getByUserId(String userId) {
        return userChargeRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Optional<UserCharge> getByUserAndChargeId(UserData user, int chargeId) {
        return userChargeRepository.findByUserAndChargeId(user, chargeId);
    }

}
