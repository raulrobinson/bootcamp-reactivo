package com.bootcamp.ws.handler;

import com.bootcamp.ws.common.ErrorDto;
import com.bootcamp.ws.common.exceptions.BusinessException;
import com.bootcamp.ws.common.exceptions.ProcessorException;
import com.bootcamp.ws.dto.CapabilityCreateDto;
import com.bootcamp.ws.mapper.CapabilityMapper;
import com.bootcamp.ws.service.CapabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.bootcamp.ws.common.ErrorBuilder.buildErrorResponse;
import static com.bootcamp.ws.common.util.Constants.CREATE_ERROR;
import static com.bootcamp.ws.common.util.Constants.RESOURCE_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapabilityHandler {

    private final CapabilityService service;
    private final CapabilityMapper mapper;

    public Mono<ServerResponse> createCapability(ServerRequest request) {
        return request.bodyToMono(CapabilityCreateDto.class)
                .flatMap(technology -> service.createCapability(mapper.toCapabilityEntityFromDto(technology)))
                .flatMap(technology -> ServerResponse.ok().bodyValue(technology))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                        HttpStatus.BAD_REQUEST, ex.getTechnicalMessage(),
                        List.of(ErrorDto.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getTechnicalMessage().getMessage())
                                .parameter(ex.getTechnicalMessage().getParameter())
                                .build())
                ));
    }

    public Mono<ServerResponse> existsCapabilities(ServerRequest request) {
        return request.bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .flatMapMany(service::existsCapabilities)
                .collectList()
                .flatMap(technologies -> {
                    return ServerResponse.ok().bodyValue(technologies); // 200 OK con lista
                })
                .doOnError(error -> log.error(RESOURCE_ERROR, error.getMessage()))
                .onErrorResume(ProcessorException.class, ex ->
                        buildErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                ex.getTechnicalMessage(),
                                List.of(ErrorDto.builder()
                                        .code(ex.getTechnicalMessage().getCode())
                                        .message(ex.getTechnicalMessage().getMessage())
                                        .parameter(ex.getTechnicalMessage().getParameter())
                                        .build())
                        )
                );
    }
}
