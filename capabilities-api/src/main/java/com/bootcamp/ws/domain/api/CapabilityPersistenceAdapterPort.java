package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityPersistenceAdapterPort {
    Mono<List<CapabilityFullList>> findAllCapabilitiesByIds(List<Long> req);

    Mono<Boolean> existsByName(String name);

    Mono<Capability> createCapability(Capability request);

    Mono<CapabilityFullList> findCapabilityById(Long id);

    Mono<Boolean> deleteCapability(Long capabilityId);

    Mono<List<Capability>> findByIdsWithPaginationAndSorting(
            List<Long> ids, String sortBy, String direction, int offset, int limit);
}
