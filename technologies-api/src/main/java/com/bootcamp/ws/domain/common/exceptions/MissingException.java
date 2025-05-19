package com.bootcamp.ws.domain.common.exceptions;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;

public class MissingException extends RuntimeException {
    public MissingException(TechnicalMessage message, String value) {
        super(message.getMessage() + ": " + value);
    }
}
