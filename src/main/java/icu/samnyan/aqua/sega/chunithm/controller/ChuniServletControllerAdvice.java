package icu.samnyan.aqua.sega.chunithm.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static icu.samnyan.aqua.sega.util.AquaConst.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestControllerAdvice(basePackages = "icu.samnyan.aqua.sega.chunithm")
public class ChuniServletControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ChuniServletControllerAdvice.class);


    /**
     * Get the map object from json string
     *
     * @param request HttpServletRequest
     */
    @ModelAttribute
    public Map<String, Object> preHandle(HttpServletRequest request) throws IOException {
        var pathVar = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        byte[] src = request.getInputStream().readAllBytes();
        String outputString = new String(src, StandardCharsets.UTF_8).trim();
        logger.info("Request " + request.getRequestURI() + ": " + outputString);
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> result = mapper.readValue(outputString, new TypeReference<>() {
        });
        result.put(SERIAL_KEY, pathVar.getOrDefault(SERIAL_KEY, DEFAULT_KEYCHIP_ID));
        result.put(VERSION_KEY, pathVar.getOrDefault(VERSION_KEY, CHUNI_DEFAULT_VERSION));
        return result;
    }
}
