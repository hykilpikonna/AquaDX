package icu.samnyan.aqua.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestControllerAdvice(basePackages = "icu.samnyan.aqua.api")
public class ApiControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElement() {
        return ResponseEntity.notFound().build();
    }
}
