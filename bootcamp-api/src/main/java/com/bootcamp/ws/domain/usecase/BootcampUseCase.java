package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.BootcampPersistenceAdapterPort;
import com.bootcamp.ws.domain.model.Bootcamp;
import com.bootcamp.ws.domain.spi.BootcampServicePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BootcampUseCase implements BootcampServicePort {

    private final BootcampPersistenceAdapterPort bootcampPersistence;

    public BootcampUseCase(BootcampPersistenceAdapterPort bootcampPersistence) {
        this.bootcampPersistence = bootcampPersistence;
    }

    @Override
    public Mono<Bootcamp> create(Bootcamp domain) {
        return bootcampPersistence.create(domain)
                .switchIfEmpty(Mono.error(new RuntimeException("Bootcamp already exists with name: " + domain.getName())));
    }

    @Override
    public Flux<Bootcamp> findAll() {
        return bootcampPersistence.findAll()
                .switchIfEmpty(Flux.error(new RuntimeException("No bootcamps found")));
    }

    @Override
    public Mono<Bootcamp> findById(Long bootcampId) {
        return bootcampPersistence.findById(bootcampId)
                .switchIfEmpty(Mono.error(new RuntimeException("Bootcamp not found with id: " + bootcampId)));
    }

    @Override
    public Mono<Boolean> delete(Long bootcampId) {
        return bootcampPersistence.delete(bootcampId)
                .switchIfEmpty(Mono.error(new RuntimeException("Bootcamp not found with id: " + bootcampId)));
    }
}
