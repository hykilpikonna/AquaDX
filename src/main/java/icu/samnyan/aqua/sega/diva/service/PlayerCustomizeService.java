package icu.samnyan.aqua.sega.diva.service;

import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerCustomizeRepository;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerCustomize;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class PlayerCustomizeService {

    private final PlayerCustomizeRepository playerCustomizeRepository;

    public PlayerCustomizeService(PlayerCustomizeRepository playerCustomizeRepository) {
        this.playerCustomizeRepository = playerCustomizeRepository;
    }

    public PlayerCustomize buy(PlayerProfile profile, int customizeId) {
        return playerCustomizeRepository.save(new PlayerCustomize(profile, customizeId));
    }

    public String getModuleHaveString(PlayerProfile profile) {
        List<PlayerCustomize> customizeList = playerCustomizeRepository.findByPdId(profile);
        BigInteger customize_have = new BigInteger("0");
        for (PlayerCustomize customize :
                customizeList) {
            customize_have = customize_have.or(BigInteger.valueOf(1).shiftLeft(customize.getCustomizeId()));
        }
        return StringUtils.leftPad(customize_have.toString(16), 250, "0");
    }
}
