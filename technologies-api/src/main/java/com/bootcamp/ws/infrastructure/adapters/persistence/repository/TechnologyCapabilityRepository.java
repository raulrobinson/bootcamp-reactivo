package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyCapabilityRepository extends ReactiveCrudRepository<TechnologyCapabilityEntity, Void> {
    Mono<Boolean> existsByCapabilityId(Long capabilityId);
    Flux<TechnologyCapabilityEntity> findAllByCapabilityId(Long capabilityId);
}
