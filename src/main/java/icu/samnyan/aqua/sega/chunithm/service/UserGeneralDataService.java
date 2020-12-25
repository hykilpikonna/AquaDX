package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserGeneralDataRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserGeneralData;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChuniUserGeneralDataService")
public class UserGeneralDataService {

    private final UserGeneralDataRepository userGeneralDataRepository;

    public UserGeneralDataService(UserGeneralDataRepository userGeneralDataRepository) {
        this.userGeneralDataRepository = userGeneralDataRepository;
    }

    public UserGeneralData save(UserGeneralData userGeneralData) {
        return userGeneralDataRepository.save(userGeneralData);
    }

    public Optional<UserGeneralData> getByUserAndKey(UserData user, String key) {
        return userGeneralDataRepository.findByUserAndPropertyKey(user, key);
    }

    public Optional<UserGeneralData> getByUserIdAndKey(String userId, String key) {
        return userGeneralDataRepository.findByUser_Card_ExtIdAndPropertyKey(Long.parseLong(userId), key);
    }

}
