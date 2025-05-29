package com.bootcamp.ws.domain.exception;

public class BusinessException extends RuntimeException {
    private final String code;
    private final String parameter;

    public BusinessException(String message) {
        super(message);
        this.code = "BUSINESS_ERROR";
        this.parameter = null;
    }

    public BusinessException(String message, String code, String parameter) {
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
