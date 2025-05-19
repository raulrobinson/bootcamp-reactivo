package com.bootcamp.ws.common.exceptions;

import com.bootcamp.ws.common.enums.TechnicalMessage;

public class DuplicateException extends RuntimeException {
    public DuplicateException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
