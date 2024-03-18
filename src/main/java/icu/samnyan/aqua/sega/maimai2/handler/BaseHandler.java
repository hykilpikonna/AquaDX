package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping({"/g/mai2/Maimai2Servlet/", "/g/mai2/"})
public interface BaseHandler {

    Object handle(Map<String, Object> request) throws JsonProcessingException;
}
