package com.bootcamp.ws.common.exceptions;

import com.bootcamp.ws.common.enums.TechnicalMessage;

public class MissingException extends RuntimeException {
    public MissingException(TechnicalMessage message, String value) {
        super(message.getMessage() + ": " + value);
    }
}
