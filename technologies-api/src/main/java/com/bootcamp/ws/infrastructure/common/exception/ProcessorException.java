package com.bootcamp.ws.infrastructure.common.exception;

import com.bootcamp.ws.domain.exception.TechnicalMessage;

public class ProcessorException extends TechnicalException {

    public ProcessorException(TechnicalMessage message) {
        super(message);
    }

    public ProcessorException(TechnicalMessage message, Throwable cause) {
        super(message, cause);
    }
}
