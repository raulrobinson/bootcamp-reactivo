package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.infrastructure.common.handler.GlobalErrorHandler;
import com.bootcamp.ws.infrastructure.inbound.dto.CapabilityCreateDto;
import com.bootcamp.ws.domain.spi.CapabilityServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.List;

import static com.bootcamp.ws.domain.exception.enums.TechnicalMessage.X_MESSAGE_ID;
import static com.bootcamp.ws.infrastructure.common.handler.MessageHeaderHandler.getMessageId;
import static com.bootcamp.ws.infrastructure.common.util.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapabilityHandler {

    private final CapabilityServicePort capabilityServicePort;
    private final CapabilityEntityMapper mapper;
    private final GlobalErrorHandler globalErrorHandler;

    public Mono<ServerResponse> createCapability(ServerRequest request) {
        return request.bodyToMono(CapabilityCreateDto.class)
                .flatMap(cap -> capabilityServicePort.createCapability(mapper.toDomain(cap)))
                .flatMap(technology -> ServerResponse.ok().bodyValue(technology))
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(exception -> globalErrorHandler.handle(exception, getMessageId(request)));
    }

    public Mono<ServerResponse> findCapabilitiesByIdIn(ServerRequest request) {
        return request.bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .flatMapMany(capabilityServicePort::findCapabilitiesByIdIn)
                .collectList()
                .flatMap(technologies -> {
                    return ServerResponse.ok().bodyValue(technologies); // 200 OK con lista
                })
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(LIST_ERROR, error.getMessage()))
                .onErrorResume(exception -> globalErrorHandler.handle(exception, getMessageId(request)));
    }

    public Mono<ServerResponse> findCapabilityById(ServerRequest request) {
        Long capabilityId = Long.valueOf(request.pathVariable("capabilityId"));
        return capabilityServicePort.findCapabilityById(capabilityId)
                .flatMap(capability -> ServerResponse.ok().bodyValue(capability))
                .switchIfEmpty(ServerResponse.notFound().build())
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(FIND_ERROR, error.getMessage()))
                .onErrorResume(exception -> globalErrorHandler.handle(exception, getMessageId(request)));
    }

    public Mono<ServerResponse> deleteCapability(ServerRequest request) {
        Long capabilityId = Long.valueOf(request.pathVariable("capabilityId"));
        return capabilityServicePort.deleteCapability(capabilityId)
                .flatMap(deleted -> ServerResponse.ok().bodyValue(deleted))
                .switchIfEmpty(ServerResponse.notFound().build())
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(DELETE_ERROR, error.getMessage()))
                .onErrorResume(exception -> globalErrorHandler.handle(exception, getMessageId(request)));
    }
}
