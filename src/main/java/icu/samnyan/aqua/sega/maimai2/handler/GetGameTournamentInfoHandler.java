package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetGameTournamentInfoHandler")
public class GetGameTournamentInfoHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameTournamentInfoHandler.class);

    //private final GameEventRepository gameEventRepository;

    private final BasicMapper mapper;

    public GetGameTournamentInfoHandler(BasicMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {

        List<Object> gameTournamentInfoList = new ArrayList<>();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("length", 0);
        resultMap.put("gameTournamentInfoList", gameTournamentInfoList);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
