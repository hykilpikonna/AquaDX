package icu.samnyan.aqua.sega.diva.service;

import icu.samnyan.aqua.sega.diva.dao.userdata.PlayerProfileRepository;
import icu.samnyan.aqua.sega.diva.model.request.card.RegistrationRequest;
import icu.samnyan.aqua.sega.diva.model.userdata.PlayerProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Service
public class PlayerProfileService {

    private final PlayerProfileRepository playerProfileRepository;

    public PlayerProfileService(PlayerProfileRepository playerProfileRepository) {
        this.playerProfileRepository = playerProfileRepository;
    }

    public Optional<PlayerProfile> findByPdId(long pdId) {
        return playerProfileRepository.findByPdId(pdId);
    }

    public PlayerProfile register(RegistrationRequest request) {
        PlayerProfile profile = new PlayerProfile();
        profile.setPdId(request.getAime_id());
        profile.setPlayerName(request.getPlayer_name());

        return playerProfileRepository.save(profile);
    }

    public PlayerProfile save(PlayerProfile profile) {
        return playerProfileRepository.save(profile);
    }
}
