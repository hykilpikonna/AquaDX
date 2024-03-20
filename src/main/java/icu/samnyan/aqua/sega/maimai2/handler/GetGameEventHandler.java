package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.model.Mai2GameEventRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.gamedata.GameEvent;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetGameEventHandler")
public class GetGameEventHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetGameEventHandler.class);

    private final Mai2GameEventRepo gameEventRepository;

    private final BasicMapper mapper;

    public GetGameEventHandler(Mai2GameEventRepo gameEventRepository, BasicMapper mapper) {
        this.gameEventRepository = gameEventRepository;
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        int type = ((Number) request.get("type")).intValue();

        // Not sure why maimai2 only do type=1 request
        List<GameEvent> gameEventList = gameEventRepository.findByTypeAndEnable(0, true);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("type", type);
        resultMap.put("gameEventList", gameEventList);

        String json = mapper.write(resultMap);
        logger.info("Response: length " + json.length());
        return json;
    }
}
