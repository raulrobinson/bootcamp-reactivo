package com.bootcamp.ws.domain.common.exceptions;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;

public class ValidationException extends RuntimeException {
    public ValidationException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
