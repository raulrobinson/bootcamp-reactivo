package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.PersonPersistencePort;
import com.bootcamp.ws.domain.model.Person;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.PersonEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonPersistenceAdapter implements PersonPersistencePort {

    private final PersonRepository personRepository;
    private final PersonEntityMapper mapper;

    @Override
    public Flux<Person> getAll() {
        return personRepository.findAll()
                .map(mapper::toDomain)
                .doOnNext(report -> System.out.println("Person found: " + report)) // imprime sin bloquear
                .switchIfEmpty(Flux.error(new RuntimeException("No Persons found")));
    }

    @Override
    public Mono<Person> getById(Long id) {
        return personRepository.findById(id)
                .map(mapper::toDomain)
                .switchIfEmpty(Mono.error(new RuntimeException("Person not found with id: " + id)));
    }

    @Override
    public Mono<Person> create(Person reporte) {
        return personRepository.save(mapper.toEntity(reporte))
                .map(mapper::toDomain)
                .doOnNext(report -> System.out.println("Person created: " + report)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Failed to create Person")));
    }

    @Override
    public Mono<Boolean> delete(Long id) {
        return personRepository.deleteById(id)
                .doOnSuccess(aVoid -> System.out.println("Person deleted with id: " + id)) // imprime sin bloquear
                .thenReturn(true)
                .onErrorResume(error -> {
                    System.err.println("Error deleting Person: " + error.getMessage());
                    return Mono.just(false);
                });
    }

}
