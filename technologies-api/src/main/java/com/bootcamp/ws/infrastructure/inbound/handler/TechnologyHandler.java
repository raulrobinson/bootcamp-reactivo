package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.infrastructure.common.ErrorDto;
import com.bootcamp.ws.infrastructure.common.exceptions.BusinessException;
import com.bootcamp.ws.infrastructure.common.exceptions.NoContentException;
import com.bootcamp.ws.infrastructure.common.exceptions.ProcessorException;
import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
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

import static com.bootcamp.ws.infrastructure.common.ErrorBuilder.buildErrorResponse;
import static com.bootcamp.ws.infrastructure.common.util.Constants.CREATE_ERROR;
import static com.bootcamp.ws.infrastructure.common.util.Constants.RESOURCE_ERROR;

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

    private final ExistsByNameServicePort existsByNameServicePort;
    private final FindAllServicePort findAllServicePort;

    private final TechnologyMapper mapper;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return Mono.fromFuture(findAllServicePort.findAll())
                .flatMap(list -> ServerResponse.ok().bodyValue(list));
    }

    public Mono<ServerResponse> existsByName(ServerRequest request) {
        String name = request.pathVariable("name");
        return Mono.fromFuture(existsByNameServicePort.existsByName(name))
                .flatMap(exists -> ServerResponse.ok().bodyValue(exists))
                .onErrorResume(NoContentException.class, e ->
                        ServerResponse.noContent().build()
                );
    }
//        return null;
//        return request.bodyToMono(ExistsTechnologiesRequestDto.class)
//                .flatMapMany(technologies -> findTechnologiesByIdsServicePort.findTechnologiesByIds(technologies.getTechnologiesIds()));

//    }


//    public Mono<ServerResponse> createTechnology(ServerRequest request) {
//        return request.bodyToMono(TechnologyCreateRequestDto.class)
//                .flatMap(createTechnologyServicePort::createTechnology)
//                .flatMap(technology -> ServerResponse.ok().bodyValue(technology))
//                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
//                .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
//                        HttpStatus.BAD_REQUEST, ex.getTechnicalMessage(),
//                        List.of(ErrorDto.builder()
//                                .code(ex.getTechnicalMessage().getCode())
//                                .message(ex.getTechnicalMessage().getMessage())
//                                .parameter(ex.getTechnicalMessage().getParameter())
//                                .build())
//                ));
//    }

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
