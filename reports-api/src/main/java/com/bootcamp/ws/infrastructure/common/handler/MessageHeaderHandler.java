package com.bootcamp.ws.infrastructure.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.UUID;

import static com.bootcamp.ws.domain.exception.enums.TechnicalMessage.X_MESSAGE_ID;

@Component
@Slf4j
@Order(-2)
public class MessageHeaderHandler {

    public static String getMessageId(ServerRequest serverRequest) {
        return serverRequest.headers()
                .firstHeader(String.valueOf(X_MESSAGE_ID)) != null
                ? serverRequest.headers().firstHeader(String.valueOf(X_MESSAGE_ID))
                : UUID.randomUUID().toString();
    }
}
