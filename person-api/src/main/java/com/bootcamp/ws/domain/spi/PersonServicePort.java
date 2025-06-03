package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonServicePort {
    Flux<Person> getAll();

    Mono<Person> getById(Long id);

    Mono<Person> create(Person reporte);

    Mono<Boolean> delete(Long id);
}
