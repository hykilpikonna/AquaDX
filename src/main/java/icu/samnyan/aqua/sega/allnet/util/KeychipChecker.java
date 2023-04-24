package icu.samnyan.aqua.sega.allnet.util;

import icu.samnyan.aqua.sega.allnet.dao.keychip.KeyChipRepository;
import icu.samnyan.aqua.sega.allnet.service.CheckKeychipService;


public class KeychipChecker {
    private final CheckKeychipService checkKeychipService;

    public KeychipChecker(KeyChipRepository keyChipRepository) {
        this.checkKeychipService = new CheckKeychipService(keyChipRepository);
    }

    public boolean checkKeychip(String keychipId){
        return checkKeychipService.checkKeyChipExistence(keychipId);
    }

}
