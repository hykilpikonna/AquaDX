package icu.samnyan.aqua.sega.diva.exception;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class SessionNotExistException extends RuntimeException {

    public SessionNotExistException(String message) {
        super(message);
    }

    public SessionNotExistException() {
        super();
    }
}
