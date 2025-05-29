package com.bootcamp.ws.domain.exception;

public class DuplicateResourceException extends RuntimeException {

    private final String code;
    private final String parameter;

    public DuplicateResourceException(String message, String code, String parameter) {
        super(message);
        this.code = code;
        this.parameter = parameter;
    }

    public String getCode() {
        return code;
    }

    public String getParameter() {
        return parameter;
    }
}
