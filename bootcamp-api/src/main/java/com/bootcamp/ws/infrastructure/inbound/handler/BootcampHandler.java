package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.spi.BootcampServicePort;
import com.bootcamp.ws.infrastructure.inbound.dto.BootcampDTO;
import com.bootcamp.ws.infrastructure.inbound.mapper.BootcampMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class BootcampHandler {

    private final BootcampServicePort servicePort;
    private final BootcampMapper mapper;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return servicePort.findAll()
                .collectList()
                .flatMap(bootcamps -> ServerResponse.ok().bodyValue(bootcamps))
                .doOnError(error -> log.error("Error fetching bootcamps: {}", error.getMessage()))
                .onErrorResume(exception -> ServerResponse.status(500).bodyValue("Internal Server Error"));
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Long bootcampId = Long.valueOf(request.pathVariable("id"));
        return servicePort.findById(bootcampId)
                .flatMap(bootcamp -> ServerResponse.ok().bodyValue(bootcamp))
                .switchIfEmpty(ServerResponse.notFound().build())
                .doOnError(error -> log.error("Error fetching bootcamp by id: {}", error.getMessage()))
                .onErrorResume(exception -> ServerResponse.status(500).bodyValue("Internal Server Error"));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(BootcampDTO.class)
                .flatMap(boot -> servicePort.create(mapper.toDomain(boot)))
                .flatMap(bootcamp -> ServerResponse.status(201).bodyValue(bootcamp))
                .doOnError(error -> log.error("Error creating bootcamp: {}", error.getMessage()))
                .onErrorResume(exception -> ServerResponse.status(500).bodyValue("Internal Server Error"));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long bootcampId = Long.valueOf(request.pathVariable("id"));
        return servicePort.delete(bootcampId)
                .doOnError(error -> log.error("Error deleting bootcamp: {}", error.getMessage()))
                .flatMap(deleted -> {
                    if (deleted) {
                        return ServerResponse.noContent().build(); // 204 No Content
                    } else {
                        return ServerResponse.notFound().build(); // 404 Not Found
                    }
                });
    }
}
