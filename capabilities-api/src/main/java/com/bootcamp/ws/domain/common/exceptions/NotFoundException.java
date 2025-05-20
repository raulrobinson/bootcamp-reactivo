package com.bootcamp.ws.domain.common.exceptions;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(TechnicalMessage message, String args) {
        super(message.getMessage() + ": " + args);
    }
}
