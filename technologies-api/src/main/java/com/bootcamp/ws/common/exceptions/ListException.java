package com.bootcamp.ws.common.exceptions;

import com.bootcamp.ws.common.enums.TechnicalMessage;

public class ListException extends RuntimeException {
    public ListException(TechnicalMessage message) {
        super(message.getMessage());
    }
}
