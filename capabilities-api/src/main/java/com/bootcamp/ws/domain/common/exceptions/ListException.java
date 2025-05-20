package com.bootcamp.ws.domain.common.exceptions;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;

public class ListException extends RuntimeException {
    public ListException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
