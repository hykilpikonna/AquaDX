package icu.samnyan.aqua.sega.diva.service;

import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerModuleRepository;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerModule;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class PlayerModuleService {

    private final PlayerModuleRepository playerModuleRepository;

    public PlayerModuleService(PlayerModuleRepository playerModuleRepository) {
        this.playerModuleRepository = playerModuleRepository;
    }

    public PlayerModule buy(PlayerProfile profile, int moduleId) {
        return playerModuleRepository.save(new PlayerModule(profile, moduleId));
    }

    public String getModuleHaveString(PlayerProfile profile) {
        List<PlayerModule> moduleList = playerModuleRepository.findByPdId(profile);
        BigInteger module_have = new BigInteger("0");
        for (PlayerModule module :
                moduleList) {
            module_have = module_have.or(BigInteger.valueOf(1).shiftLeft(module.getModuleId()));
        }
        System.out.println(module_have.toString(2));
        return StringUtils.leftPad(module_have.toString(16), 250, "0").toUpperCase();
    }
}
