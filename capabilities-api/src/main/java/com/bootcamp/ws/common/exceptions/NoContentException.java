package com.bootcamp.ws.common.exceptions;

import com.bootcamp.ws.common.enums.TechnicalMessage;

public class NoContentException extends RuntimeException {
    public NoContentException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
