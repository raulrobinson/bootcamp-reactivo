package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Bootcamp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcampServicePort {
    Mono<Bootcamp> create(Bootcamp domain);

    Flux<Bootcamp> findAll();

    Mono<Bootcamp> findById(Long bootcampId);

    Mono<Boolean> delete(Long bootcampId);
}
