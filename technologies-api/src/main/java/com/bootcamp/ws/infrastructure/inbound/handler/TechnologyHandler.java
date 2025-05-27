package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.infrastructure.common.handler.GlobalErrorHandler;
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

import static com.bootcamp.ws.infrastructure.common.util.Constants.CREATE_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
@Tag(name = "Technologies", description = "Technologies Management")
public class TechnologyHandler {

    private final CreateTechnologyServicePort createTechnologyServicePort;
//    private final AssociateTechnologiesServicePort associateTechnologiesServicePort;
//    private final ExistsTechnologiesServicePort existsTechnologiesServicePort;
//    private final FindAssociatesTechsByCapIdServicePort findAssociatesTechsByCapIdServicePort;

    private final TechnologyMapper mapper;
    private final GlobalErrorHandler globalErrorHandler;

    public Mono<ServerResponse> createTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyCreateRequestDto.class)
                .map(mapper::toDomainFromDto) // conversión DTO → Domain
                .flatMap(tech -> Mono.fromFuture(mapper.toResponseTechnologyDto(createTechnologyServicePort.createTechnology(tech))))
                .flatMap(saved -> ServerResponse.ok().bodyValue(saved))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(globalErrorHandler::handle);
//                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
//                        HttpStatus.BAD_REQUEST, TechnicalMessage.valueOf(ex.getMessage()),
//                        List.of(new ErrorDto.Builder()
//                                .code(ex.getCode())
//                                .message(ex.getMessage())
//                                .parameter(ex.getParameter())
//                                .build())
//                ));
    }


//    public Mono<ServerResponse> existsTechnologies(ServerRequest request) {
//        return request.bodyToMono(ExistsTechnologiesRequestDto.class)
//                .flatMapMany(existsTechnologiesServicePort::existsTechnologies)
//                .collectList()
//                .flatMap(technologies -> {
//                    return ServerResponse.ok().bodyValue(technologies); // 200 OK con lista
//                })
//                .doOnError(error -> log.error(RESOURCE_ERROR, error.getMessage()))
//                .onErrorResume(ProcessorException.class, ex ->
//                        buildErrorResponse(
//                                HttpStatus.BAD_REQUEST,
//                                ex.getTechnicalMessage(),
//                                List.of(ErrorDto.builder()
//                                        .code(ex.getTechnicalMessage().getCode())
//                                        .message(ex.getTechnicalMessage().getMessage())
//                                        .parameter(ex.getTechnicalMessage().getParameter())
//                                        .build())
//                        )
//                );
//    }
//
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
}
