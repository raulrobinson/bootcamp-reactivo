package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;
import com.bootcamp.ws.domain.spi.CreateCapabilityServicePort;
import com.bootcamp.ws.domain.spi.ExistsCapabilitiesServicePort;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import com.bootcamp.ws.infrastructure.common.handler.GlobalErrorHandler;
import com.bootcamp.ws.infrastructure.inbound.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.infrastructure.inbound.mapper.CapabilityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.bootcamp.ws.infrastructure.common.util.Constants.CREATE_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapabilityHandler {

    private final CreateCapabilityServicePort createCapabilityServicePort;
    private final AssociateTechnologiesServicePort associateTechnologiesServicePort;
    private final ExistsCapabilitiesServicePort existsCapabilitiesServicePort;
    private final ExistsTechnologiesServicePort existsTechnologiesServicePort;

    private final CapabilityMapper mapper;
    private final GlobalErrorHandler globalErrorHandler;

    public Mono<ServerResponse> createCapability(ServerRequest request) {
        return request.bodyToMono(CapabilityCreateDto.class)
                .map(mapper::toDomainFromDto)
                .flatMap(caps -> Mono.fromFuture(mapper.toResponseCapabilityDto(createCapabilityServicePort.createCapability(caps))))
                .flatMap(saved -> ServerResponse.ok().bodyValue(saved))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(globalErrorHandler::handle);
    }

    public Mono<ServerResponse> existsCapabilities(ServerRequest request) {
//        return request.bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
//                .flatMapMany(existsCapabilitiesServicePort::existsCapabilities)
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
        return null;
    }
}
