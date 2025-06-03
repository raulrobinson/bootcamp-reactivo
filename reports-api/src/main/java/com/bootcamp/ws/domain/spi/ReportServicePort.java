package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Report;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportServicePort {
    Flux<Report> getAll();

    Mono<Report> getById(String id);

    Mono<Report> create(Report report);

    Mono<Boolean> delete(String id);
}
