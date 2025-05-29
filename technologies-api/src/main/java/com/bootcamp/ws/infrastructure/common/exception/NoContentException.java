package com.bootcamp.ws.infrastructure.common.exception;

import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;

public class NoContentException extends TechnicalException {
    public NoContentException(TechnicalMessage message) {
        super(message);
    }
}
