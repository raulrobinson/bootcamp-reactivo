package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.infrastructure.common.handler.GlobalErrorHandler;
import com.bootcamp.ws.infrastructure.inbound.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologiesByIdsRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.spi.*;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import static com.bootcamp.ws.domain.exception.TechnicalMessage.X_MESSAGE_ID;
import static com.bootcamp.ws.infrastructure.common.handler.MessageHeaderHandler.getMessageId;
import static com.bootcamp.ws.infrastructure.common.util.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Tag(name = "Technologies", description = "Technologies Management")
public class TechnologyHandler {

    private final TechnologyServicePort servicePort;
    private final TechnologyMapper mapper;
    private final GlobalErrorHandler globalErrorHandler;

    public Mono<ServerResponse> createTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyCreateRequestDto.class)
                .map(mapper::toDomainFromDto) // conversión DTO → Domain
                .flatMap(tech -> mapper.toResponseTechnologyDto(servicePort.createTechnology(tech)))
                .flatMap(saved -> ServerResponse.ok().bodyValue(saved))
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(exception -> globalErrorHandler.handle(exception, getMessageId(request)));
    }

    public Mono<ServerResponse> findTechnologiesByIdIn(ServerRequest request) {
        return request.bodyToMono(TechnologiesByIdsRequestDto.class)
                .map(mapper::toDomainFromTechnologiesByIdsRequestDto) // List<Long>
                .flatMap(ids -> servicePort.findTechnologiesByIdIn(ids).collectList())
                .flatMap(result -> ServerResponse.ok().bodyValue(result))
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(LIST_ERROR, error.getMessage()))
                .onErrorResume(error -> globalErrorHandler.handle(error, getMessageId(request)));
    }

    public Mono<ServerResponse> associateTechnologies(ServerRequest request) {
        return request.bodyToMono(AssociateTechnologiesCreateRequestDto.class)
                .flatMap(assoc ->
                        servicePort.associateTechnologies(assoc.getCapabilityId(), assoc.getTechnologiesIds()).collectList()
                )
                .flatMap(resultList -> ServerResponse.ok().bodyValue(resultList))
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(ASSOC_TECHS_ERROR, error.getMessage()))
                .onErrorResume(error -> globalErrorHandler.handle(error, getMessageId(request)));
    }

    public Mono<ServerResponse> findAssociatesTechsByCapId(ServerRequest request) {
        Long capabilityId = Long.parseLong(request.pathVariable("capabilityId"));
        return servicePort.findAssociatesTechsByCapId(capabilityId)
                .flatMap(resultList -> ServerResponse.ok().bodyValue(resultList))
                .contextWrite(Context.of(X_MESSAGE_ID, getMessageId(request)))
                .doOnError(error -> log.error(FIND_ASSOC_TECHS_ERROR, error.getMessage()))
                .onErrorResume(error -> globalErrorHandler.handle(error, getMessageId(request)));
    }
}
