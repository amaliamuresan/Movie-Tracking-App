package com.server.restservice.models;

public class Error {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = "Error: " + message;
    }
}
