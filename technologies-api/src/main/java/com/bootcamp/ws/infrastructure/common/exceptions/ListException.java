package com.bootcamp.ws.infrastructure.common.exceptions;

import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;

public class ListException extends RuntimeException {
    public ListException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
