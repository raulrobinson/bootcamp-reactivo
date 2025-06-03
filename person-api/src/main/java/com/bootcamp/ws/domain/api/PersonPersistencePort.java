package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonPersistencePort {
    Flux<Person> getAll();

    Mono<Person> getById(Long id);

    Mono<Person> create(Person reporte);

    Mono<Boolean> delete(Long id);
}
