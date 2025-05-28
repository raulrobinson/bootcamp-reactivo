package com.bootcamp.ws.domain.exception;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class DuplicateResourceException extends RuntimeException {

    private final String code;
    private final String parameter;

    public DuplicateResourceException(TechnicalMessage message, String code, String parameter) {
        super(message.getMessage());
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
