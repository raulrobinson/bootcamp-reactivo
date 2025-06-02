package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.BootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BootcampRepository extends ReactiveCrudRepository<BootcampEntity, Long> {
    Mono<Boolean> existsByName(String name);
}
