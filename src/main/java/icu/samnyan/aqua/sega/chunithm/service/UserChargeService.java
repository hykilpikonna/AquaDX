package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserChargeRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCharge;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class UserChargeService {

    private final UserChargeRepository userChargeRepository;

    public UserChargeService(UserChargeRepository userChargeRepository) {
        this.userChargeRepository = userChargeRepository;
    }

    public UserCharge save(UserCharge userCharge) {
        return userChargeRepository.save(userCharge);
    }

    public List<UserCharge> saveAll(List<UserCharge> newUserChargeList) {
        return userChargeRepository.saveAll(newUserChargeList);
    }

    public List<UserCharge> getByUserId(String userId) {
        return userChargeRepository.findByUser_Card_ExtId(Integer.parseInt(userId));
    }

    public Optional<UserCharge> getByUserAndChargeId(UserData user, int chargeId) {
        return userChargeRepository.findByUserAndChargeId(user, chargeId);
    }

}
