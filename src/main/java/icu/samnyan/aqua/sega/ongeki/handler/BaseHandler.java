package icu.samnyan.aqua.sega.ongeki.handler;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public interface BaseHandler {

    String handle(Map<String, Object> request) throws JsonProcessingException;
}
