package com.bootcamp.ws.common.exceptions;

import com.bootcamp.ws.common.enums.TechnicalMessage;

public class ValidationException extends RuntimeException {
    public ValidationException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
