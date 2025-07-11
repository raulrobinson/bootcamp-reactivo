package com.bootcamp.ws.domain.exception;

import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {

    private final String code;
    private final String parameter;

    public DuplicateResourceException(TechnicalMessage message, String code, String parameter) {
        super(message.getMessage());
        this.code = code;
        this.parameter = parameter;
    }
}
