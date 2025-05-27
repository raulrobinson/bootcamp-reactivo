package com.bootcamp.ws.infrastructure.common.exceptions;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(TechnicalMessage message, String args) {
        super(message.getMessage() + ": " + args);
    }
}
