package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.BootcampPersistenceAdapterPort;
import com.bootcamp.ws.domain.model.Bootcamp;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.BootcampEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BootcampPersistenceAdapter implements BootcampPersistenceAdapterPort {

    private final BootcampRepository bootcampRepository;
    private final BootcampEntityMapper bootcampEntityMapper;

    @Override
    public Mono<Bootcamp> create(Bootcamp domain) {
        return bootcampRepository.existsByName(domain.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Bootcamp already exists with name: " + domain.getName()));
                    }
                    return bootcampRepository.save(bootcampEntityMapper.toEntity(domain))
                            .map(bootcampEntityMapper::toDomain);
                });
    }

    @Override
    public Flux<Bootcamp> findAll() {
        return bootcampRepository.findAll()
                .map(bootcampEntityMapper::toDomain)
                .doOnNext(bootcamp -> System.out.println("Bootcamp found: " + bootcamp)) // imprime sin bloquear
                .switchIfEmpty(Flux.error(new RuntimeException("No bootcamps found")));
    }

    @Override
    public Mono<Bootcamp> findById(Long bootcampId) {
        return bootcampRepository.findById(bootcampId)
                .map(bootcampEntityMapper::toDomain)
                .switchIfEmpty(Mono.error(new RuntimeException("Bootcamp not found with id: " + bootcampId)));
    }

    @Override
    public Mono<Boolean> delete(Long bootcampId) {
        return bootcampRepository.existsById(bootcampId)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new RuntimeException("Bootcamp not found with id: " + bootcampId));
                    }
                    return bootcampRepository.deleteById(bootcampId)
                            .thenReturn(true);
                });
    }
}
