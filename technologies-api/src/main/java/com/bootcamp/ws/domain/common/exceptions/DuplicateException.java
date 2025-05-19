package com.bootcamp.ws.domain.common.exceptions;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;

public class DuplicateException extends RuntimeException {
    public DuplicateException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
