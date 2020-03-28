package icu.samnyan.aqua.sega.chunithm.service;

import icu.samnyan.aqua.sega.chunithm.dao.userdata.UserCharacterRepository;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
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
@Service
public class UserCharacterService {

    private final UserCharacterRepository userCharacterRepository;

    @Autowired
    public UserCharacterService(UserCharacterRepository userCharacterRepository) {
        this.userCharacterRepository = userCharacterRepository;
    }

    public UserCharacter save(UserCharacter userCharacter) {
        return userCharacterRepository.save(userCharacter);
    }

    public List<UserCharacter> saveAll(Iterable<UserCharacter> userCharacter) {
        return userCharacterRepository.saveAll(userCharacter);
    }

    public List<UserCharacter> getByUserId(String userId) {
        return userCharacterRepository.findByUser_Card_ExtId(Integer.parseInt(userId));
    }

    public Page<UserCharacter> getByUserId(String userId, int pageNumber, int maxCount) {
        Pageable pageable = PageRequest.of(pageNumber, maxCount);
        return userCharacterRepository.findByUser_Card_ExtId(Integer.parseInt(userId), pageable);
    }

    public Optional<UserCharacter> getByUserAndCharacterId(UserData user, String characterId) {
        return getByUserAndCharacterId(user, Integer.parseInt(characterId));
    }

    public Optional<UserCharacter> getByUserAndCharacterId(UserData user, int characterId) {
        return userCharacterRepository.findTopByUserAndCharacterIdOrderByIdDesc(user, characterId);
    }
}
