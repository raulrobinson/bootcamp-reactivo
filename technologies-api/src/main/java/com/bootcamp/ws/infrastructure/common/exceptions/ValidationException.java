package com.bootcamp.ws.infrastructure.common.exceptions;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class ValidationException extends RuntimeException {
    public ValidationException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
