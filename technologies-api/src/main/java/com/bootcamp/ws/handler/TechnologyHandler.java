package com.bootcamp.ws.handler;

import com.bootcamp.ws.common.ErrorDto;
import com.bootcamp.ws.common.exceptions.BusinessException;
import com.bootcamp.ws.common.exceptions.ProcessorException;
import com.bootcamp.ws.dto.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.dto.TechnologyCreateDto;
import com.bootcamp.ws.mapper.TechnologyMapper;
import com.bootcamp.ws.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class TechnologyHandler {

    private final TechnologyService service;
    private final TechnologyMapper mapper;

    public Mono<ServerResponse> createTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyCreateDto.class)
                .flatMap(technology -> service.createTechnology(mapper.toTechnologyEntityFromDto(technology)))
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

    public Mono<ServerResponse> existsTechnologies(ServerRequest request) {
        return request.bodyToMono(ExistsTechnologiesDto.class)
                .flatMapMany(service::existsTechnologies)
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

    public Mono<ServerResponse> associateTechnologies(ServerRequest request) {
        return request.bodyToMono(AssociateTechnologiesCreateDto.class)
                .flatMap(service::associateTechnologies)
                .flatMap(resultList -> ServerResponse.ok().bodyValue(resultList))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        ex.getTechnicalMessage(),
                        List.of(ErrorDto.builder()
                                .code(ex.getTechnicalMessage().getCode())
                                .message(ex.getTechnicalMessage().getMessage())
                                .parameter(ex.getTechnicalMessage().getParameter())
                                .build())
                ));
    }
}
