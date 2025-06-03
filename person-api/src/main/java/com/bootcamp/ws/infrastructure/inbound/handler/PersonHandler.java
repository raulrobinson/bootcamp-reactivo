package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.model.Person;
import com.bootcamp.ws.domain.spi.PersonServicePort;
import com.bootcamp.ws.infrastructure.inbound.dto.PersonDTO;
import com.bootcamp.ws.infrastructure.inbound.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonHandler {

    private final PersonServicePort service;
    private final PersonMapper mapper;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll(), Person.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return service.getById(id)
                .flatMap(reporte -> ServerResponse.ok().bodyValue(reporte))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(PersonDTO.class)
                .flatMap(dto -> service.create(mapper.toDomain(dto)))
                .flatMap(reporte -> ServerResponse.ok().bodyValue(reporte));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return service.delete(id)
                .then(ServerResponse.noContent().build());
    }

}
