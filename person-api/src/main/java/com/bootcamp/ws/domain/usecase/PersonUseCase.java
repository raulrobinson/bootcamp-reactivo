package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.PersonPersistencePort;
import com.bootcamp.ws.domain.model.Person;
import com.bootcamp.ws.domain.spi.PersonServicePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonUseCase implements PersonServicePort {

    private final PersonPersistencePort personPersistence;

    public PersonUseCase(PersonPersistencePort personPersistence) {
        this.personPersistence = personPersistence;
    }

    @Override
    public Flux<Person> getAll() {
        return personPersistence.getAll()
                .doOnNext(person -> System.out.println("Person found: " + person)) // imprime sin bloquear
                .switchIfEmpty(Flux.error(new RuntimeException("No persons found")));
    }

    @Override
    public Mono<Person> getById(Long id) {
        return personPersistence.getById(id)
                .doOnNext(person -> System.out.println("Person found: " + person)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Person not found with id: " + id)));
    }

    @Override
    public Mono<Person> create(Person reporte) {
        return personPersistence.create(reporte)
                .doOnNext(person -> System.out.println("Person created: " + person)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Failed to create person")));
    }

    @Override
    public Mono<Boolean> delete(Long id) {
        return personPersistence.delete(id)
                .doOnNext(deleted -> System.out.println("Person deleted: " + deleted)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Failed to delete person with id: " + id)));
    }
}
