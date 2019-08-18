package com.pickcle.picklework.model.event;

public class StopEvent {
    private String message;

    public StopEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
