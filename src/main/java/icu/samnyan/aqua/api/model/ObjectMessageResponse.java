package icu.samnyan.aqua.api.model;

import icu.samnyan.aqua.api.model.MessageResponse;

public class ObjectMessageResponse<T> extends MessageResponse {
    private T data;

    public ObjectMessageResponse(String message) {
        super(message);
    }

    public ObjectMessageResponse(T data) {
        super();
        setData(data);
    }

    public ObjectMessageResponse(T data, String message) {
        super(message);
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
