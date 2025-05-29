package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityBootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CapabilityBootcampRepository extends ReactiveCrudRepository<CapabilityBootcampEntity, Long> {
}
