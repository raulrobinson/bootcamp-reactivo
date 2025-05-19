package com.bootcamp.ws.common.exceptions;

import com.bootcamp.ws.common.enums.TechnicalMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(TechnicalMessage message, String args) {
        super(message.getMessage() + ": " + args);
    }
}
