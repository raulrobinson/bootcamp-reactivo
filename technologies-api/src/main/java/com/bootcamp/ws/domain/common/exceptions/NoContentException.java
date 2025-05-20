package com.bootcamp.ws.domain.common.exceptions;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;

public class NoContentException extends RuntimeException {
    public NoContentException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
