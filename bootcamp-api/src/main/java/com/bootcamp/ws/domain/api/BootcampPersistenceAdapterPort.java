package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Bootcamp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcampPersistenceAdapterPort {
    Mono<Bootcamp> create(Bootcamp domain);

    Flux<Bootcamp> findAll();

    Mono<Bootcamp> findById(Long bootcampId);

    Mono<Boolean> delete(Long bootcampId);
}
