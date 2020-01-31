package icu.samnyan.aqua.api.model;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class MessageResponse {
    private String message = "ok";

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
