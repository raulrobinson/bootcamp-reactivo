package com.bootcamp.ws.infrastructure.common.exceptions;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class NoContentException extends RuntimeException {
    public NoContentException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
