package com.client.webClient.exceptions;

import java.util.Map;

public class ServerErrorException extends Exception{
    private Map<String,String> errorMessage;

    public ServerErrorException(Map<String, String> message) {
        this.errorMessage = message;
    }

    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Map<String, String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
