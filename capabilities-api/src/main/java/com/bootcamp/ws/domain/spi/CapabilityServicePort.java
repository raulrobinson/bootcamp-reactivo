package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityServicePort {
    Mono<Capability> createCapability(Capability request);

    Mono<List<CapabilityFullList>> findCapabilitiesByIdIn(List<Long> ids, String sortBy, String direction, int rawPage, int rawSize);

    Mono<CapabilityFullList> findCapabilityById(Long id);

    Mono<Boolean> deleteCapability(Long capabilityId);
}
