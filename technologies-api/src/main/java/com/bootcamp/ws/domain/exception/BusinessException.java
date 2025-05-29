package com.bootcamp.ws.domain.exception;

import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;

public class BusinessException extends RuntimeException {
    private final String code;
    private final String parameter;

    public BusinessException(TechnicalMessage message, String parameter) {
        super(message.getMessage());
        this.code = "BUSINESS_ERROR";
        this.parameter = parameter;
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
