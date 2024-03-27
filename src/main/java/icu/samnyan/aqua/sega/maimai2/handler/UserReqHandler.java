package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.general.BaseHandler;

import java.util.Map;

public interface UserReqHandler extends BaseHandler {
    @Override
    default Object handle(Map<String, Object> request) throws JsonProcessingException {
        var userId = ((Number) request.get("userId")).longValue();
        return handleThis(request, userId);
    }

    Object handleThis(Map<String, Object> request, Long userId) throws JsonProcessingException;
}
