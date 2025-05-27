package com.bootcamp.ws.infrastructure.common.exceptions;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class MissingException extends RuntimeException {
    public MissingException(TechnicalMessage message, String value) {
        super(message.getMessage() + ": " + value);
    }
}
