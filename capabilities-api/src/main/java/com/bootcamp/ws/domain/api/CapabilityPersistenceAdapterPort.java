package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityPersistenceAdapterPort {
    Flux<CapabilityFullList> findAllCapabilitiesByIds(List<Long> req);

//    Mono<List<FindAssociatesTechsByCapIdResponseDto>> findAssociatesTechsByCapId(Long capabilityId);
//    Flux<Capability> findCapabilitiesByIdIn(List<Long> req);
    Mono<Boolean> existsByName(String name);
    Mono<Capability> createCapability(Capability request);

    Mono<CapabilityFullList> findCapabilityById(Long id);
}
