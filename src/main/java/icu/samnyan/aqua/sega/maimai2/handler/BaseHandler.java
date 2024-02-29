package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface BaseHandler {

    Object handle(Map<String, Object> request) throws JsonProcessingException;
}
