package icu.samnyan.aqua.sega.chunithm.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.chunithm.handler.BaseHandler;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.service.UserDataService;
import icu.samnyan.aqua.sega.util.jackson.StringMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class GetUserDataHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserDataHandler.class);

    private final StringMapper mapper;

    private final UserDataService userDataService;

    private final boolean overwriteVersion;
    private final String romVersion;
    private final String dataVersion;

    @Autowired
    public GetUserDataHandler(StringMapper mapper,
                              UserDataService userDataService,
                              @Value("${game.chunithm.overwrite-version}") boolean overwriteVersion,
                              @Value("${game.chunithm.rom-version}") String romVersion,
                              @Value("${game.chunithm.data-version}") String dataVersion
    ) {
        this.mapper = mapper;
        this.userDataService = userDataService;
        this.overwriteVersion = overwriteVersion;
        this.romVersion = romVersion;
        this.dataVersion = dataVersion;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        String userId = (String) request.get("userId");
        Optional<UserData> userDataOptional = userDataService.getUserByExtId(userId);

        if (userDataOptional.isPresent()) {
            Map<String, Object> resultMap = new LinkedHashMap<>();
            resultMap.put("userId", userId);
            UserData user = userDataOptional.get();

            if (overwriteVersion) {
                user.setLastRomVersion(romVersion);
                user.setLastDataVersion(dataVersion);
            }
            resultMap.put("userData", user);
            String json = mapper.write(resultMap);
            logger.info("Response: " + json);
            return json;
        }

        return null;
    }
}
