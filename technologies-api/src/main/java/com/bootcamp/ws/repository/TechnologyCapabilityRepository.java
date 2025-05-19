package com.bootcamp.ws.repository;

import com.bootcamp.ws.entity.TechnologyCapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TechnologyCapabilityRepository extends ReactiveCrudRepository<TechnologyCapabilityEntity, Void> {
    Mono<Boolean> existsByCapabilityId(Long capabilityId);
}
