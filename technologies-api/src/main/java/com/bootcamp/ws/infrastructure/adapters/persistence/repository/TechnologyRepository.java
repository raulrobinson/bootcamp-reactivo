package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {
    Mono<Boolean> existsByName(String name);

    Flux<TechnologyEntity> findByIdIn(Collection<Long> ids);
}
