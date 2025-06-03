package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Report;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportPersistencePort {
    Flux<Report> getAll();

    Mono<Report> getById(String id);

    Mono<Report> create(Report reporte);

    Mono<Boolean> delete(String id);
}
