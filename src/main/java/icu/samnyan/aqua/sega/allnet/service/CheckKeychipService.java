package icu.samnyan.aqua.sega.allnet.service;

import icu.samnyan.aqua.sega.allnet.dao.keychip.KeyChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service("CheckKeychipService")
public class CheckKeychipService {
    private final KeyChipRepository keyChipRepository;

    @Autowired
    public CheckKeychipService(KeyChipRepository keyChipRepository) {
        this.keyChipRepository = keyChipRepository;
    }

    public boolean checkKeyChipExistence(String keychipId){
        Optional<Integer> findRes = keyChipRepository.findKeyChipExistence(keychipId);
        try {
            findRes.orElseThrow();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
