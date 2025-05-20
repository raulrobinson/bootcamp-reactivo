package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.common.ErrorDto;
import com.bootcamp.ws.domain.common.exceptions.BusinessException;
import com.bootcamp.ws.domain.common.exceptions.ProcessorException;
import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.dto.request.TechnologiesByIdsRequestDto;
import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.spi.*;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.bootcamp.ws.domain.common.ErrorBuilder.buildErrorResponse;
import static com.bootcamp.ws.domain.common.util.Constants.CREATE_ERROR;
import static com.bootcamp.ws.domain.common.util.Constants.RESOURCE_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
@Tag(name = "Technologies", description = "Technologies Management")
public class TechnologyHandler {

    private final CreateTechnologyServicePort createTechnologyServicePort;
    private final AssociateTechnologiesServicePort associateTechnologiesServicePort;
    private final ExistsTechnologiesServicePort existsTechnologiesServicePort;
    private final FindAssociatesTechsByCapIdServicePort findAssociatesTechsByCapIdServicePort;
    private final FindTechnologiesByIdsServicePort findTechnologiesByIdsServicePort;

    private final TechnologyMapper mapper;

    public Mono<ServerResponse> createTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyCreateRequestDto.class)
                .flatMap(createTechnologyServicePort::createTechnology)
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
        return request.bodyToMono(ExistsTechnologiesRequestDto.class)
                .flatMapMany(existsTechnologiesServicePort::existsTechnologies)
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

//    public Mono<ServerResponse> associateTechnologies(ServerRequest request) {
//        return request.bodyToMono(AssociateTechnologiesCreateRequestDto.class)
//                .flatMap(associateTechnologiesServicePort::associateTechnologies)
//                .flatMap(resultList -> ServerResponse.ok().bodyValue(resultList))
//                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
//                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
//                        HttpStatus.BAD_REQUEST,
//                        ex.getTechnicalMessage(),
//                        List.of(ErrorDto.builder()
//                                .code(ex.getTechnicalMessage().getCode())
//                                .message(ex.getTechnicalMessage().getMessage())
//                                .parameter(ex.getTechnicalMessage().getParameter())
//                                .build())
//                ));
//    }
//
//    public Mono<ServerResponse> findAssociatesTechsByCapId(ServerRequest request) {
//        Long capabilityId = Long.parseLong(request.pathVariable("capabilityId"));
//        return findAssociatesTechsByCapIdServicePort.findAssociatesTechsByCapId(capabilityId)
//                .flatMap(technology -> ServerResponse.ok().bodyValue(technology))
//                .doOnError(error -> log.error(RESOURCE_ERROR, error.getMessage()))
//                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
//                        HttpStatus.BAD_REQUEST,
//                        ex.getTechnicalMessage(),
//                        List.of(ErrorDto.builder()
//                                .code(ex.getTechnicalMessage().getCode())
//                                .message(ex.getTechnicalMessage().getMessage())
//                                .parameter(ex.getTechnicalMessage().getParameter())
//                                .build())
//                ));
//    }

    public Mono<ServerResponse> findTechnologiesByIds(ServerRequest request) {
        return request.bodyToMono(TechnologiesByIdsRequestDto.class)
                .flatMapMany(dto -> findTechnologiesByIdsServicePort
                        .findTechnologiesByIds(dto.getTechnologiesIds()))
                .collectList()
                .flatMap(technologies -> {
                    if (technologies.isEmpty()) {
                        return ServerResponse.noContent().build(); // 204 No Content
                    }
                    return ServerResponse.ok().bodyValue(technologies); // 200 OK con lista
                })
                .doOnError(error -> log.error(RESOURCE_ERROR, error.getMessage()))
                .onErrorResume(BusinessException.class, ex ->
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
