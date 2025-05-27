package com.bootcamp.ws.infrastructure.common.exceptions;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class DuplicateException extends RuntimeException {
    public DuplicateException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
