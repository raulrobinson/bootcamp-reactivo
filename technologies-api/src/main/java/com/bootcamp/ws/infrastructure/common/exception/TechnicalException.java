package com.bootcamp.ws.infrastructure.common.exception;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class TechnicalException extends RuntimeException {
    public TechnicalException(TechnicalMessage message) {
        super(message.getMessage());
    }

    public TechnicalException(TechnicalMessage message, Throwable cause) {
        super(message.getMessage(), cause);
    }
}
