package icu.samnyan.aqua.api.model;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class OkResponse {
    private String message = "ok";

    public OkResponse(String message) {
        this.message = message;
    }

    public OkResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
