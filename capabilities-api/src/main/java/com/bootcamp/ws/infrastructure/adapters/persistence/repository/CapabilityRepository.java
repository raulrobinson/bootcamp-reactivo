package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CapabilityRepository extends ReactiveCrudRepository<CapabilityEntity, Long> {
    Mono<Boolean> existsByName(String name);
}
