package icu.samnyan.aqua.sega.chusan.service;

import icu.samnyan.aqua.sega.chusan.model.Chu3UserCharacterRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.chusan.model.userdata.Chu3UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service("ChusanUserCharacterService")
public class UserCharacterService {

    private final Chu3UserCharacterRepo userCharacterRepository;

    @Autowired
    public UserCharacterService(Chu3UserCharacterRepo userCharacterRepository) {
        this.userCharacterRepository = userCharacterRepository;
    }

    public UserCharacter save(UserCharacter userCharacter) {
        return userCharacterRepository.save(userCharacter);
    }

    public List<UserCharacter> saveAll(Iterable<UserCharacter> userCharacter) {
        return userCharacterRepository.saveAll(userCharacter);
    }

    public List<UserCharacter> getByUserId(String userId) {
        return userCharacterRepository.findByUser_Card_ExtId(Long.parseLong(userId));
    }

    public Page<UserCharacter> getByUserId(String userId, int pageNumber, int maxCount) {
        Pageable pageable = PageRequest.of(pageNumber, maxCount);
        return userCharacterRepository.findByUser_Card_ExtId(Long.parseLong(userId), pageable);
    }

    public Optional<UserCharacter> getByUserAndCharacterId(Chu3UserData user, int characterId) {
        return userCharacterRepository.findTopByUserAndCharacterIdOrderByIdDesc(user, characterId);
    }

}
