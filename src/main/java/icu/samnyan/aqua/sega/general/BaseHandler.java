package icu.samnyan.aqua.sega.general;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface BaseHandler {

    Object handle(Map<String, Object> request) throws JsonProcessingException;
}
